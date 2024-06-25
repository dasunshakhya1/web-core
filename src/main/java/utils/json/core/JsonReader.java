package utils.json.core;



import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.json.annotations.JsonId;
import utils.json.configs.Configs;
import utils.json.exceptions.JsonFileNotFoundException;
import utils.json.exceptions.KeyFieldNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonReader {
    private static final Map<String, Path> JSON_FILES = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(JsonReader.class.getName());


    static {init();}

    private static void init() {
        try (Stream<Path> paths = Files.walk(Paths.get(Configs.TEST_DATA_DIR))) {
            paths.forEach(f -> {
                if (Files.isRegularFile(f)) {
                    String[] filePath = f.toString().split("/");
                    String key = filePath[filePath.length - 1].toUpperCase();
                    JSON_FILES.put(key, f);
                }
            });
            LOGGER.log(Level.INFO,"Total Data Files :: {0}" , JSON_FILES.size());
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private static <T> Path getFilePath(Class<T> tClass) {
        String extractedClassName = tClass.getName().replace("[L", "").replace(";", "");
        String[] arr = extractedClassName.split("\\.");
        String className = arr[arr.length - 1].toUpperCase().concat(".JSON");
        Path path = JSON_FILES.get(className);
        if (path == null) {
            throw new JsonFileNotFoundException(className + " is not found");
        }
        return path;
    }


    public static <T> List<T> getObjectsList(Class<T> tClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(getFilePath(tClass), charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException x) {
            LOGGER.warning(x.getMessage());
        }
        return ObjectMapperUtil.mapStringArrayToObjectArray(tClass, stringBuilder.toString());
    }


    private static <T> Field getKeyField(Class<T> tClass) {
        Field field;
        Field[] fields = tClass.getDeclaredFields();
        Optional<Field> annotatedField = Arrays.stream(fields).filter(f -> f.isAnnotationPresent(JsonId.class)).findFirst();

        if (annotatedField.isPresent()) {
            return annotatedField.get();
        }
        try {
            field = tClass.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            throw new KeyFieldNotFoundException("Id field is not found");
        }
        return field;
    }


    public static <T> T getObject(Class<T> t, Object id) {
        Optional<T> optional;
        Field field = getKeyField(t);
        optional = getObjectsList(t).stream().filter(data -> {
            try {
                field.setAccessible(true);
                return field.get(data).equals(id);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).findFirst();
        return optional.orElse(null);
    }


}
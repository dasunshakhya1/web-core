package utils.json.core;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperUtil {
    private static final Gson GSON = new Gson();

    public static <T> T mapStringToObject(Class<T> tClass, String object) {
        return GSON.fromJson(object, tClass);
    }

    public static <T> List<T> mapStringArrayToObjectArray(Class<T> tClass, String object) {
        Type type = TypeToken.getParameterized(List.class,tClass).getType();
        return GSON.fromJson(object, type);
    }

    public static <T> String mapObjectToString(T object) {
        return GSON.toJson(object);
    }
}

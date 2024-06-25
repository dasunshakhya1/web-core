package utils.json.configs;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Configs {
    public static final String TEST_DATA_DIR = System.getenv("TEST_DATA_DIR") != null ? System.getenv("TEST_DATA_DIR") : "src/main/resources/testdata";
}
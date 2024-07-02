package configs;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configs {

    public static final String TEST_DATA_DIR = System.getenv("TEST_DATA_DIR") != null ? System.getenv("TEST_DATA_DIR") : "src/main/resources/testdata";
    public static final String BROWSER = System.getenv("BROWSER")!=null?System.getenv("BROWSER"):"CHROME";
    public static final String BASE_URL = System.getenv("BASE_URL")!=null?System.getenv("BASE_URL"):"https://www.saucedemo.com/";
}

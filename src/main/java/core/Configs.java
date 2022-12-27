package core;

import java.net.URI;
import java.util.Objects;

public class Configs {

    public static final String BROWSER = System.getenv("browser")!=null?System.getenv("browser"):"CHROME";
    public static final String BASE_URL = "https://www.saucedemo.com/";

}

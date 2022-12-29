package core;

public class Configs {

    public static final String BROWSER = System.getenv("browser")!=null?System.getenv("browser"):"CHROME";
    public static final String BASE_URL = System.getenv("baseUrl")!=null?System.getenv("baseUrl"):"https://www.saucedemo.com/";

}

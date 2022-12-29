package core;

public class Configs {

    public static final String BROWSER = System.getenv("BROWSER")!=null?System.getenv("BROWSER"):"CHROME";
    public static final String BASE_URL = System.getenv("BASE_URL")!=null?System.getenv("BASE_URL"):"https://www.saucedemo.com/";

}

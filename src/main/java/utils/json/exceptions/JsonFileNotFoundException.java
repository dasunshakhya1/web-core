package utils.json.exceptions;

public class JsonFileNotFoundException extends RuntimeException{

    public JsonFileNotFoundException(String message){
        super(message);
    }
}

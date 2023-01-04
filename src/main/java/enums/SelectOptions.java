package enums;

public enum SelectOptions {

    INDEX("INDEX"),
    TEXT("TEXT"),
    VALUE("VALUE");

    private final String type;

    SelectOptions(String type) {
        this.type = type;
    }
}

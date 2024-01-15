package Types;


/**
 *
 * @author USER
 */
public abstract class ValueFields {

    private String type;

    public static final String STRING = "String";
    public static final String NUMBER = "Number";
    public static final String CHARACTER = "Character";
    public static final String COLLECTION = "Collections";

    public ValueFields(String type) {
        this.type = type;
    }

    public abstract Class<?> getClassType();

    public String getType() {
        return type;
    }

}

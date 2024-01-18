package Types;


/**
 *
 * @author USER
 */
public abstract class ValueFields {

    private String type;

    public static final String STRING = "String";
    public static final String BOOLEAN = "Boolean";
    public static final String NUMBER = "Numerical";
    public static final String CHARACTER = "Character";
    public static final String COLLECTIONSTRING = "Collection(String)";
    public static final String COLLECTIONBOOLEAN = "Collection(Boolean)";
    public static final String COLLECTIONNUMBER = "Collection(Numerical)";
    public static final String COLLECTIONCHARACTER = "Collection(Character)";

    public ValueFields(String type) {
        this.type = type;
    }

    public abstract Class<?> getClassType();

    public String getType() {
        return type;
    }

}

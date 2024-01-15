package Types;

/**
 *
 * @dzxky_
 */
public class StringType extends ValueFields {

    private String value;

    public StringType(String value) {
        super(STRING);
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    @Override
    public Class<?> getClassType() {
        return String.class;
    }

}

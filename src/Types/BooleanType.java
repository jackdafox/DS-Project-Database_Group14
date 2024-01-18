package Types;

public class BooleanType extends ValueFields {
	private Boolean value;

    public BooleanType(Boolean value) {
        super(BOOLEAN);
        this.value = value;
    }
    
    public Boolean getValue() {
        return value;
    }

    @Override
    public Class<?> getClassType() {
        return String.class;
    }
}

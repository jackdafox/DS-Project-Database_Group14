package Types;

/**
 *
 * @dzxky_
 */
public class CharacterType extends ValueFields {

    private char value;

    public CharacterType(char value) {
        super(CHARACTER);
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
    
    @Override
    public Class<?> getClassType() {
        return Character.class;
    }

}

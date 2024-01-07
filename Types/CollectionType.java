package Types;

import java.util.List;

/**
 *
 * @dzxky_
 */
public class CollectionType extends ValueFields {

    private List<?> value;

    public CollectionType(List<?> value) {
        super(COLLECTION);
        this.value = value;
    }

    @Override
    public Class<?> getClassType() {
        return Character.class;
    }

    public List<?> getValue() {
        return value;
    }

    public String toString() {
        String output = "[";
        for (int i = 0; i < value.size(); i++) {
            if (i == value.size() - 1) {
                output += value.get(i) + "]";
            } else
            output += value.get(i) + " ";
        }
        return output;
    }
}

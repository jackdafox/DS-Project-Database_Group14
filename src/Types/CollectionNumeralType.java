package Types;

import java.util.List;

public class CollectionNumeralType extends ValueFields {
	private List<Number> value;

	public CollectionNumeralType(List<Number> value) {
	        super(COLLECTIONNUMBER);
	        this.value = value;
	    }

	@Override
	public Class<?> getClassType() {
		return Character.class;
	}

	public List<Number> getValue() {
		return value;
	}

	public String toString() {
		String output = "[";
		for (int i = 0; i < value.size(); i++) {
			if (i == value.size() - 1) {
				output += value.get(i) + "]";
			} else
				output += value.get(i) + ",";
		}
		return output;
	}

	public String toCSV() {
		String output = "";
		for (int i = 0; i < value.size(); i++) {
			output += value.get(i) + ",";
		}
		return output;
	}
}

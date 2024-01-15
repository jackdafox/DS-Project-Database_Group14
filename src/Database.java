import Types.CharacterType;
import Types.CollectionType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author USER
 */
public class Database {

    private DatabaseMap<String, ValueFields> database;

    public Database() {
        database = new DatabaseMap<>();
    }

    public boolean add(String index, ValueFields value) {
        if (!database.containsKey(index)) {
            database.put(index, value);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String index) {
        if (database.containsKey(index)) {
            database.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        database.clear();
    }

    public ValueFields get(String index) {
        if (database.containsKey(index)) {
            return database.get(index);
        }
        return null;
    }

    public boolean set(String index, ValueFields value) {
        if (database.containsKey(index)) {
            database.set(index, value);
            return true;
        } else {
        	return false;
        }
    }

    public void display() {
    	if(database.getKeys() == null) {
    		return;
    	}
        HashList<String> keyList = database.getKeys();
        for (String key : keyList) {
            ValueFields value = database.get(key);
            switch (value.getType()) {
                case ValueFields.STRING:
                    StringType type1 = (StringType) value;
                    System.out.println(key + " " + type1.getValue() + " " + type1.getType());
                    break;
                case ValueFields.CHARACTER:
                    CharacterType type2 = (CharacterType) value;
                    System.out.println(key + " " + type2.getValue() + " " + type2.getType());
                    break;
                case ValueFields.NUMBER:
                    NumeralType type3 = (NumeralType) value;
                    System.out.println(key + " " + type3.getValue() + " " + type3.getType());
                    break;
                case ValueFields.COLLECTION:
                    CollectionType type4 = (CollectionType) value;
                    System.out.println(key + " " + type4.getValue().toString() + " " + type4.getType());
                    break;
            }
        }
    }
    
    public String[][] displayGUI() {
    	if(database.getKeys() == null) {
    		return null;
    	}
    	HashList<String> keyList = database.getKeys();
    	String[][] output = new String[keyList.size()][3];
    	for (int i = 0; i < output.length; i++) {
    		ValueFields value = database.get(keyList.get(i));
            switch (value.getType()) {
                case ValueFields.STRING:
                    StringType type1 = (StringType) value;
                    output[i][0] = keyList.get(i);
                    output[i][1] = type1.getType();
                    output[i][2] = type1.getValue();
                    break;
                case ValueFields.CHARACTER:
                    CharacterType type2 = (CharacterType) value;
                    output[i][0] = keyList.get(i);
                    output[i][1] = type2.getType();
                    output[i][2] = String.valueOf(type2.getValue());
                    break;
                case ValueFields.NUMBER:
                    NumeralType type3 = (NumeralType) value;
                    output[i][0] = keyList.get(i);
                    output[i][1] = type3.getType();
                    output[i][2] = String.valueOf(type3.getValue());
                    break;
                case ValueFields.COLLECTION:
                    CollectionType type4 = (CollectionType) value;
                    output[i][0] = keyList.get(i);
                    output[i][1] = type4.getType();
                    output[i][2] = type4.getValue().toString();
                    break;
            }
		}
    	return output;
    }

    public HashList<String> getCSV() {
    	if(database.getKeys() == null) {
    		return null;
    	}
        HashList<String> keyList = database.getKeys();
        HashList<String> csv = new HashList<>();
        csv.add("Key,Value,Datatype");
        for (String key : keyList) {
            ValueFields value = database.get(key);
            switch (value.getType()) {
                case ValueFields.STRING:
                    StringType type1 = (StringType) value;
                    csv.add(key + "," + type1.getValue() + "," + type1.getType());
                    break;
                case ValueFields.CHARACTER:
                    CharacterType type2 = (CharacterType) value;
                    csv.add(key + "," + type2.getValue() + "," + type2.getType());
                    break;
                case ValueFields.NUMBER:
                    NumeralType type3 = (NumeralType) value;
                    csv.add(key + "," + type3.getValue() + "," + type3.getType());
                    break;
                case ValueFields.COLLECTION:
                    CollectionType type4 = (CollectionType) value;
                    csv.add(key + "," + type4.toString() + "," + type4.getType());
                    break;
            }
        }
        return csv;
    }

    public void toCSV(String name) {
    	if(getCSV() == null) {
    		return;
    	}
        try {
            PrintWriter out = new PrintWriter(new File(name));
            HashList<String> csv = getCSV();
            for (String s : csv) {
                out.println(s);
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

}

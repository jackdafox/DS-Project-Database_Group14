import Types.BooleanType;
import Types.CharacterType;
import Types.CollectionBooleanType;
import Types.CollectionCharacterType;
import Types.CollectionNumeralType;
import Types.CollectionStringType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author USER
 */
public class Database {

	private DatabaseMap<String, ValueFields> database;
	private HashQueue<ValueFields> fieldsValue;

	public Database() {
		database = new DatabaseMap<>();
		fieldsValue = new HashQueue<>(2);
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
	
	public boolean contains(String index) {
		if (database.containsKey(index)) {
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
		if (database.getKeys() == null) {
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
			case ValueFields.COLLECTIONSTRING:
				CollectionStringType type4 = (CollectionStringType) value;
				System.out.println(key + " " + type4.getValue().toString() + " " + type4.getType());
				break;
			case ValueFields.COLLECTIONBOOLEAN:
				CollectionBooleanType type5 = (CollectionBooleanType) value;
				System.out.println(key + " " + type5.getValue().toString() + " " + type5.getType());
				break;
			case ValueFields.COLLECTIONNUMBER:
				CollectionNumeralType type6 = (CollectionNumeralType) value;
				System.out.println(key + " " + type6.getValue().toString() + " " + type6.getType());
				break;
			case ValueFields.COLLECTIONCHARACTER:
				CollectionCharacterType type7 = (CollectionCharacterType) value;
				System.out.println(key + " " + type7.getValue().toString() + " " + type7.getType());
				break;
			case ValueFields.BOOLEAN:
				BooleanType type8 = (BooleanType) value;
				System.out.println(key + " " + type8.getValue() + " " + type8.getType());
				break;
			}
		}
	}

	public String[][] displayGUI() {
		if (database.getKeys() == null) {
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
			case ValueFields.COLLECTIONSTRING:
				CollectionStringType type4 = (CollectionStringType) value;
				output[i][0] = keyList.get(i);
				output[i][1] = type4.getType();
				output[i][2] = type4.toString();
				break;
			case ValueFields.COLLECTIONBOOLEAN:
				CollectionBooleanType type5 = (CollectionBooleanType) value;
				output[i][0] = keyList.get(i);
				output[i][1] = type5.getType();
				output[i][2] = type5.toString();
				break;
			case ValueFields.COLLECTIONNUMBER:
				CollectionNumeralType type6 = (CollectionNumeralType) value;
				output[i][0] = keyList.get(i);
				output[i][1] = type6.getType();
				output[i][2] = type6.toString();
				break;
			case ValueFields.COLLECTIONCHARACTER:
				CollectionCharacterType type7 = (CollectionCharacterType) value;
				output[i][0] = keyList.get(i);
				output[i][1] = type7.getType();
				output[i][2] = type7.toString();
				break;
			case ValueFields.BOOLEAN:
				BooleanType type8 = (BooleanType) value;
				output[i][0] = keyList.get(i);
				output[i][1] = type8.getType();
				output[i][2] = String.valueOf(type8.getValue());
				break;
			}
		}
		return output;
	}

	public void importTXT(String path) {
		database.clear();
		String line = "";  
		String splitBy = ";";  
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null)
			{
				String[] csvData = line.trim().split(splitBy);
				if(csvData[0].equals("Key") && csvData[1].equals("Value")) {
					continue;
				}
				Validate.validate(csvData[2], csvData[1], fieldsValue);
				add(csvData[0], fieldsValue.dequeue());
			}
		} catch (IOException e) {
			System.out.println("File Invalid!");
		}
	}

	public HashList<String> getTXT() {
		if (database.getKeys() == null) {
			return null;
		}
		HashList<String> keyList = database.getKeys();
		HashList<String> csv = new HashList<>();
		csv.add("Key;Value;Datatype");
		for (String key : keyList) {
			ValueFields value = database.get(key);
			switch (value.getType()) {
			case ValueFields.STRING:
				StringType type1 = (StringType) value;
				csv.add(key + ";" + type1.getValue() + ";" + type1.getType());
				break;
			case ValueFields.CHARACTER:
				CharacterType type2 = (CharacterType) value;
				csv.add(key + ";" + type2.getValue() + ";" + type2.getType());
				break;
			case ValueFields.NUMBER:
				NumeralType type3 = (NumeralType) value;
				csv.add(key + ";" + type3.getValue() + ";" + type3.getType());
				break;
			case ValueFields.COLLECTIONSTRING:
				CollectionStringType type4 = (CollectionStringType) value;
				csv.add(key + ";" + type4.toCSV() + ";" + type4.getType());
				break;
			case ValueFields.COLLECTIONBOOLEAN:
				CollectionBooleanType type5 = (CollectionBooleanType) value;
				csv.add(key + ";" + type5.toCSV() + ";" + type5.getType());
				break;
			case ValueFields.COLLECTIONNUMBER:
				CollectionNumeralType type6 = (CollectionNumeralType) value;
				csv.add(key + ";" + type6.toCSV() + ";" + type6.getType());
				break;
			case ValueFields.COLLECTIONCHARACTER:
				CollectionCharacterType type7 = (CollectionCharacterType) value;
				csv.add(key + ";" + type7.toCSV() + ";" + type7.getType());
				break;
			case ValueFields.BOOLEAN:
				BooleanType type8 = (BooleanType) value;
				csv.add(key + ";" + type8.getValue() + ";" + type8.getType());
				break;
			}
		}
		return csv;
	}

	public void toCSV(File file) {
		if (getTXT() == null) {
			return;
		}
		try {
			PrintWriter out = new PrintWriter(new File(file.getAbsolutePath() + ".txt"));
			HashList<String> csv = getTXT();
			for (String s : csv) {
				out.println(s);
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Error");
		}

	}

}

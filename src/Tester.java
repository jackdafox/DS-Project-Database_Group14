
import Types.CharacterType;
import Types.CollectionType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @dzxky_
 */
public class Tester {

    public static void main(String[] args) {
        Database database = new Database();
        Integer[] arr = {23, 4};
//        database.add("123", new StringType("134"));
//        database.add("12323", new StringType("134"));
//        database.add("123423", new StringType("134"));
//        database.add("12342345", new StringType("134"));
//        database.add("12342334", new StringType("134"));
        database.clear();
        database.display();
        database.toCSV("WOW.csv");
        String[][] testingArr = database.displayGUI();
//        for (int i = 0; i < testingArr.length; i++) {
//        	System.out.println(testingArr[i][0] + " " + testingArr[i][1] + " " + testingArr[i][2]);
//		}
    }

    public static <T> List<T> listConvert(T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    public static <T> T valueConvert(ValueFields value) {
        switch (value.getType()) {
            case ValueFields.STRING:
                StringType type1 = (StringType) value;
                return (T) type1.getValue();
            case ValueFields.CHARACTER:
                CharacterType type2 = (CharacterType) value;
                return (T) type2.getValue();
            case ValueFields.NUMBER:
                NumeralType type3 = (NumeralType) value;
                return (T) type3.getValue();
            case ValueFields.COLLECTION:
                CollectionType type4 = (CollectionType) value;
                return (T) type4.getValue();
        }
        return null;
    }

}

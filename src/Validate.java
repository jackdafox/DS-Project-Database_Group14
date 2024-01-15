
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Types.CharacterType;
import Types.CollectionType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;

/**
 *
 * @author Nayatama
 */
public class Validate {
	public static boolean validate(String dataType, String value, HashQueue<ValueFields> queue) {
		boolean result = false;
		// 0 for invalid, 1 for valid
		// double - float - long - int
		switch (dataType) {
		case "Numerical":
		    try {
		        // Int
		        int intValue = Integer.parseInt(value);
		        result = true;
		        queue.enqueue(new NumeralType(intValue));
		    } catch (NumberFormatException eInt) {
		        try {
		            // Long
		            long longValue;
		            if (value.endsWith("L") || value.endsWith("l")) {
		                // Remove 'L' or 'l' suffix if present
		                value = value.substring(0, value.length() - 1);
		                longValue = Long.parseLong(value);
		            } else {
		                // Parse as a BigInteger and then convert to long
		                longValue = new BigInteger(value).longValue();
		            }
		            queue.enqueue(new NumeralType(longValue));
		            result = true;
		        } catch (NumberFormatException eLong) {
		            try {
		                // Double
		                double doubleValue = Double.parseDouble(value);
		                result = true;
		                queue.enqueue(new NumeralType(doubleValue));
		            } catch (NumberFormatException eDouble) {
		                try {
		                    // Float
		                    float floatValue = Float.parseFloat(value);
		                    result = true;
		                    queue.enqueue(new NumeralType(floatValue));
		                } catch (NumberFormatException eFloat) {
		                    result = false;
		                }
		            }
		        }
		    }
		    break;


		case "String":
			result = true;
			queue.enqueue(new StringType(value));
			break;

		case "Character":
			if (value.length() == 1 && Character.isLetter(value.charAt(0))) {
				queue.enqueue(new CharacterType(value.charAt(0)));
				result = true;
			} else {
				result = false;
			}
			break;

		case "Collection(String)":
			if (value.startsWith("[") && value.endsWith("]")) {
				value = value.substring(1, value.length() - 1);
			}
			String[] elementsString = value.split(",");
			// Validate each element based on its data type
			for (String element : elementsString) {
				if (element.matches("[a-zA-Z]+") || element.matches(".*[a-zA-Z].*\\d.*")) {
					result = true;
				} else {
					result = false;
					break;
				}
			}
			queue.enqueue(new CollectionType(listConvert(elementsString)));
			break;

		case "Collection(Numerical)":
			if (value.startsWith("[") && value.endsWith("]")) {
				value = value.substring(1, value.length() - 1);
			}
			String[] elementsNumerical = value.split(",");
			Number[] numberArr = new Number[elementsNumerical.length];
			int counter = 0;
			// Validate each element based on its data type
			for (int i = 0; i < elementsNumerical.length; i++) {
				try {
					// Double
					double doubleValue = Double.parseDouble(elementsNumerical[i]);
					result = true;
					numberArr[i] = doubleValue;
				} catch (NumberFormatException eDouble) {
					try {
						// Float
						float floatValue = Float.parseFloat(elementsNumerical[i]);
						result = true;
						numberArr[i] = floatValue;
					} catch (NumberFormatException eFloat) {
						try {
							// Long
							double doubleValue = Double.parseDouble(elementsNumerical[i]);
							long longValue;
							if (elementsNumerical[i].endsWith("L") || elementsNumerical[i].endsWith("l")) {
								value = elementsNumerical[i].substring(0, elementsNumerical[i].length() - 1);
								longValue = Long.parseLong(value);
							} else {
								longValue = new BigInteger(elementsNumerical[i]).longValue();
							}
							numberArr[i] = longValue;
							result = true;
						} catch (NumberFormatException eLong) {
							try {
								// Int
								int intValue = Integer.parseInt(elementsNumerical[i]);
								numberArr[i] = intValue;
								result = true;
							} catch (NumberFormatException eInt) {
								result = false;
								break;
							}
						}
					}
				}
			}
			queue.enqueue(new CollectionType(listConvert(numberArr)));
			break;

		case "Collection(Character)":
			if (value.startsWith("[") && value.endsWith("]")) {
				value = value.substring(1, value.length() - 1);
			}
			String[] elementsCharacter = value.split(",");
			Character[] charArr = new Character[elementsCharacter.length];
			// Validate each element based on its data type
			for (int i = 0; i < elementsCharacter.length; i++) {
				if (elementsCharacter[i].length() == 1) {
					result = true;
					charArr[i] = elementsCharacter[i].charAt(0);
				} else {
					result = false;
					break;
				}
			}
			queue.enqueue(new CollectionType(listConvert(charArr)));
			break;
		}
		return result;
	}

	public static <T> List<T> listConvert(T[] a) {
		return Arrays.stream(a).collect(Collectors.toList());
	}

}

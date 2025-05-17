import java.util.HashMap;
import java.util.Map;

public class NatoPhoneticAlphabet {
    private final Map<Character, String> natoAlphabet = new HashMap<>();

    public NatoPhoneticAlphabet () {
        natoAlphabet.put('A', "Able");
        natoAlphabet.put('B', "Baker");
        natoAlphabet.put('C', "Charlie");
        natoAlphabet.put('D', "Dog");
        natoAlphabet.put('E', "Easy");
        natoAlphabet.put('F', "Fox");
        natoAlphabet.put('G', "George");
        natoAlphabet.put('H', "How");
        natoAlphabet.put('I', "Item");
        natoAlphabet.put('J', "Jig");
        natoAlphabet.put('K', "King");
        natoAlphabet.put('L', "Love");
        natoAlphabet.put('M', "Mike");
        natoAlphabet.put('N', "Nan");
        natoAlphabet.put('O', "Oboe");
        natoAlphabet.put('P', "Peter");
        natoAlphabet.put('Q', "Queen");
        natoAlphabet.put('R', "Roger");
        natoAlphabet.put('S', "Sugar");
        natoAlphabet.put('T', "Tare");
        natoAlphabet.put('U', "Uncle");
        natoAlphabet.put('V', "Victor");
        natoAlphabet.put('W', "William");
        natoAlphabet.put('X', "X-ray");
        natoAlphabet.put('Y', "Yoke");
        natoAlphabet.put('Z', "Zebra");
    }

    public String getNatoPhoneticString (String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toUpperCase().toCharArray()) {
            try {
                result.append(natoAlphabet.get(character)).append(" ");
            } catch (ClassCastException | NullPointerException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return result.toString().trim();
    }
}

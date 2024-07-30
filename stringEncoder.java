import java.util.Random;
import java.util.LinkedList;

public class stringEncoder {
    static String[] encodeTable = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
    "S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9","(",")","*","+",",","-",".","/"};
    static LinkedList<String> encodeLinkedList = initializeLinkedList(encodeTable);
    static LinkedList<String> referenceEncode = initializeLinkedList(encodeTable);
    static Random random = new Random();
    //static int offsetCharacter = random.nextInt(44);
    static int offsetCharacter = 5;

    public static void main(String[] args) {
        String encodedString = encode(args[0]);
        System.out.println(encodedString);
        String decodedString = decode(encodedString);
        System.out.println(decodedString);
    }

    public static String encode(String plainText) {
        String encodedString = "";
        for (int i = 0; i < offsetCharacter; i++) {
            String cycledItem = encodeLinkedList.getLast();
            encodeLinkedList.addFirst(cycledItem);
            encodeLinkedList.removeLast();
        }
        for (int i = 0; i < plainText.length(); i++){
            int itemLocation = referenceEncode.indexOf(Character.toString(plainText.charAt(i)));
            if (itemLocation == -1) {
                encodedString += Character.toString(plainText.charAt(i));
            } else {
                encodedString += encodeLinkedList.get(itemLocation);
            }
        }
        return encodedString;
    }

    public static String decode(String plainText) {
        String decodedString = "";
        for (int i = 0; i < plainText.length(); i++){
            int itemLocation = encodeLinkedList.indexOf(Character.toString(plainText.charAt(i)));
            if (itemLocation == -1) {
                decodedString += Character.toString(plainText.charAt(i));
            } else {
                decodedString += referenceEncode.get(itemLocation);
            }
        }
        return decodedString;
    }

    public static LinkedList<String> initializeLinkedList(String[] stringArray) {
        LinkedList<String> encodeLinkedList = new LinkedList<String>();
        for (int i = 0; i < encodeTable.length; i++) {
            encodeLinkedList.add(encodeTable[i]);
        }
        return encodeLinkedList;
    }
}

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("What would you like to do?");
        System.out.println("1 - Create dictionary");
        System.out.println("2 - Encode file");
        System.out.println("3 - Decode file");

        int command = scanner.nextInt();
        scanner.nextLine();
        switch(command) {
            case 1:
                createDictionary();
                break;
            case 2:
                encodeFile();
                break;
            case 3:

                break;
            default:
                System.out.println("Incorrect command");
        }
    }

    private static void createDictionary() {
        System.out.println("Enter the name of file you would like to use to create dictionary");
        String fileName = scanner.nextLine();
        System.out.println("Enter the maximum length of sequences you would like to use");
        int n = scanner.nextInt();
        FileParser fileParser = new FileParser(fileName, n);
        HuffmanTree huffmanTree = HuffmanTree.create(fileParser.getSymbolFrequencies());
        Dictionary dictionary = new Dictionary(huffmanTree);
        dictionary.saveToFile();
    }

    private static void encodeFile() {
        System.out.println("Enter the name of file you would like to encode");
        String fileToEncode = scanner.nextLine();
        System.out.println("Enter the name of dictionary file");
        String dictionaryFile = scanner.nextLine();
        System.out.println("Enter the maximum length of sequences you would like to use");
        int n = scanner.nextInt();
        scanner.nextLine();
        Dictionary dictionary = new Dictionary(dictionaryFile);
        dictionary.encode(fileToEncode, n);
    }
}

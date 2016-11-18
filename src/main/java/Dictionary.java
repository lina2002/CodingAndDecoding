import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.Files;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> symbolCodes = new HashMap<>();

    public Dictionary(HuffmanTree huffmanTree) {
        dfs(huffmanTree, "");
    }

    private void dfs(HuffmanTree node, String path) {
        if (node.isLeaf()) {
            symbolCodes.put(node.getSymbol(), path);
        }
        if (node.getLeft() != null) {
            dfs(node.getLeft(), path + "0");
        }
        if (node.getRight() != null) {
            dfs(node.getRight(), path + "1");
        }
    }

    public void saveToFile() {
        try (PrintWriter file = new PrintWriter("dictionary.txt")) {
            file.println(new Gson().toJson(symbolCodes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Dictionary(String dictionaryFile) {
        try {
            String content = getFileContent(dictionaryFile);
            this.symbolCodes = new Gson().fromJson(content, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void encode(String fileToEncode, int n) {
        try {
            String content = getFileContent(fileToEncode);
            StringBuilder encodedContent = new StringBuilder("");
            content.chars()
                    .mapToObj(i -> Character.toString((char) i))
                    .map(s -> symbolCodes.get(s))
                    .forEach(encodedContent::append);
            saveToFile(fileToEncode + "_encoded.txt", encodedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decode(String fileToDecode) {
        try {
            BiMap<String, String> symbolToCode = ImmutableBiMap.copyOf(symbolCodes);
            Map<String, String> codeToSymbol = symbolToCode.inverse();
            String content = getFileContent(fileToDecode);
            StringBuilder decodedContent = new StringBuilder("");
            int decoded = 0;
            while (decoded < content.length() - 1) {
                int length = 0;
                String current;
                do {
                    length++;
                    current = content.substring(decoded, decoded + length);
                } while (!codeToSymbol.containsKey(current));
                decodedContent.append(codeToSymbol.get(current));
                decoded += length;
            }
            saveToFile(fileToDecode + "_decoded.txt", decodedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(String fileName, String encodedContent) {
        try (PrintWriter file = new PrintWriter(fileName)) {
            file.println(encodedContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFileContent(String fileName) throws IOException {
        return Files.toString(new File(fileName), StandardCharsets.US_ASCII);
    }
}

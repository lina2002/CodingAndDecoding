import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<SymbolCoding> symbolCodings = new ArrayList<>();

    public Dictionary(HuffmanTree huffmanTree) {
        dfs(huffmanTree, "");
    }

    private void dfs(HuffmanTree node, String path) {
        if (node.isLeaf()) {
            symbolCodings.add(new SymbolCoding(node.getSymbol(), path));
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
            file.println(new Gson().toJson(symbolCodings));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AllArgsConstructor
    private class SymbolCoding {
        private String symbol;
        private String code;
    }
}

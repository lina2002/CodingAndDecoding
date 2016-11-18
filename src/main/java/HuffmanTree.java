import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeSet;

@EqualsAndHashCode
@Getter
@ToString
public class HuffmanTree implements Comparable<HuffmanTree> {
    private String symbol;
    private int frequency;
    private HuffmanTree left;
    private HuffmanTree right;

    public HuffmanTree(String symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanTree(HuffmanTree left, HuffmanTree right) {
        this.symbol = left.symbol + right.symbol;
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }

    public static HuffmanTree create(Map<String, Integer> symbolFrequencies) {
        TreeSet<HuffmanTree> trees = toTrees(symbolFrequencies);

        while (trees.size() > 1) {
            HuffmanTree least = trees.pollFirst();
            HuffmanTree secondLeast = trees.pollFirst();
            HuffmanTree merged = new HuffmanTree(secondLeast, least);
            trees.add(merged);
        }

        return trees.pollFirst();
    }

    private static TreeSet<HuffmanTree> toTrees(Map<String, Integer> symbolFrequencies) {
        TreeSet<HuffmanTree> result = new TreeSet<>();
        symbolFrequencies.entrySet()
                .forEach(entry -> result.add(new HuffmanTree(entry.getKey(), entry.getValue())));
        return result;
    }

    @Override
    public int compareTo(HuffmanTree other) {
        if (this.frequency != other.frequency) {
            return this.frequency - other.frequency;
        }
        return this.symbol.compareTo(other.symbol);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

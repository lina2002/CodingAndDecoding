import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HuffmanTreeTest {

    @Test
    public void shouldCreateHuffmanTreeBasedOnSymbolsFrequencies() throws Exception {
        // Given
        Map<String, Integer> frequencies = new HashMap<>();
        frequencies.put("a", 3);
        frequencies.put("b", 4);
        frequencies.put("c", 2);
        frequencies.put("d", 6);

        // When
        HuffmanTree huffmanTree = HuffmanTree.create(frequencies);

        // Then
        HuffmanTree aNode = new HuffmanTree("a", 3);
        HuffmanTree bNode = new HuffmanTree("b", 4);
        HuffmanTree cNode = new HuffmanTree("c", 2);
        HuffmanTree dNode = new HuffmanTree("d", 6);
        HuffmanTree acNode = new HuffmanTree(aNode, cNode);
        HuffmanTree acbNode = new HuffmanTree(acNode, bNode);
        HuffmanTree acbdNode = new HuffmanTree(acbNode, dNode);

        assertThat(huffmanTree).isEqualTo(acbdNode);
    }

    @Test
    public void shouldCreateHuffmanTreeWhenThereAreSymbolsWitSameProbabilities() throws Exception {
        // Given
        Map<String, Integer> frequencies = new HashMap<>();
        frequencies.put("a", 3);
        frequencies.put("b", 3);

        // When
        HuffmanTree huffmanTree = HuffmanTree.create(frequencies);

        // Then
        HuffmanTree aNode = new HuffmanTree("a", 3);
        HuffmanTree bNode = new HuffmanTree("b", 3);
        HuffmanTree baNode = new HuffmanTree(bNode, aNode);

        assertThat(huffmanTree).isEqualTo(baNode);
    }
}

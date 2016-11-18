import com.google.common.io.Files;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class FileParser {
    private String fileName;
    private int n;

    public Map<String, Integer> getSymbolFrequencies() {
        try {
            return tryGetSymbolFrequencies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Integer> tryGetSymbolFrequencies() throws IOException {
        Map<String, Integer> symbolFrequencies = new HashMap<>();
        String fileContent = getFileContent();
        fileContent.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> incrementValueForCharacter(c, symbolFrequencies));
        return symbolFrequencies;
    }

    private void incrementValueForCharacter(char c, Map<String, Integer> symbolFrequencies) {
        String key = Character.toString(c);
        Integer oldValue = symbolFrequencies.getOrDefault(key, 0);
        symbolFrequencies.put(key, oldValue + 1);
    }

    private String getFileContent() throws IOException {
        return Files.toString(new File(fileName), StandardCharsets.US_ASCII);
    }
}

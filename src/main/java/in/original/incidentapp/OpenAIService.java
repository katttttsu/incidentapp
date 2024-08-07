package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private final OpenAIConfig openAIConfig;

    @Autowired
    public OpenAIService(OpenAIConfig openAIConfig) {
        this.openAIConfig = openAIConfig;
    }

    public void useApiKey() {
        String apiKey = openAIConfig.getKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("APIキーが設定されていません。");
        }
        System.out.println("APIキー: " + apiKey);
    }
}

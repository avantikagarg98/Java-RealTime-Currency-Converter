import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Currencies {

    private static final String BASE_URL = "https://api.frankfurter.dev/v1/latest";

    public static ExchangeResponse getExchangeData(String baseCurrency)
            throws IOException, InterruptedException {

        // Normalize input
        String base = baseCurrency.trim().toUpperCase();

        // Build URL
        String url = BASE_URL + "?from=" + URLEncoder.encode(base, StandardCharsets.UTF_8);

        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Send request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check response
        if (response.statusCode() != 200) {
            throw new RuntimeException("API error: " + response.statusCode());
        }

        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), ExchangeResponse.class);
    }
}
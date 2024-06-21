import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");
        System.out.print("Select your base currency (e.g., USD, EUR, GBP): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Select your target currency (e.g., USD, EUR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        ExchangeRateFetcher rateFetcher = new ExchangeRateFetcher(baseCurrency);
        String exchangeRateJson = rateFetcher.fetchExchangeRateJson();

        if (exchangeRateJson.isEmpty()) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
            return;
        }

        ExchangeRateParser rateParser = new ExchangeRateParser();
        double exchangeRate = rateParser.parseExchangeRate(exchangeRateJson, targetCurrency);

        if (exchangeRate == -1.0) {
            System.out.println("Failed to parse exchange rates from JSON response. Please try again later.");
            return;
        }

        System.out.print("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
        double amountToConvert = scanner.nextDouble();

        double convertedAmount = amountToConvert * exchangeRate;
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }
}

class ExchangeRateFetcher {
    private String baseCurrency;
    private String apiEndpoint;

    public ExchangeRateFetcher(String baseCurrency) {
        this.baseCurrency = baseCurrency;
        this.apiEndpoint = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
    }

    public String fetchExchangeRateJson() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiEndpoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                System.out.println("Failed to fetch exchange rates. Response code: " + responseCode);
                // Optionally, you can read the error message from the API
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    response.append(errorLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }
}

class ExchangeRateParser {
    public double parseExchangeRate(String jsonResponse, String targetCurrency) {
        double exchangeRate = -1.0;

        try {
            exchangeRate = Double.parseDouble(jsonResponse.split("\"" + targetCurrency + "\":")[1].split(",")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exchangeRate;
    }
}

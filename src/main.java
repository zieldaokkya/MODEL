import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class main {
    public static class Main {
        public static <JSONArray> void main(String[] args) {
            String url = "https://dummyjson.com/product";
            String consId = "1234567";
            String userKey = "faY738sH";

            // Mendapatkan data JSON dari URL dengan header akses
            String json = getJSONFromURL(url, consId, userKey);

            // Mengekstrak data produk dari JSON
            JSONArray products = extractProducts(json);

            // Melakukan pengurutan produk berdasarkan rating menggunakan Selection Sort
            sortProductsByRating(products);

            // Menampilkan data produk yang sudah diurutkan
            displayProducts(products);
        }

        private static String getJSONFromURL(String url, String consId, String userKey) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL requestUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-cons-ID", consId);
                connection.setRequestProperty("user_key", userKey);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return stringBuilder.toString();
        }

        private static <JSONObject, JSONArray> JSONArray extractProducts(String json) {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getJSONArray("product");
        }

        private static <JSONObject> void sortProductsByRating(JSONArray products) {
            int n = products.length();
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                double minRating = products.getJSONObject(i).getDouble("rating");
                for (int j = i + 1; j < n; j++) {
                    double currRating = products.getJSONObject(j).getDouble("rating");
                    if (currRating < minRating) {
                        minIndex = j;
                        minRating = currRating;
                    }
                }
                if (minIndex != i) {
                    JSONObject temp = products.getJSONObject(i);
                    products.put(i, products.getJSONObject(minIndex));
                    products.put(minIndex, temp);
                }
            }
        }

        private static <JSONObject> void displayProducts(JSONArray products) {
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);
                String name = product.getString("name");
                double rating = product.getDouble("rating");
                System.out.println("Name: " + name + ", Rating: " + rating);
            }
        }
    }
}

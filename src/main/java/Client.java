import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import org.json.JSONObject;

/**
 * Created by Alejandro on 1/14/17.
 */
public class Client {

    private String appId;
    private String apiKey;
    private String baseUrl = "http://api.lvh.me:3000/";
    private String version = "v1";

    public Client(String appId, String apiKey, String version) throws Exception {
        // post("resource", "body");
        appId = appId;
        apiKey = apiKey;
        version = version;
    }

    public String post(String resource, String body) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(baseUrl + version + resource);
        String authStr = appId + ":" + apiKey;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + authEncoded);
        connection.setRequestProperty("Content-Type", "application/json");
        JSONObject obj = new JSONObject();
        String jsonData = body;
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(jsonData);
        wr.flush();
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;
        while((output = in.readLine()) != null) {
            result.append(output);
        }
        in.close();
        System.out.println(result.toString());
        return result.toString();
    }

}

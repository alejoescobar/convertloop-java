package co.convertloop.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.MissingFormatArgumentException;
import com.google.gson.Gson;

/**
 * Created by Alejandro on 1/14/17.
 */
public class Client {

    private String appId;
    private String apiKey;
    private String baseUrl = "http://api.lvh.me:3000/";
    private String version = "v1";
    private final String LIB_VERSION = "java-0.1.0";


    public Client(String appId, String apiKey, String version) throws Exception {
        this.appId = appId;
        this.apiKey = apiKey;
        this.version = version;
    }

    public String createOrUpdatePerson(HashMap<String, Object> data) throws Exception {
        if (data.get("pid") == null && data.get("user_id") == null && data.get("email") == null) {
            throw new MissingFormatArgumentException("You must supply at least one of the following keys: 'pid' (to update), or 'user_id' and/or 'email' (to create or update)");
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return post("/people", json);
    }

    public String sendEventLog(HashMap<String, Object> data) throws Exception {
        if (data.get("name") == null) {
            throw new MissingFormatArgumentException("No event name provided");
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return post("/event_logs", json);
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
        connection.setRequestProperty("X-API-Source", LIB_VERSION);
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
        return result.toString();
    }

}


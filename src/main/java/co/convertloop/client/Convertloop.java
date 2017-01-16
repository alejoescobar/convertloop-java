package co.convertloop.client;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.MissingFormatArgumentException;
import com.google.gson.Gson;

/**
 * Created by Alejandro on 1/14/17.
 */
public class Convertloop {

    private Client client;

    public Convertloop(String appId, String apiKey, String version) throws Exception {
        this.client = new Client(appId, apiKey, version);
    }

}
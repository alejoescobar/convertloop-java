/**
 * Created by Alejandro on 1/14/17.
 */
public class Convertloop {

    private Client client;

    public Convertloop(String appId, String apiKey, String version) throws Exception {
        client = new Client(appId, apiKey, version);
    }

    public static void main(String args[]) throws Exception {
        new Convertloop("ed5efc09", "HrHXLw6FtfB4MMcxWPGncao", "v1");
    }

}

import java.util.HashMap;

/**
 * Created by Alejandro on 1/14/17.
 */
public class Convertloop {

    private Client client;

    public Convertloop(String appId, String apiKey, String version) throws Exception {
        this.client = new Client(appId, apiKey, version);
    }

    public static void main(String args[]) throws Exception {
        Convertloop  convertloop = new Convertloop("ed5efc09", "zHrHXLw6FtfB4MMcxWPGncao", "v1");
        HashMap<String, Object> data = new HashMap<String, Object>();
//        data.put("email", "alejoescobac+2@gmail.com");
//        data.put("first_name", "alejo2");
//        data.put("last_name", "escobar2");
//        data.put("credits", 1);
//        convertloop.client.createOrUpdatePerson(data);
        HashMap<String, Object> metadata = new HashMap<String, Object>();
        HashMap<String, Object> person = new HashMap<String, Object>();
        metadata.put("credits", 1000);
        person.put("email", "alejoescobac+2@gmail.com");
        data.put("name", "Billed");
        data.put("metadata", metadata);
        data.put("person", person);
        convertloop.client.sendEventLog(data);
    }

}

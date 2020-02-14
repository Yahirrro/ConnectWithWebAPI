package party.morino.apipush.connectwithwebapi;

import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class POSTtoAPI {
    public static <JSONException extends Throwable> void POST(String path, String input, CommandSender sender) {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.morino.party/" + path );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8;");

                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}

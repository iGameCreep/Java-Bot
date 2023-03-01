package fr.gamecreep.bot;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/*
Test réalisé dans le but de récupérer toute les commandes du bot Twitch, la technique utilisée est pas ouf mais bon, ca m'amusait de le faire x)
 */

public class test {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://api.funixgaming.fr/funixbot/command";
    public static void main(String[] args) throws IOException {

        // Create a neat value object to hold the URL
        String res = sendGET();

        Gson gson = new Gson();

        String res2 = res.replace("{\"content\":", "").replace(",\"totalPages\":1,\"actualPage\":0,\"totalElementsDatabase\":12,\"totalElementsThisPage\":12}", "");

        command[] cmds = gson.fromJson(res2, command[].class);

        Map<String, String> twitchcmds = new HashMap<String, String>();

        for (command cmd : cmds) {
            twitchcmds.put(cmd.command, cmd.message);
        }

        System.out.println(twitchcmds.getClass());

        for (Entry<String, String> element : twitchcmds.entrySet()) {
            System.out.println("Nom de la commande : " + element.getKey() + ". Message : " + element.getValue());
        }



    }

    public class result {
        cmd content;
        int totalPages;
        int actualPage;
        int totalElementsDatabase;
        int TotalElementsThisPage;
    }

    public class cmd {
        command[] commands;
    }

    public class command {
        String id;
        Date createdAt;
        Date updatedAt;
        String command;
        String message;
    }

    private static String sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return null;
        }

    }

}

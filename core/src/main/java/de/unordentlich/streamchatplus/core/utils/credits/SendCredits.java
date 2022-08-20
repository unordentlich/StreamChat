package de.unordentlich.streamchatplus.core.utils.credits;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.unordentlich.streamchatplus.core.utils.credits.objects.Credit;
import net.labymod.api.util.I18n;
import net.labymod.api.util.io.web.URLResolver;
import net.labymod.api.util.io.web.WebResponse;
import net.labymod.api.util.io.web.exception.WebRequestException;
import net.labymod.api.util.io.web.result.Callback;
import net.labymod.api.util.io.web.result.Result;

import java.io.File;
import java.util.UUID;

public class SendCredits {

    public static String generateAndSend(Credit... uuids) {
        StringBuilder credits = new StringBuilder();
        credits.append("\n==========================================\n");
        credits.append("=    A SUPER AWESOME SPECIAL THANKS TO   =\n");
        //TODO UUIDFetcher
        for(Credit current : uuids) {
            credits.append("= ");
            credits.append(current.getUuid());
            credits.append(" — ");
            credits.append(current.getContribution());
            credits.append(calculateSpaces(38, current.getUuid() + " — " + current.getContribution()));
            credits.append("=\n");
        }
        credits.append("= FOR THIS OUTSTANDING WORK & SUPPORT ❤ =\n");
        credits.append("=========================================");
        return credits.toString();
    }

    private static String calculateSpaces(int expectedAmount, String value) {
        int leftSpaces = expectedAmount - value.length();
        StringBuilder returnBuild = new StringBuilder();
        for (int i = 0; i <= leftSpaces; i++) {
            returnBuild.append(" ");
        }
        return returnBuild.toString();
    }

    /**public static JsonObject getJSON(String url) {
        StringBuilder response = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (connection.getResponseCode() != 200) {
                JsonObject object = new JsonObject();
                object.addProperty("error", connection.getResponseCode());
                return object;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            for (String line; (line = in.readLine()) != null;)
                response.append(line);

            in.close();
            connection.disconnect();

            return parse(response.toString());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static UUID getUUID(String username) {
        JsonObject response = getJSON(
                String.format("https://api.mojang.com/users/profiles/minecraft/%s", username)
        );

        if (response == null)
            return null;

        String uuid = response.get("id").getAsString();
        uuid = uuid
                .replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                        "$1-$2-$3-$4-$5");

        return UUID.fromString(uuid);
    }**/
}

package fr.gamecreep.bot;

import fr.gamecreep.bot.events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import io.github.cdimascio.dotenv.Dotenv;

import javax.security.auth.login.LoginException;
import java.util.Map;

public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Set token and login
        final String TOKEN = dotenv.get("TOKEN");
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        JDA jda = jdaBuilder
                // Load intents
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                // Load events
                .addEventListeners(new ReadyEventListener(), new MessageEventListener(), new InteractionEventListener())
                // Build the bot
                .build();

        jda.upsertCommand("slashcommand", "Slash commande loadé en Java").setGuildOnly(true).queue();
        jda.upsertCommand("gnou", "Incroyable blague 👀").setGuildOnly(true).queue();
    }
}

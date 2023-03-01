package fr.gamecreep.bot;

import fr.gamecreep.bot.commands.CommandIP;
import fr.gamecreep.bot.events.InteractionEventListener;
import fr.gamecreep.bot.events.MessageEventListener;
import fr.gamecreep.bot.events.ReadyEventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

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

        jda.getPresence().setActivity(Activity.watching("twitch.tv/funixgaming"));
    }
}

package fr.gamecreep.bot.events;

import fr.gamecreep.bot.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "gnou" :
                Commands.gnou.run(event);
                break;
            case "ip":
                event.replyEmbeds(EmbedCommands.ip.getEmbed().build()).queue();
                break;
            case "ping":
                EmbedCommands.ping.run(event);
                break;
            case "me":
                EmbedCommands.me.run(event);
                break;
            case "help":
                EmbedCommands.help.run(event);
        }

    }
}

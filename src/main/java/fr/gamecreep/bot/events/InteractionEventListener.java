package fr.gamecreep.bot.events;

import fr.gamecreep.bot.commands.Commands;
import fr.gamecreep.bot.commands.EmbedCommands;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Collection;
import java.util.Date;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "gnou" :
                event.reply(Commands.gnou.getMessage()).setEphemeral(Commands.gnou.getEphemeral()).queue();
                break;
            case "slashcommand":
                event.reply(Commands.slashcommand.getMessage()).setEphemeral(Commands.slashcommand.getEphemeral()).queue();
                break;
            case "ip":
                event.replyEmbeds(EmbedCommands.ip.getEmbed().build()).queue();
                break;
            case "ping":
                event.replyEmbeds(
                        EmbedCommands.ping.getEmbed()
                                // Je met tout ca la pcq ca requiert le JDA qui est inaccessible dans mon EmbedCommands... (Ouvre une issue ou une pull request si t'as une meilleure idée fufu)
                                .setFooter(event.getUser().getAsTag(), event.getUser().getAvatarUrl())
                                .setThumbnail(event.getJDA().getSelfUser().getAvatarUrl())
                                .setDescription(String.format("La latence de l'API est de %s ms :satellite_orbital: !", event.getJDA().getGatewayPing(), false))
                                .build()
                        ).queue();
                break;
            case "me":
                event.replyEmbeds(
                        EmbedCommands.me.getEmbed()
                                .setThumbnail(event.getUser().getAvatarUrl())
                                .setAuthor(event.getUser().getAsTag(), null, event.getUser().getAvatarUrl())
                                .addField("**Pseudo**", event.getUser().getAsTag(), false)
                                .addField("**Date de création**", "Pas encore fini", false)
                                .build()
                ).queue();
                break;
        }

    }
}

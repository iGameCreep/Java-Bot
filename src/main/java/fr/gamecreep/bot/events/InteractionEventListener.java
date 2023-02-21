package fr.gamecreep.bot.events;

import fr.gamecreep.bot.commands.Commands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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
        }

    }
}

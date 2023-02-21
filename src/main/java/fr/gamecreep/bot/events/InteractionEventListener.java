package fr.gamecreep.bot.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        switch (event.getName()) {
            case "gnou":
                event.reply("Alors c'est l'histoire d'un gnou qui se balade dans la savane et qui croise un autre groupe de gnou.\n" +
                        "L'autre groupe de gnou le voyant tout seul lui a donc demandé : \n" +
                        "Eh viens avec gnou :water_buffalo:").setEphemeral(true).queue();
                break;
        }

    }
}

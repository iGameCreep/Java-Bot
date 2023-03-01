package fr.gamecreep.bot.commands;

import fr.gamecreep.bot.commands.utils.BotCommand;
import lombok.NonNull;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandIP extends BotCommand {

    /*
        Principalement repris du FunixBot Twitch.
     */

    public CommandIP(String name, String description) {
        super("ip", "IP CMD");
    }

    @Override
    public void onUserCommand(@NonNull SlashCommandInteractionEvent interactionEvent) {
        interactionEvent.reply(String.format("Commande éxecutée : %s", interactionEvent.getName())).setEphemeral(true).queue();
    }
}

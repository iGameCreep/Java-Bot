package fr.gamecreep.bot.commands.utils;

import lombok.NonNull;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class BotCommand implements UserCommandEvent {

    /*
        Principalement repris du FunixBot Twitch.
     */

    private final String name;
    private final String description;

    public BotCommand(final String name, final String description) {
        this.name = name.toLowerCase();
        this.description = description;
    }
}
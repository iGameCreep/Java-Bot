package fr.gamecreep.bot.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot()) return;

        System.out.println(String.format("[ %s > %s ] >> %s : %s", event.getGuild().getName(), event.getChannel().getName(), event.getAuthor().getAsTag(), event.getMessage().getContentDisplay()));
    }
}

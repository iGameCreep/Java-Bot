package fr.gamecreep.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MessageEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot()) return;

        System.out.println(String.format("[ %s > %s ] >> %s : %s", event.getGuild().getName(), event.getChannel().getName(), event.getAuthor().getAsTag(), event.getMessage().getContentDisplay()));

        System.out.println(event.getMessage().getContentRaw().equals("!rolereact"));

        if (event.getMessage().getContentDisplay().equals("!rolereact") && event.getAuthor().getId().equals("696753471650660412")){
            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(new Color(44,175,255))
                    .setTitle("**Choix des rôles**")
                    .setDescription("Afin d'éviter de faire des tag everyone et here sur le discord, vous pouvez choisir vos rôles pour recevoir les notifications qui vous intéressent.\n" +
                            "Vous pouvez d'ailleurs ajouter et retirer votre rôle à tout moment.")
                    .addField("<:twitch:1076929000330498058>", "Les notifications Twitch", true)
                    .addField("<:youtube:1076929043154346047>", "Les notifications YouTube", true)
                    .addField("<:tiktok:1076929073516912733>", "Les notifications TikTok", true);
            Button twitchbtn = Button.primary("notif-twitch", Emoji.fromCustom(":twitch:", Long.parseLong("1076929000330498058"), false));
            Button youtubebtn = Button.primary("notif-youtube", Emoji.fromCustom(":youtube:", Long.parseLong("1076929043154346047"), false));
            Button tiktokbtn = Button.primary("notif-tiktok", Emoji.fromCustom(":tiktok:", Long.parseLong("1076929073516912733"), false));

            event.getChannel().sendMessageEmbeds(embed.build()).addActionRow(twitchbtn, youtubebtn, tiktokbtn).queue();
            event.getMessage().delete().queue();
        }
    }
}

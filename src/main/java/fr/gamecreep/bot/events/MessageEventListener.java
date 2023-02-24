package fr.gamecreep.bot.events;

import fr.gamecreep.bot.commands.CommandList;
import fr.gamecreep.bot.commands.Commands;
import fr.gamecreep.bot.commands.EmbedCommands;
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

        if (event.getMessage().getContentRaw().startsWith("!")) {
            String command = event.getMessage().getContentRaw().split(" ")[0].replace("!", "");

            for (CommandList cmdl : CommandList.values()) {
                if (cmdl.getName().equals(command)) {
                    for (EmbedCommands cmd : EmbedCommands.values()) {
                        if (cmd.getName().equals(command)) {
                            cmd.run(event, command);
                        }
                    }
                    for (Commands cmd : Commands.values()) {
                        if (cmd.getName().equals(command)) {
                            cmd.run(event);
                        }
                    }
                } else {
                    errcommand(event, command);
                }

            }
            setCount(0);
        }
    }

    private int Count = 0;

    public void errcommand(MessageReceivedEvent event, String command) {
        setCount((getCount() + 1));
        if (getCount() == CommandList.values().length) { // Command is invalid
            event.getChannel().sendMessage(String.format(":warning: La commande `%s` n'existe pas ! \n`!help` pour la liste des commandes.", command)).queue();
        }
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}

package fr.gamecreep.bot.commands;

import fr.gamecreep.bot.commands.CommandCategories;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.awt.Color;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

public enum EmbedCommands {

    ip(
            "ip",
            "Renvoi l'IP de connection et les informations du serveur Minecraft Pacifista !",
            CommandCategories.PACIFISTA.name(),
            new EmbedBuilder()
                    .setTitle("Pacifista Minecraft")
                    .setDescription("Serveur minecraft survie")
                    .setColor(new Color(44,175,255))
                    .addField("**Site Web**", "https://pacifista.fr", true)
                    .addField("**IP de connexion**", "play.pacifista.fr", true)
                    .addField("**Version**", "1.19.2", true),
            false
    ),

    ping(
            "ping",
            "Renvoi le ping du bot !",
            CommandCategories.INFORMATIONS.name(),
            new EmbedBuilder()
                    .setTitle("Pong :ping_pong: !")
                    .setColor(new Color(44,175,255))
                    .setTimestamp(Instant.now()),
            false
    ),

    me(
            "me",
            "Renvoi les infos principales de ton compte !",
            CommandCategories.INFORMATIONS.name(),
            new EmbedBuilder()
                    .setDescription("Voici donc les infos principales de ton compte.")
                    .setColor(new Color(44,175,255)),
            false
    ),

    help(
            "help",
            "Liste toute les commandes du bot !",
            CommandCategories.INFORMATIONS.name(),
            new EmbedBuilder()
                    .setColor(new Color(44,175,255))
                    .setDescription("Voici toute les commandes du bot !")
                    .setTimestamp(Instant.now()),
            false
    );



    private String name;
    private String description;
    private String category;
    private EmbedBuilder embed;
    private boolean ephemeral;

    EmbedCommands(String name, String description, String category, EmbedBuilder embed, boolean ephemeral) {
        this.name = name;
        this.embed = embed;
        this.description = description;
        this.category = category;
        this.ephemeral = ephemeral;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() { return category; }
    public EmbedBuilder getEmbed() {
        return embed;
    }
    public boolean getEphemeral() {
        return ephemeral;
    }

    public void run(SlashCommandInteractionEvent interaction) {

        switch (interaction.getName()) {
            case "me":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);
                me.embed.clearFields();
                me.embed
                        .setThumbnail(interaction.getUser().getAvatarUrl())
                        .setAuthor(interaction.getUser().getAsTag(), null, interaction.getUser().getAvatarUrl())
                        .addField("**Pseudo**", interaction.getUser().getAsTag(), false)
                        .addField("**Date de création**", interaction.getUser().getTimeCreated().format(formatter), false);
                break;
            case "ping":
                ping.embed.clearFields();
                ping.embed
                        .setFooter(interaction.getUser().getAsTag(), interaction.getUser().getAvatarUrl())
                        .setThumbnail(interaction.getJDA().getSelfUser().getAvatarUrl())
                        .setDescription(String.format("La latence de l'API est de %s ms :satellite_orbital: !", interaction.getJDA().getGatewayPing(), false));
                break;
            case "help":
                help.embed.clearFields();
                help.embed
                        .setThumbnail(interaction.getJDA().getSelfUser().getAvatarUrl())
                        .setAuthor(interaction.getJDA().getSelfUser().getAsTag(), null, interaction.getJDA().getSelfUser().getAvatarUrl())
                        .setFooter(interaction.getUser().getAsTag(), interaction.getUser().getAvatarUrl());

                Map<String, String> commands = new HashMap<>();

                for (CommandList command : CommandList.values()) {
                    commands.put(command.getName(), command.getDescription());
                }

                for (Map.Entry<String, String> cmd : commands.entrySet()) {
                    help.embed.addField(cmd.getKey(), cmd.getValue(), true);
                }
        }

        interaction.replyEmbeds(embed.build()).setEphemeral(ephemeral).queue();
    }

}

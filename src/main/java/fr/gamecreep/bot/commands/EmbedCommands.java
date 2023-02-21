package fr.gamecreep.bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import java.awt.Color;
import java.time.Instant;

public enum EmbedCommands {

    ip(
            "ip",
            "Renvoi l'IP de connection et les informations du serveur Minecraft Pacifista !",
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
            new EmbedBuilder()
                    .setTitle("Pong :ping_pong: !")
                    .setColor(new Color(44,175,255))
                    .setTimestamp(Instant.now()),
            false
    ),

    me(
            "me",
            "Renvoi les infos principales de ton compte !",
            new EmbedBuilder()
                    .setDescription("Voici donc les infos principales de ton compte.")
                    .setColor(new Color(44,175,255)),
            false
    );

    private String name;
    private EmbedBuilder embed;
    private String description;
    private boolean ephemeral;

    EmbedCommands(String name, String description, EmbedBuilder embed, boolean ephemeral) {
        this.name = name;
        this.embed = embed;
        this.description = description;
        this.ephemeral = ephemeral;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public EmbedBuilder getEmbed() {
        return embed;
    }

    public boolean getEphemeral() {
        return ephemeral;
    }

}

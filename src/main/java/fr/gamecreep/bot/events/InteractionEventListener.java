package fr.gamecreep.bot.events;

import fr.gamecreep.bot.commands.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InteractionEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        /*
            Le code viendra plus tard une fois la liste de commandes fonctionnelle
         */

    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        super.onButtonInteraction(event);

        String twitchroleid = "1075765623830884362";
        String youtuberoleid = "1076927052634140772";
        String tiktokroleid = "1076926853098508399";

        Role twitchrole = event.getGuild().getRoleById(twitchroleid);
        Role youtuberole = event.getGuild().getRoleById(youtuberoleid);
        Role tiktokrole = event.getGuild().getRoleById(tiktokroleid);

        switch (event.getInteraction().getComponentId()) {
            case "notif-twitch":
                Role role1 = hasrole(twitchroleid, event.getMember());
                giveRole(event, role1, twitchrole);
                break;
            case "notif-youtube":
                Role role2 = hasrole(youtuberoleid, event.getMember());
                giveRole(event, role2, youtuberole);
                break;
            case "notif-tiktok":
                Role role3 = hasrole(tiktokroleid, event.getMember());
                giveRole(event, role3, tiktokrole);
                break;
        }

    }

    public Role hasrole(String roleid, Member member) {
        Role role = member.getRoles().stream().filter(r -> r.getId().equals(roleid)).findFirst().orElse(null);
        if (role == null) return null;
        else return role;
    }

    public void giveRole(ButtonInteractionEvent event, Role role, Role roleselected) {
        if (role == null) {
            event.getGuild().addRoleToMember(event.getMember(), roleselected).queue();
            event.reply(String.format("Le rôle <@&%s> t'as été donné avec succès !", roleselected.getId())).setEphemeral(true).queue();
        } else {
            event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
            event.reply(String.format("Le rôle <@&%s> t'as été enlevé avec succès !", role.getId())).setEphemeral(true).queue();
        }
    }

}

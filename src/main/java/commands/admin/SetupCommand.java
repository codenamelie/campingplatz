package commands.admin;

import handler.RoleHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.Member;

public class SetupCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentDisplay().startsWith(".setup")) {
            String type = event.getMessage().getContentDisplay().toLowerCase().split("\\.setup ")[1];
            if(type.equalsIgnoreCase("staff")) {
                Embed embed = new Embed();
                embed.getEmbedBuilder().setDescription("**"+event.getGuild().getName()+"**'s Team\n\n");
                RoleHandler.getRoles("staff", event.getGuild()).forEach(role -> {
                    List<Member> roleMembers = event.getGuild().getMembersWithRoles(role);
                    embed.getEmbedBuilder().appendDescription(role.getAsMention() + " (" + roleMembers.size() + ")\n");
                    List<String> users = new ArrayList<>();
                    roleMembers.forEach(member -> {
                        users.add(member.getAsMention());
                    });
                    String s = roleMembers.size()>0 ? "» "+String.join(",", users) + "\n" : "» *Niemand hat diese Rolle*\n";
                    embed.getEmbedBuilder().appendDescription(s);
                    embed.getEmbedBuilder().appendDescription("\n");
                });
                embed.sendIn(event.getTextChannel());
            }
        }
    }
}
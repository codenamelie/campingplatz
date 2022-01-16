package commands.fun;

import handler.CustomDateFormatter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Embed;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class InfoCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if (event.getMessage().getContentDisplay().startsWith(".i")) {
            Embed embed = new Embed();
            Member member;
            try {
                member = event.getMessage().getMentionedMembers().get(0);
            }catch(IndexOutOfBoundsException e) {
                member = event.getMember();
            }
            List<String> roles = new ArrayList<>();
            member.getRoles().forEach(role->{
               roles.add(role.getAsMention());
            });
            embed.getEmbedBuilder().setThumbnail(member.getEffectiveAvatarUrl());
            String name = member.getUser().getName();
            String discrim = member.getUser().getDiscriminator();
            embed.getEmbedBuilder().setDescription(String.format("**" + name + "**#" + discrim + "'s Profil\n\n**Discord User seit**\n» " + CustomDateFormatter.newInstance().format(member.getTimeCreated()) + "\n\n**Servermitglied seit**\n» " + CustomDateFormatter.newInstance().format(member.getTimeJoined())));
            embed.getEmbedBuilder().addField("Rollen (" + roles.size() + ")", roles.size() > 0 ? String.join("," ,roles) : "Keine Rollen", false);
            embed.sendIn(event.getTextChannel());
        }
    }
}

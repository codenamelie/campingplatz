package commands.moderation;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

public class BanCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().equalsIgnoreCase("teuatheauthaeuthaeutheautheaut")) {
            Member member = null;
            String reason;
            Embed embed = new Embed();
            try {
                reason = null;
            } catch (IndexOutOfBoundsException e) {
                reason = "Kein Grund angegeben";
            }
            embed.getEmbedBuilder().setDescription("Du wurdest gebannt. Grund: " + reason + "\n[Entbannungsantrag](https://www.google.com/)");
            member.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.getEmbedBuilder().build()).queue();
            member.ban(0, reason);
        }
    }
}
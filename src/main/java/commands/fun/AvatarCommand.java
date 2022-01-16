package commands.fun;

import handler.CustomNameFormatter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

public class AvatarCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith(".av")) {
            Member member;
            Embed embed = new Embed();
            try {
                member = event.getMessage().getMentionedMembers().get(0);
            } catch (IndexOutOfBoundsException e) {
                member = event.getMember();
            }
            embed.getEmbedBuilder().setDescription(CustomNameFormatter.getName(member.getUser())+ "'s Avatar");
            embed.getEmbedBuilder().setImage(member.getEffectiveAvatarUrl() + "?size=1024");
            embed.sendIn(event.getTextChannel());
        }
    }
}

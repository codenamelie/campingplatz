package commands.fun;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Embed;

public class SlapCommand extends ListenerAdapter {

    final String gif = "https://media2.giphy.com/media/vxvNnIYFcYqEE/giphy.gif?cid=ecf05e478riknk2vq8n639zwmmfv55l5e4xyczmh1dkfys5r&rid=giphy.gif";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith(".slap")) {
            Embed embed = new Embed();
            try {
                Member member = event.getMessage().getMentionedMembers().get(0);
                embed = new Embed();
                embed.getEmbedBuilder().setDescription("> " + event.getMember().getAsMention() + " slapt dich, " + member.getAsMention());
                embed.getEmbedBuilder().setImage(gif);
            } catch (IndexOutOfBoundsException e) {
                embed.getEmbedBuilder().setDescription("Du musst einen User pingen");
            }
            embed.sendIn(event.getTextChannel());
        }
    }
}
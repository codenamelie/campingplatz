package commands.fun;

import handler.CustomNameFormatter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Embed;

public class BannerCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith(".banner")) {
            Member member;
            Embed embed = new Embed();
            try {
                member = event.getMessage().getMentionedMembers().get(0);
            } catch (IndexOutOfBoundsException e) {
                member = event.getMember();
            }
            String url = member.getUser().retrieveProfile().complete().getBannerUrl();
            if (url == "null") {
                embed.getEmbedBuilder().setImage(member.getUser().retrieveProfile().complete().getBannerUrl());
            } else {
                embed.getEmbedBuilder().setDescription(member.getAsMention() + " hat keinen Banner :(");
            }
            embed.getEmbedBuilder().setDescription(CustomNameFormatter.getName(member.getUser())+ "'s Banner");
            embed.sendIn(event.getTextChannel());
        }
    }
}
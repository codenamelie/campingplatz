package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Embed;

public class BannerCommand extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if(event.getName().equalsIgnoreCase("banner")) {
            event.deferReply().complete().deleteOriginal().queue();
            Member member;
            Embed embed = new Embed();
            try {
                member = event.getOptionsByName("user").get(0).getAsMember();
            }catch(IndexOutOfBoundsException e ){
                member = event.getMember();
            }
            String url = member.getUser().retrieveProfile().complete().getBannerUrl();
            if(url=="null") {
                embed.getEmbedBuilder().setImage(member.getUser().retrieveProfile().complete().getBannerUrl());
            }else {
                embed.getEmbedBuilder().setDescription(member.getAsMention() + " hat keinen Banner :(");
            }
            embed.sendIn(event.getTextChannel());
        }
    }
}
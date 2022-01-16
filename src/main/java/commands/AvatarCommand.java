package commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import utils.Embed;

public class AvatarCommand extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if(event.getName().equalsIgnoreCase("avatare")) {
            event.deferReply().complete().deleteOriginal().queue();
            Member member;
            Embed embed = new Embed();
            try {
                member = event.getOptionsByName("user").get(0).getAsMember();
            }catch(IndexOutOfBoundsException e ){
                member = event.getMember();
            }
            embed.getEmbedBuilder().setImage(member.getEffectiveAvatarUrl() + "?size=1024");
            embed.sendIn(event.getTextChannel());
        }
    }
}

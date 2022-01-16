package commands.moderation;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

public class KickCommand extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equalsIgnoreCase("kick")) {
            event.deferReply().complete().deleteOriginal().queue();
            Member member;
            Embed embed = new Embed();
            member = event.getOptionsByName("user").get(0).getAsMember();
            embed.getEmbedBuilder().setDescription(member.getAsMention() + " wurde gekickt");
            embed.sendIn(event.getTextChannel());
            try {
                member.kick(event.getOptionsByName("reason").get(0).getAsString()).queue();
            } catch (IndexOutOfBoundsException e) {
                member.kick().queue();
            }
        }
    }
}
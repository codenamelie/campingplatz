package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Embed;

public class BanCommand extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if(event.getName().equalsIgnoreCase("ban")) {
            event.deferReply().complete().deleteOriginal().queue();
            Member member = event.getOptionsByName("user").get(0).getAsMember();
            String reason;
            Embed embed = new Embed();
            try {
                reason = event.getOptionsByName("reason").get(0).getAsString();
            }catch(IndexOutOfBoundsException e) {
                reason = "Kein Grund angegeben";
            }
            embed.getEmbedBuilder().setDescription("Du wurdest gebannt. Grund: " + reason + "\n[Entbannungsantrag](https://www.google.com/)");
            member.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.getEmbedBuilder().build()).queue();
            member.ban(0, reason);
        }
    }
}
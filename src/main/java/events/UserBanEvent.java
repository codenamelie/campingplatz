package events;

import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

public class UserBanEvent extends ListenerAdapter {

    @Override
    public void onGuildBan(GuildBanEvent event) {
        Embed embed = new Embed();
        embed.getEmbedBuilder().setDescription("Du wurdest gebannt. \n\n[Appeal](https://www.google.com/)");
        event.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.getEmbedBuilder().build()).queue();
    }
}
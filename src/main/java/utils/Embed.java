package utils;

import main.Campingplatz;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Date;

public class Embed {

    private static SelfUser user = Campingplatz.getSelfUser();
    private EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor(user.getName(), null, user.getAvatarUrl()).setFooter(user.getName()).setTimestamp(new Date().toInstant());

    public Embed() {

    }

    public EmbedBuilder getEmbedBuilder() {
        return embedBuilder;
    }

    public void sendIn(TextChannel textChannel) {
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

}

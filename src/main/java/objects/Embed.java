package objects;

import main.Campingplatz;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.TextChannel;
import java.awt.*;
import java.io.File;
import java.util.Date;

public class Embed {

    private static final SelfUser user = Campingplatz.getSelfUser();
    private final EmbedBuilder embedBuilder = new EmbedBuilder()
            .setColor(Color.decode("0x061387"))
            .setAuthor(user.getName(), null, user.getAvatarUrl())
            .setFooter(user.getName())
            .setTimestamp(new Date().toInstant());
    private Message message;

    public Embed() {
    }

    public EmbedBuilder getEmbedBuilder() {
        return embedBuilder;
    }

    public void sendIn(TextChannel textChannel, File file) {
        message = textChannel.sendMessageEmbeds(embedBuilder.build()).addFile(file).complete();
    }

    public void sendIn(TextChannel textChannel) {
        message = textChannel.sendMessageEmbeds(embedBuilder.build()).complete();
    }

    public Message getMessage() {
        return message;
    }
}
package commands.fun;

import handler.CustomNameFormatter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;
import objects.LoveImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class ShipCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith(".ship ")) {
            try {
                Member t = event.getMessage().getMentionedMembers().get(0);
                int love = new Random().nextInt(100);
                LoveImage cImage = new LoveImage(love, event.getMember().getUser(), t.getUser());
                Embed embed = new Embed();
                embed.getEmbedBuilder().setDescription(CustomNameFormatter.getName(event.getMember().getUser()) + " shipt dich, " + CustomNameFormatter.getName(t.getUser()));
                embed.getEmbedBuilder().appendDescription("\n" + cImage.getMessage() + "");
                embed.getEmbedBuilder().setImage("attachment://saved.png");
                embed.sendIn(event.getTextChannel(), cImage.getFile());
                cImage.delete();
            } catch (IndexOutOfBoundsException e) {
                Embed embed = new Embed();
                embed.getEmbedBuilder().setDescription("Du musst einen User pingen");
                embed.sendIn(event.getTextChannel());
            }
        }
    }

    public byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }
}
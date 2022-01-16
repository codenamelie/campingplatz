package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import utils.Embed;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class ShipCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentDisplay().startsWith(".ship")) {
            try {
                Member t = event.getMessage().getMentionedMembers().get(0);
                int love = new Random().nextInt(100);
                CImage cImage = new CImage(love, event.getMember().getUser(), t.getUser());
                event.getChannel().sendFile(toByteArray(cImage.getImg(), "png"),"file.png").queue();
            }catch(IndexOutOfBoundsException | IOException e ){
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
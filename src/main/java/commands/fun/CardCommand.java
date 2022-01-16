package commands.fun;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import objects.CardImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CardCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentDisplay().startsWith(".card")) {
            try {
                Member t = event.getMessage().getMentionedMembers().get(0);
                CardImage card = new CardImage(t.getUser());
                event.getChannel().sendFile(toByteArray(card.getImg(), "png"), "file.png").queue();
            }catch (IndexOutOfBoundsException | IOException e) {

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

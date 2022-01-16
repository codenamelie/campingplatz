package commands;

import net.dv8tion.jda.api.entities.User;
import sun.misc.Launcher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class CImage {

    private BufferedImage img;

    public CImage(int love, User sender, User target) {
        try {
            img = ImageIO.read(new File("assets/background.png"));
            Graphics2D g = img.createGraphics();

            Font font = new Font("Arial", Font.PLAIN, 80);
            g.setFont(font);
            g.setPaint(Color.decode("0xff9cf7"));
            String text = String.valueOf(love);
            TextLayout textLayout = new TextLayout(text, g.getFont(),
                    g.getFontRenderContext());
            double textHeight = textLayout.getBounds().getHeight();
            double textWidth = textLayout.getBounds().getWidth();
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(text, 960 / 2 - (int) textWidth / 2,
                    320 / 2 + (int) textHeight / 2);
            String imgFormat = "png";

            g.drawImage(circle(getUserAvatar(sender)), 10, 10, null);
            g.drawImage(circle(getUserAvatar(target)), 650, 10, null);
            try {
                ImageIO.write(img, imgFormat, new File("new_image." + imgFormat));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getUserAvatar(User user) throws IOException {
        URLConnection connection = new URL(user.getAvatarUrl()+"?size=300" != null ? user.getAvatarUrl()+"?size=300" : user.getDefaultAvatarUrl()+"?size=300").openConnection();
        connection.setRequestProperty("User-Agent", "bot emily-bot");
        BufferedImage profileImg;
        try {
            profileImg = ImageIO.read(connection.getInputStream());
        } catch (Exception ignored) {
            profileImg = ImageIO.read(Objects.requireNonNull(Launcher.class.getClassLoader().getResource("default_profile.jpg")));
        }
        return profileImg;
    }

    public BufferedImage getImg() {
        return img;
    }

    private BufferedImage circle(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, 300, 300));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        return output;
    }
}
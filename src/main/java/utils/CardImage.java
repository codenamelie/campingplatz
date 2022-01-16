package utils;

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

public class CardImage {

    private BufferedImage img;

    public CardImage(User user) {
        try {
            img = ImageIO.read(new File("assets/card.png"));
            Graphics2D g = img.createGraphics();


            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")));


            for(Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
                System.out.println(font.getName());
            }

            Font font = new Font("Eufoniem One", Font.PLAIN, 40);
            g.setFont(font);
            g.setPaint(Color.decode("0xffc7c7"));
            String text = user.getAsTag();
            TextLayout textLayout = new TextLayout(text, g.getFont(),
                    g.getFontRenderContext());
              g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(text, 327,
                    183);
            g.drawImage(circle(getUserAvatar(user)), 40, 40, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getUserAvatar(User user) throws IOException {
        String size = "?size=256";
        URLConnection connection = new URL(user.getAvatarUrl() + size != null ? user.getAvatarUrl() + size : user.getDefaultAvatarUrl() + size).openConnection();
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

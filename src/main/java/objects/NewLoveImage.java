package objects;

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

public class NewLoveImage {

    private BufferedImage img;
    private File file;
    private String message;

    public NewLoveImage(int love, String url) {

        try {
            img = ImageIO.read(new File("assets/love.png"));
            Graphics2D g = img.createGraphics();
            this.message = LoveImageMessageHandler.get(love);
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
            g.drawImage(circle(getUserAvatar(url)), 10, 10, null);
            g.drawImage(circle(getUserAvatar(url)), 650, 10, null);
            //g.drawImage(circle(ImageIO.read(new File("assets/status.png"))), 220, 230, null);
            file = new File("saved.png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public File getFile() {
        return file;
    }

    public void delete() {
        file.delete();
    }

    private BufferedImage getUserAvatar(String s) throws IOException {
        URLConnection connection = new URL(s + "?size=300" != null ? s + "?size=300" : s + "?size=300").openConnection();
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

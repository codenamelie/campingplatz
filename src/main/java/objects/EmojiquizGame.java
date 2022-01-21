package objects;

import handler.EmojiquizHandler;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.Member;
import java.util.concurrent.TimeUnit;

public class EmojiquizGame {

    private Emojiquiz current;
    private TextChannel textChannel;

    public EmojiquizGame(TextChannel textChannel) {
        this.textChannel = textChannel;
        try {
            textChannel.purgeMessages(textChannel.getIterableHistory().complete()).get(0);
        }catch(IndexOutOfBoundsException e) {

        }
        Emojiquiz newQuiz = EmojiquizHandler.getNew();
        this.current = newQuiz;
        this.current.getEmbed().sendIn(textChannel);
    }

    public void getNew(Member guesser) {
        textChannel.purgeMessages(textChannel.getIterableHistory().complete()).get(0);
        Embed embed = new Embed();
        embed.getEmbedBuilder().setDescription(guesser.getAsMention() + " hat das Wort erraten und **" + current.getPoints() + "** " + (current.getPoints() > 1 ? "Punkte" : "Punkt") + " erhalten");
        embed.sendIn(textChannel);
        embed.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
        Emojiquiz newQuiz = EmojiquizHandler.getNew();
        this.current = newQuiz;
        this.current.getEmbed().sendIn(textChannel);
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public Emojiquiz getCurrent() {
        return current;
    }
}

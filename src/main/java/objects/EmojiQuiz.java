package objects;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class EmojiQuiz {

    private String answer;
    private List<String> emojis = new ArrayList<>();
    private Embed embed = new Embed();
    private String hint = "*Kein Tipp*";
    private EmojiQuizDifficulty difficulty;
    private int points;

    public EmojiQuiz(String answer, String... entries) {
        this.answer = answer;
        for (String emoji : entries) {
            emojis.add(emoji);
        }
        embed.getEmbedBuilder().setTitle("Emojiquiz");
        embed.getEmbedBuilder().setDescription("Emojis: " + String.join(" ", emojis));
        EmojiquizHandler.register(this);
    }

    public EmojiQuizDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EmojiQuizDifficulty difficulty) {
        embed.getEmbedBuilder().appendDescription("\n\nSchwierigkeitsgrad: " + difficulty.name());
        this.difficulty = difficulty;
        if (difficulty.equals(EmojiQuizDifficulty.Easy)) {
            this.points = 1;
        }else if (difficulty.equals(EmojiQuizDifficulty.Hard)) {
            this.points = 3;
        }else if (difficulty.equals(EmojiQuizDifficulty.Normal)) {
            this.points = 2;
        }
    }

    public int getPoints() {
        return points;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        embed.getEmbedBuilder().appendDescription("\nTipp: " + hint);
        this.hint = hint;
    }

    public Embed getEmbed() {
        return embed;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getEmojis() {
        return emojis;
    }
}

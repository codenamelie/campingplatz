package handler;

import enums.EmojiQuizDifficulty;
import objects.Emojiquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmojiquizHandler {

    private static Emojiquiz current;
    private static List<Emojiquiz> quizList = new ArrayList<>();

    public static void setCurrent(Emojiquiz emojiQuiz) {
        current = emojiQuiz;
    }

    public static Emojiquiz getCurrent() {
        return current;
    }

    public static void registerAll() {
        new Emojiquiz("Gitarrensolo", "\uD83C\uDFB8", "\uD83D\uDE4D\u200D♂️")
                .setDifficulty(EmojiQuizDifficulty.Hard);

        new Emojiquiz("Kugelschreiber", "\uD83C\uDFB1", "\uD83D\uDE31", "\uD83D\uDC3B")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Liebe", "❤️", "\uD83D\uDE18")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Lagerfeuer", "\uD83C\uDFD5️", "\uD83D\uDD25")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Silvester", "\uD83C\uDF86", "\uD83D\uDE80")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Butterbrot", "\uD83E\uDDC8", "\uD83C\uDF5E")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Hundeschule", "\uD83D\uDC36", "\uD83E\uDDD1\u200D\uD83C\uDFEB")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Gedankenblitz", "\uD83E\uDDE0", "\uD83C\uDF29️")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new Emojiquiz("Wasserwaage", "\uD83D\uDCA6", "⚖️")
                .setDifficulty(EmojiQuizDifficulty.Easy);

    }

    public static void register(Emojiquiz emojiQuiz) {
        quizList.add(emojiQuiz);
    }

    public static Emojiquiz getNew() {
        return quizList.get(new Random().nextInt(quizList.size()));
    }
}
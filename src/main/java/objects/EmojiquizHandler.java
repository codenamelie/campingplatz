package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmojiquizHandler {

    private static EmojiQuiz current;
    private static List<EmojiQuiz> quizList = new ArrayList<>();

    public static void setCurrent(EmojiQuiz emojiQuiz) {
        current = emojiQuiz;
    }

    public static EmojiQuiz getCurrent() {
        return current;
    }

    public static void registerAll() {
        new EmojiQuiz("Gitarrensolo", "\uD83C\uDFB8", "\uD83D\uDE4D\u200D♂️")
                .setDifficulty(EmojiQuizDifficulty.Hard);

        new EmojiQuiz("Kugelschreiber", "\uD83C\uDFB1", "\uD83D\uDE31", "\uD83D\uDC3B")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Liebe", "❤️", "\uD83D\uDE18")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Lagerfeuer", "\uD83C\uDFD5️", "\uD83D\uDD25")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Silvester", "\uD83C\uDF86", "\uD83D\uDE80")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Butterbrot", "\uD83E\uDDC8", "\uD83C\uDF5E")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Hundeschule", "\uD83D\uDC36", "\uD83E\uDDD1\u200D\uD83C\uDFEB")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Gedankenblitz", "\uD83E\uDDE0", "\uD83C\uDF29️")
                .setDifficulty(EmojiQuizDifficulty.Easy);

        new EmojiQuiz("Wasserwaage", "\uD83D\uDCA6", "⚖️")
                .setDifficulty(EmojiQuizDifficulty.Easy);




    }

    public static void register(EmojiQuiz emojiQuiz) {
        quizList.add(emojiQuiz);
    }

    public static EmojiQuiz getNew() {
        return quizList.get(new Random().nextInt(quizList.size()));
    }
}
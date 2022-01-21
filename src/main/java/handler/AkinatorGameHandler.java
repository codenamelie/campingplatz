package handler;

import objects.AkinatorGame;
import net.dv8tion.jda.api.entities.Member;

import java.util.HashMap;

public class AkinatorGameHandler {

    public static HashMap<Member, AkinatorGame> games = new HashMap<Member, AkinatorGame>();

    public static AkinatorGame getGame(Member member) {
        return games.get(member);
    }

    public static HashMap<Member, AkinatorGame> getGames() {
        return games;
    }

    public static void register(AkinatorGame game) {
        games.put(game.getMember(), game);
    }

    public static boolean hasUserAkiGame(Member member) {
        return games.containsKey(member);
    }

    public static void remove(Member member) {
        games.remove(member);
    }

}

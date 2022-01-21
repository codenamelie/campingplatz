package objects;

import com.markozajc.akiwrapper.Akiwrapper;
import com.markozajc.akiwrapper.AkiwrapperBuilder;
import com.markozajc.akiwrapper.core.entities.Server;
import com.markozajc.akiwrapper.core.exceptions.ServerNotFoundException;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class AkinatorGame {

    private TextChannel textChannel;
    private Member member;
    private Akiwrapper akiwrapper;
    private int state = 0;
    private boolean done = false;

    public AkinatorGame(Member member, TextChannel textChannel) {
        this.member = member;
        this.textChannel = textChannel;
        try {
            akiwrapper = new AkiwrapperBuilder()
                    .setLanguage(Server.Language.GERMAN)
                    .build();
        } catch (ServerNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDone() {
        done = true;
    }

    public int getState() {
        return state;
    }

    public Akiwrapper getAkiwrapper() {
        return akiwrapper;
    }

    public Member getMember() {
        return member;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }
}

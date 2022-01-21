package cache;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import objects.EmojiquizGame;

import java.util.stream.Collectors;

public class Cache {

    private EmojiquizGame emojiquizGame;

    private Guild guild;

    public Cache(Guild guild) {
        this.guild = guild;
        this.emojiquizGame = new EmojiquizGame(getByName("emojiquiz"));
    }

    private TextChannel getByName(String string) {
        try {
            return guild.getTextChannels().stream().filter(x -> x.getName().contains(string)).collect(Collectors.toList()).get(0);
        }catch(IndexOutOfBoundsException e) {

        }
        return null;
    }

    public EmojiquizGame getEmojiquizGame() {
        return emojiquizGame;
    }

}

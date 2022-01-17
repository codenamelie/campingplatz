package cache;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class Cache {

    private TextChannel emojiquiz;

    public Cache(Guild guild) {
        emojiquiz = guild.getTextChannelById("932387814711111710");
    }

    public TextChannel getEmojiquiz() {
        return emojiquiz;
    }

}

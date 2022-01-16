package events;

import cache.Cache;
import main.Campingplatz;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        Campingplatz.setCache(new Cache(event.getGuild()));
        Campingplatz.getPresence().setActivity(Activity.watching("dem Lagerfeuer \uD83C\uDFD5️ zu"));

    }
}

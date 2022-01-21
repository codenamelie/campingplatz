package events;

import cache.Cache;
import main.Campingplatz;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import handler.EmojiquizHandler;

public class ReadyEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        EmojiquizHandler.registerAll();
        Cache cache = new Cache(event.getGuild());
        Campingplatz.setCache(cache);
        Campingplatz.getPresence().setActivity(Activity.watching("dem Lagerfeuer \uD83C\uDFD5️ zu"));
    }
}

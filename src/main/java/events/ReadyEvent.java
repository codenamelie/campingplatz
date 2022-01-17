package events;

import cache.Cache;
import main.Campingplatz;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.EmojiQuiz;
import objects.EmojiquizHandler;

public class ReadyEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        EmojiquizHandler.registerAll();
        Campingplatz.setCache(new Cache(event.getGuild()));
        Campingplatz.getPresence().setActivity(Activity.watching("dem Lagerfeuer \uD83C\uDFD5️ zu"));

        event.getGuild().getTextChannels().forEach(textChannel -> {
            String n = textChannel.getName().replaceAll("》\uD83D\uDC95", "");
            if(!textChannel.getName().equalsIgnoreCase(n))
            textChannel.getManager().setName("》\uD83D\uDC95" + n).queue();
        });

        EmojiQuiz first = EmojiquizHandler.getNew();
        EmojiquizHandler.setCurrent(first);
        first.getEmbed().sendIn(Campingplatz.getCache().getEmojiquiz());
    }
}

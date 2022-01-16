package events;

import main.Campingplatz;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Campingplatz.getCache().addMessageCount();
    }
}

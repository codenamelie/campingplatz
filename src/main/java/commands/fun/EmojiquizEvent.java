package commands.fun;

import main.Campingplatz;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;
import objects.Emojiquiz;
import handler.EmojiquizHandler;
import objects.EmojiquizGame;

import java.util.concurrent.TimeUnit;

public class EmojiquizEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getTextChannel().getId().equalsIgnoreCase(Campingplatz.getCache().getEmojiquizGame().getTextChannel().getId())) {
            if(event.getMember().getUser().isBot()) {
                return;
            }
            EmojiquizGame game = Campingplatz.getCache().getEmojiquizGame();
            Emojiquiz currentQuiz = game.getCurrent();
            if(event.getMessage().getContentDisplay().equalsIgnoreCase(currentQuiz.getAnswer())) {
                currentQuiz.getEmbed().getMessage().delete().queue();
                event.getTextChannel().getHistory().getRetrievedHistory().forEach(message -> {
                    try {
                        message.delete().complete();
                    }catch(ErrorResponseException e) {

                    }
                });
                game.getNew(event.getMember());
            }
        }
    }
}

package commands.fun;

import main.Campingplatz;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;
import objects.EmojiQuiz;
import objects.EmojiquizHandler;

import java.util.concurrent.TimeUnit;

public class EmojiquizEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getTextChannel().getId().equalsIgnoreCase(Campingplatz.getCache().getEmojiquiz().getId())) {
            if(event.getMember().getUser().isBot()) {
                return;
            }
            EmojiQuiz current = EmojiquizHandler.getCurrent();
            if(event.getMessage().getContentDisplay().equalsIgnoreCase(current.getAnswer())) {
                current.getEmbed().getMessage().delete().queue();
                event.getTextChannel().getHistory().retrievePast(100).complete().forEach(message -> {
                    try {
                        message.delete().complete();
                    }catch(ErrorResponseException e) {

                    }
                });
                String punkte = current.getPoints()>1 ? "Punkte" : "Punkt";
                Embed embed = new Embed();
                embed.getEmbedBuilder().setDescription(event.getMember().getAsMention() + " hat das Wort erraten und **" + current.getPoints() + "** " + punkte + " erhalten");
                embed.sendIn(event.getTextChannel());
                embed.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
                EmojiQuiz quiz = EmojiquizHandler.getNew();
                quiz.getEmbed().sendIn(event.getTextChannel());
                EmojiquizHandler.setCurrent(quiz);
            }
        }
    }
}

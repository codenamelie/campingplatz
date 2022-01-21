package commands.fun;

import com.markozajc.akiwrapper.Akiwrapper;
import com.markozajc.akiwrapper.AkiwrapperBuilder;
import com.markozajc.akiwrapper.core.entities.Guess;
import com.markozajc.akiwrapper.core.entities.Server;
import com.markozajc.akiwrapper.core.exceptions.ServerNotFoundException;
import handler.AkinatorGameHandler;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import objects.AkinatorGame;
import objects.Embed;

import java.awt.*;
import java.util.HashMap;

public class AkiCommand extends ListenerAdapter {

    private static HashMap<String, Akiwrapper.Answer> answers = new HashMap<>();

    static {
        answers.put("p", Akiwrapper.Answer.PROBABLY);
        answers.put("y", Akiwrapper.Answer.YES);
        answers.put("n", Akiwrapper.Answer.NO);
        answers.put("i", Akiwrapper.Answer.DONT_KNOW);
        answers.put("pn", Akiwrapper.Answer.PROBABLY_NOT);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentDisplay().equalsIgnoreCase(".aki ")) {
            if(!AkinatorGameHandler.hasUserAkiGame(event.getMember())) {
                AkinatorGame game = new AkinatorGame(event.getMember(), event.getTextChannel());
                AkinatorGameHandler.register(game);
                Embed embed = new Embed();
                embed.getEmbedBuilder().setColor(Color.decode("0xfcffa8"));
                embed.getEmbedBuilder().setDescription("**Frage " + game.getAkiwrapper().getCurrentQuestion().getStep() + "** (" + game.getAkiwrapper().getCurrentQuestion().getProgression() + "%)");
                embed.getEmbedBuilder().appendDescription("\n**" + game.getAkiwrapper().getCurrentQuestion().getQuestion() + "**");
                embed.getEmbedBuilder().appendDescription("\n[ja (**y**) / nein (**n**) / weiß ich nicht (**i**) / wahrscheinlich (**p**) / wahrscheinlich nicht (**pn**)] [zurück (**b**)]");
                embed.sendIn(event.getTextChannel());
                return;
            }
        }

        if (AkinatorGameHandler.hasUserAkiGame(event.getMember())) {
            AkinatorGame game = AkinatorGameHandler.getGame(event.getMember());
            if (event.getTextChannel().equals(game.getTextChannel())) {
                if(game.getAkiwrapper().getCurrentQuestion().getProgression() > 85.00) {
                    Guess g = game.getAkiwrapper().getGuesses().get(0);
                    Embed embed = new Embed();
                    embed.getEmbedBuilder().setColor(Color.decode("0xfcffa8"));
                    embed.getEmbedBuilder().setDescription("Ich denke an **" + g.getName() + "**");
                    embed.getEmbedBuilder().appendDescription("\n" + g.getDescription());
                    embed.getEmbedBuilder().appendDescription("\nRanking: #" + g.getId());
                    if(g.getImage() != null) {
                        embed.getEmbedBuilder().setImage(g.getImage().toString());
                    }
                    embed.sendIn(event.getTextChannel());
                    AkinatorGameHandler.remove(event.getMember());
                    return;
                }
                if(event.getMessage().getContentDisplay().equalsIgnoreCase("b")) {
                    game.getAkiwrapper().undoAnswer();
                    Embed embed = new Embed();
                    embed.getEmbedBuilder().setColor(Color.decode("0xfcffa8"));
                    embed.getEmbedBuilder().setDescription("**Frage " + game.getAkiwrapper().getCurrentQuestion().getStep() + "** (" + game.getAkiwrapper().getCurrentQuestion().getProgression() + "%)");
                    embed.getEmbedBuilder().appendDescription("\n**" + game.getAkiwrapper().getCurrentQuestion().getQuestion() + "**");
                    embed.getEmbedBuilder().appendDescription("\n[ja (**y**) / nein (**n**) / weiß ich nicht (**i**) / wahrscheinlich (**p**) / wahrscheinlich nicht (**pn**)] [zurück (**b**)]");
                    embed.sendIn(event.getTextChannel());
                    return;
                }
                game.getAkiwrapper().answerCurrentQuestion(answers.get(event.getMessage().getContentDisplay().toLowerCase()));
                Embed embed = new Embed();
                embed.getEmbedBuilder().setColor(Color.decode("0xfcffa8"));
                embed.getEmbedBuilder().setDescription("**Frage " + game.getAkiwrapper().getCurrentQuestion().getStep() + "** (" + game.getAkiwrapper().getCurrentQuestion().getProgression() + "%)");
                embed.getEmbedBuilder().appendDescription("\n**" + game.getAkiwrapper().getCurrentQuestion().getQuestion() + "**");
                embed.getEmbedBuilder().appendDescription("\n[ja (**y**) / nein (**n**) / weiß ich nicht (**i**) / wahrscheinlich (**p**) / wahrscheinlich nicht (**pn**)] [zurück (**b**)]");
                embed.sendIn(event.getTextChannel());
            }
        }
    }
}
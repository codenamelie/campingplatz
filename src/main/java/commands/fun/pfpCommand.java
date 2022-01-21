package commands.fun;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.Embed;

import java.util.concurrent.ExecutionException;

public class pfpCommand extends ListenerAdapter {

    private IGClient client;

    {
        try {
            client = IGClient.builder().username("ooo").password("yourpass").simulatedLogin();
        } catch (IGLoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith(".ig ")) {
            String n = event.getMessage().getContentDisplay().split(".ig ")[1];
            Embed embed = new Embed();
            try {
                UsersSearchResponse.User user = client.actions().search().searchUser(n).get().getUsers().get(0);
                embed.getEmbedBuilder().appendDescription("**@" + user.getUsername() + "**");
                embed.getEmbedBuilder().appendDescription("\n" + user.getFull_name());
                embed.getEmbedBuilder().setImage(user.getProfile_pic_url());
                embed.sendIn(event.getTextChannel());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

package cache;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.stream.Collectors;

public class Cache {

    private VoiceChannel members;
    private VoiceChannel bots;
    private VoiceChannel messageCountChannel;
    private int messageCount = 0;

    public Cache(Guild guild) {
        members = guild.getVoiceChannelById("932341919948865536");
        bots = guild.getVoiceChannelById("932342358132002876");
        messageCountChannel = guild.getVoiceChannelById("932344151213760623");
        members.getManager().setName("• Mitglieder: " + guild.getMembers().size() + "/" + guild.getMaxMembers()).queue();
        bots.getManager().setName("• Bots: " + guild.getMembers().stream().filter(x->x.getUser().isBot()).collect(Collectors.toList()).size()).queue();
        messageCountChannel.getManager().setName("• Nachrichten: " + messageCount).queue();
    }

    public void addMessageCount() {
        messageCount++;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public VoiceChannel getMessageCountChannel() {
        return messageCountChannel;
    }
}

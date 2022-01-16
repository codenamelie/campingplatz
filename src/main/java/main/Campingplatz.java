package main;

import commands.AvatarCommand;
import commands.BannerCommand;
import events.ReadyEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Campingplatz {

    private static final String token = "OTMyMDYxNTEwNTg5NjQwNzQ1.YeNf7A.Fyox3hmOSaFZ4eZvHRohSV4kaeY";
    private static SelfUser selfUser;
    private static JDA jda;

    public static void main(String[] args) throws LoginException {

        jda = JDABuilder.createDefault(token)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableIntents(GatewayIntent.GUILD_EMOJIS)
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .setStatus(OnlineStatus.ONLINE)
                .setAutoReconnect(true)
                .addEventListeners(new ReadyEvent())
                .addEventListeners(new AvatarCommand())
                .addEventListeners(new BannerCommand())
                .build();
        selfUser=jda.getSelfUser();
    }

    public static SelfUser getSelfUser() {
        return selfUser;
    }

}
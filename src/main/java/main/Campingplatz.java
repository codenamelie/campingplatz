package main;

import commands.*;
import events.ReadyEvent;
import events.UserBanEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.Random;

public class Campingplatz {

    private static final String token = "OTMyMDYxNTEwNTg5NjQwNzQ1.YeNf7A.PJ95ZmgH8XBVB1pvr-YUp4wqyiQ";
    private static SelfUser selfUser;
    private static JDA jda;

    public static void main(String[] args) throws LoginException {
        bot();
    }

    private static void bot() throws LoginException {
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
                .addEventListeners(new ShipCommand())
                .addEventListeners(new BanCommand())
                .addEventListeners(new AvatarCommand())
                .addEventListeners(new SlapCommand())
                .addEventListeners(new UserBanEvent())
                .addEventListeners(new BannerCommand())
                .build();
        selfUser=jda.getSelfUser();
    }

    public static SelfUser getSelfUser() {
        return selfUser;
    }

}
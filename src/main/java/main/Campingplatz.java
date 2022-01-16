package main;

import cache.Cache;
import commands.fun.*;
import commands.moderation.BanCommand;
import events.MessageEvent;
import events.ReadyEvent;
import events.UserBanEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Campingplatz {

    private static final String token = "";
    private static SelfUser selfUser;
    private static JDA jda;
    private static Cache cache;

    public static void main(String[] args) throws LoginException {
        bot();
    }

    public static void setCache(Cache c) {
        cache = c;
    }

    public static Cache getCache() {
        return cache;
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
                .setStatus(OnlineStatus.IDLE)
                .setAutoReconnect(true)
                .addEventListeners(new ReadyEvent())
                .addEventListeners(new ShipCommand())
                .addEventListeners(new MessageEvent())
                .addEventListeners(new BanCommand())
                .addEventListeners(new AvatarCommand())
                .addEventListeners(new SlapCommand())
                .addEventListeners(new UserBanEvent())
                .addEventListeners(new InfoCommand())
                .addEventListeners(new BannerCommand())
                .addEventListeners(new CardCommand())
                .build();
        selfUser = jda.getSelfUser();
    }

    public static Presence getPresence( ){
        return jda.getPresence();
    }

    public static SelfUser getSelfUser() {
        return selfUser;
    }

}
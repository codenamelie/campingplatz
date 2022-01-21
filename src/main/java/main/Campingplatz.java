package main;

import cache.Cache;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequest;
import commands.admin.SetupCommand;
import commands.fun.*;
import commands.moderation.BanCommand;
import events.ReadyEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.Map;

public class Campingplatz {

    private static final String token = "OTMyMDYxNTEwNTg5NjQwNzQ1.YeNf7A.UTcAzvkx811OLBWxyGR3Vr5QcbU";
    private static SelfUser selfUser;
    private static JDA jda;
    private static Cache cache;

    public static void main(String[] args) throws LoginException {
        //new NewLoveImage(100, "https://cdn.discordapp.com/avatars/703751413305573438/c49e5b4298afdee58b0761e5758698df.png");
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
                .addEventListeners(new AkiCommand())
                //.addEventListeners(new pfpCommand())
                .addEventListeners(new ShipCommand())
                .addEventListeners(new BanCommand())
                .addEventListeners(new AvatarCommand())
                .addEventListeners(new SlapCommand())
                .addEventListeners(new InfoCommand())
                .addEventListeners(new BannerCommand())
                .addEventListeners(new CardCommand())
                .addEventListeners(new SetupCommand())
                .addEventListeners(new EmojiquizEvent())
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
package events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class ReadyEvent extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        final Guild g = event.getGuild();
        final CommandData avatar = new CommandData("avatar", "Avatar")
                .addOption(OptionType.USER, "user", "User");

        final CommandData banner = new CommandData("banner", "Banner")
                .addOption(OptionType.USER, "user", "User");

        final CommandData kick = new CommandData("kick", "Kick")
                .addOption(OptionType.USER, "user", "User", true)
                .addOption(OptionType.STRING, "reason", "Grund", false);

        g.upsertCommand(avatar).queue();
        g.upsertCommand(banner).queue();
        g.upsertCommand(kick).queue();
    }
}

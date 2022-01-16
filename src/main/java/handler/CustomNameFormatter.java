package handler;

import net.dv8tion.jda.api.entities.User;

public class CustomNameFormatter {

    public static String getName(User user) {
        return "**"+user.getName() + "**#" + user.getDiscriminator();
    }
}

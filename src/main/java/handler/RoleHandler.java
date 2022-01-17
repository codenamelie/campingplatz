package handler;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleHandler {

    public static List<Role> getRoles(String spacer, Guild guild) {
        List<Role> spacers = guild.getRolesByName("⎼⎼⎼⎼⎼ " + spacer + " ⎼⎼⎼⎼⎼", true);
        List<Role> roles = new ArrayList<>();
        for (Role role : guild.getRoles()) {
            if(role.getPosition()>spacers.get(1).getPosition()) {
                if(role.getPosition()<spacers.get(0).getPosition()) {
                    roles.add(role);
                }
            }
        }
        return roles;
    }
}
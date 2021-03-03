package com.github.groomon.gslib.commandez;

import com.github.groomon.commandez.Sender;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class BukkitSender implements Sender {

    private final CommandSender sender;

    public BukkitSender(@NotNull CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    @Override
    public boolean hasPermission(@NotNull String perm) {
        if(perm.equals("")) return true;
        return sender.hasPermission(perm);
    }

    @Override
    public void sendMissingPermissionMessage(@NotNull String msg) {
        sender.sendMessage(msg);
    }

    @Override
    public void sendErrorMessage(@NotNull String msg) {
        sender.sendMessage(msg);
    }
}

package com.github.groomon.gslib.commandez;

import com.github.groomon.commandez.CommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class BukkitCommandManager extends CommandManager implements TabExecutor {

    private final JavaPlugin plugin;

    public BukkitCommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void logInfo(@NotNull String msg) {
        plugin.getLogger().info(msg);
    }

    @Override
    protected void logWarning(@NotNull String msg) {
        plugin.getLogger().warning(msg);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> pathList = new ArrayList<>();
        pathList.add(label);
        pathList.addAll(Arrays.asList(args));

        return executeCommand(new BukkitSender(sender), pathList.toArray(new String[0]));
    }

    //TODO implement
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}

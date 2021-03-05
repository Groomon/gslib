package com.github.groomon.gslib;

import org.bukkit.plugin.java.JavaPlugin;

public class Gslib extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("gslib successfully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("gslib successfully disabled");
    }
}

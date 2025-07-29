package com.steve.steveSkyLobby;

import com.steve.steveSkyLobby.event.TpServerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SteveSkyLobby extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("SteveSkyLobby is loaded!! :)");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        TpServerEvent tpServerEvent = new TpServerEvent(this);

        getServer().getPluginManager().registerEvents(tpServerEvent, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

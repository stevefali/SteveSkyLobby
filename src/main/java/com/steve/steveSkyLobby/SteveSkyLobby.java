package com.steve.steveSkyLobby;

import com.steve.steveSkyLobby.commands.BiomeCommand;
import com.steve.steveSkyLobby.commands.ShowBiomeCommand;
import com.steve.steveSkyLobby.event.BlockEvent;
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
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);

        getCommand("biomeset").setExecutor(new BiomeCommand());
        getCommand("showBiome").setExecutor(new ShowBiomeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

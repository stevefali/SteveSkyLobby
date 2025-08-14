package com.steve.steveSkyLobby;

import com.fastasyncworldedit.core.Fawe;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.steve.steveSkyLobby.commands.*;
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
        getServer().getPluginManager().registerEvents(new BlockEvent(this), this);

        getCommand("biomeset").setExecutor(new BiomeCommand());
        getCommand("showBiome").setExecutor(new ShowBiomeCommand());
        getCommand("makeWorldCopy").setExecutor(new MakeWorldCommand(this));
        getCommand("sendPlayer").setExecutor(new SendPlayerCommand(this));
        getCommand("setupbarriers").setExecutor(new SetupBarriersCommand(getServer()));
        getCommand("removebarrier").setExecutor(new RemoveBarrierCommand(getServer()));
        getCommand("setIsland").setExecutor(new SetIslandCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

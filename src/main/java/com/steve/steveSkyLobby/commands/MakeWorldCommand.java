package com.steve.steveSkyLobby.commands;

import com.steve.steveSkyLobby.SteveSkyLobby;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MakeWorldCommand implements CommandExecutor {

    private SteveSkyLobby plugin;

    public MakeWorldCommand(SteveSkyLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        World masterVoidWorld = plugin.getServer().getWorld("voidBuildingWorld");

        WorldCreator worldCreator = new WorldCreator("nwTestVoidWorld2");

        try {
            World testWorld = worldCreator.copy(masterVoidWorld).createWorld();
        } catch (Exception e) {
            System.out.println("Error creating world from copy!");
            e.printStackTrace();
        }

        return true;
    }

}

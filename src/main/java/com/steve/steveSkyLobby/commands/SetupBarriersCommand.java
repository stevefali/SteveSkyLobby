package com.steve.steveSkyLobby.commands;

import com.steve.steveSkyLobby.worldoperation.CuboidOperation;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetupBarriersCommand implements CommandExecutor, TabCompleter {

    private final Server server;

    public SetupBarriersCommand(Server server) {
        this.server = server;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1) {
            sender.sendMessage("Please specify a world");
            return false;
        }
        if (args.length == 1) {
            World world = server.getWorld(args[0]);
            if (world == null) {
                sender.sendMessage("Error: can't find world named " + args[0]);
                return false;
            }
            int startY = -64;
            int endY = 319;

            setBarrierBlocks(new Vector(-51, startY, -150), new Vector(-51, endY, 150), world);
            setBarrierBlocks(new Vector(51, startY, -150), new Vector(51, endY, 150), world);
            setBarrierBlocks(new Vector(-150, startY, -51), new Vector(150, endY, -51), world);
            setBarrierBlocks(new Vector(-150, startY, 51), new Vector(150, endY, 51), world);

            if (sender instanceof Player) {
                sender.sendMessage("§aCreated barriers in " + world.getName());
            } else {
                System.out.println("§aCreated barriers in " + world.getName());
            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> worldNames = server.getWorlds().stream().map(WorldInfo::getName).toList();
            return worldNames;
        }

        return List.of();
    }


    private void setBarrierBlocks(Vector start, Vector end, World world) {
        CuboidOperation.performCuboidSectionOperation(start, end,
                (blockPosition) -> world.getBlockAt(blockPosition.getBlockX(),
                                blockPosition.getBlockY(),
                                blockPosition.getBlockZ())
                        .setType(Material.ORANGE_WOOL));
    }
}

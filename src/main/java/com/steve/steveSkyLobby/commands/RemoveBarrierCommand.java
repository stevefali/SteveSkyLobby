package com.steve.steveSkyLobby.commands;

import com.steve.steveSkyLobby.worldoperation.CuboidOperation;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RemoveBarrierCommand implements CommandExecutor, TabCompleter {

    private final Server server;

    public RemoveBarrierCommand(Server server) {
        this.server = server;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length < 2) {
            sender.sendMessage("§ePlease specify a world and a section number");
            return false;
        }
        World world = server.getWorld(args[0]);
        if (world == null) {
            sender.sendMessage("§eError: can't find world named " + args[0]);
            return false;
        }
        try {
            int section = Integer.parseInt(args[1]);
            if (section < 2 || section > 9) {
                sender.sendMessage("§ePlease specify a section number between 2 and 9");
                return false;
            }
            int startY = -64;
            int endY = 319;
            switch (section) {
                case 2: {
                    removeBarrier(new Vector(-50, startY, -51), new Vector(50, endY, -51), world);
                    break;
                }
                case 3: {
                    removeBarrier(new Vector(-51, startY, -150), new Vector(-51, endY, -51), world);
                    break;
                }
                case 4: {
                    removeBarrier(new Vector(-150, startY, -51), new Vector(-51, endY, -51), world);
                    removeBarrier(new Vector(-51, startY, -51), new Vector(-51, endY, 50), world);
                    break;
                }
                case 5: {
                    removeBarrier(new Vector(-150, startY, 51), new Vector(-51, endY, 51), world);
                    break;
                }
                case 6: {
                    removeBarrier(new Vector(-51, startY, 51), new Vector(-51, endY, 150), world);
                    removeBarrier(new Vector(-51, startY, 51), new Vector(50, endY, 51), world);
                    break;
                }
                case 7: {
                    removeBarrier(new Vector(51, startY, 51), new Vector(51, endY, 150), world);
                    break;
                }
                case 8: {
                    removeBarrier(new Vector(51, startY, -50), new Vector(51, endY, 51), world);
                    removeBarrier(new Vector(51, startY, 51), new Vector(150, endY, 51), world);
                    break;
                }
                case 9: {
                    removeBarrier(new Vector(51, startY, -150), new Vector(51, endY, -51), world);
                    removeBarrier(new Vector(51, startY, -51), new Vector(150, endY, -51), world);
                    break;
                }
                default:
                    sender.sendMessage("§ePlease specify a section number between 2 and 9");
            }
            sender.sendMessage("§aRemoved barriers for section " + section);
        } catch (NumberFormatException e) {
            sender.sendMessage("§ePlease specify a section number between 2 and 9");
            return false;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> worldNames = server.getWorlds().stream().map(WorldInfo::getName).toList();
            return worldNames;
        }
        if (args.length == 2) {
            List<String> sections = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9");
            return sections;
        }
        return List.of();
    }

    private void removeBarrier(Vector start, Vector end, World world) {
        CuboidOperation.performCuboidSectionOperation(start, end,
                (block) -> world.getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR));
    }
}

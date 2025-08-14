package com.steve.steveSkyLobby.commands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class SetIslandCommand implements CommandExecutor, TabCompleter {

    private final Server server;
    private final Plugin plugin;

    public SetIslandCommand(Plugin plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
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
            if (section < 1 || section > 9) {
                sender.sendMessage("§ePlease specify a section number between 1 and 9");
                return false;
            }

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                sender.sendMessage("§aTestCommand!");
            });


//            EditSession editSession = WorldEdit.getInstance().newEditSession((com.sk89q.worldedit.world.World) world);


            /*switch (section) {
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {


                    break;
                }
                case 5: {

                    break;
                }
                case 6: {


                    break;
                }
                case 7: {

                    break;
                }
                case 8: {

                    break;
                }
                case 9: {

                    break;
                }
                default:
                    sender.sendMessage("§ePlease specify a section number between 1 and 9");
            }*/
//            sender.sendMessage("§aPlaced island for section " + section);
        } catch (NumberFormatException e) {
            sender.sendMessage("§ePlease specify a section number between 1 and 9");
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
            List<String> sections = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
            return sections;
        }
        return List.of();
    }
}

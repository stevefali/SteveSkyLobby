package com.steve.steveSkyLobby.commands;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ShowBiomeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command should be run in-game");
            return true;
        }

        Player senda = (Player) sender;
        World world = senda.getWorld();

        if (world.getName().equals("voidBuildingWorld")) {

            if (args.length > 0) {
                if (args[0].equals("large")) {

                    for (int x = -150; x <= 150; x++) {
                        for (int z = -150; z <= 150; z++) {
                            Block currentBlock = world.getBlockAt(x, 64, z);
                            currentBlock.setType(getColorCodedBlock(currentBlock.getBiome()));
                        }
                    }
                    // Set center block back to bedrock
                    world.getBlockAt(0, 64, 0).setType(Material.BEDROCK);

                    return true;
                } else {
                    senda.sendMessage("§eNothing to do.");
                    return true;
                }
            } else {
                for (int i = -1; i > -16; i--) {
                    Block currentBlock = world.getBlockAt(0, 64, i);
                    currentBlock.setType(getColorCodedBlock(currentBlock.getBiome()));
                    System.out.println(currentBlock.getBiome());
                }
                return true;
            }

        } else {
            senda.sendMessage("§cThis command should be run in the void world for safety!");
            return true;
        }


    }


    private Material getColorCodedBlock(Biome biome) {
//        Material colorblock;
        switch (biome.toString()) {
            case "PLAINS":
                return Material.WHITE_STAINED_GLASS;
            case "DESERT":
                return Material.YELLOW_STAINED_GLASS;
            case "JUNGLE":
                return Material.LIME_STAINED_GLASS;
            case "BADLANDS":
                return Material.ORANGE_STAINED_GLASS;
            case "TAIGA":
                return Material.BLUE_STAINED_GLASS;
            case "SAVANNA":
                return Material.BROWN_STAINED_GLASS;
            case "ICE_SPIKES":
                return Material.MAGENTA_STAINED_GLASS;
            case "THE_VOID":
                return Material.BLACK_STAINED_GLASS;
        }

        return Material.AIR;
    }
}

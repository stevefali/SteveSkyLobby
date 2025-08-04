package com.steve.steveSkyLobby.commands;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BiomeCommand implements CommandExecutor {


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
                    final Vector center = senda.getLocation().toVector();

                    // Visualization:
                    // [(3)Jungle]   [(2)Desert]  [(9)End(The Void)]
                    // [(4)Badlands] [(1)Plains]  [(8)Nether(The Void)]
                    // [(5)Savanna]  [(6)Taiga]   [(7)Ice Spikes]

                    setSectionBiome(Biome.PLAINS, world, new Vector(center.getBlockX() - 50, 64, center.getBlockZ() - 50), 100, 50);
                    setSectionBiome(Biome.DESERT, world, new Vector(center.getBlockX() - 50, 64, center.getBlockZ() - 150), 100, 50);
                    setSectionBiome(Biome.JUNGLE, world, new Vector(center.getBlockX() - 150, 64, center.getBlockZ() - 150), 100, 50);
                    setSectionBiome(Biome.BADLANDS, world, new Vector(center.getBlockX() - 150, 64, center.getBlockZ() - 50), 100, 50);
                    setSectionBiome(Biome.SAVANNA, world, new Vector(center.getBlockX() - 150, 64, center.getBlockZ() + 50), 100, 50);
                    setSectionBiome(Biome.TAIGA, world, new Vector(center.getBlockX() - 50, 64, center.getBlockZ() + 50), 100, 50);
                    setSectionBiome(Biome.ICE_SPIKES, world, new Vector(center.getBlockX() + 50, 64, center.getBlockZ() + 50), 100, 50);
                    setSectionBiome(Biome.THE_VOID, world, new Vector(center.getBlockX() + 50, 64, center.getBlockZ() - 50), 100, 50);
                    setSectionBiome(Biome.THE_VOID, world, new Vector(center.getBlockX() + 50, 64, center.getBlockZ() - 150), 100, 50);

                    senda.sendMessage("§aSet all 9 region biomes :)");
                    return true;
                } else {
                    senda.sendMessage("§eNothing to do.");
                    return true;
                }
            } else {
                Block sendersBlock = world.getBlockAt(senda.getLocation());
                sendersBlock.setBiome(Biome.JUNGLE);
                System.out.println(sendersBlock.getBiome().toString());
                senda.sendMessage("§aSet block at " + sendersBlock.getLocation().toVector() + " biome to " + sendersBlock.getBiome());

                return true;
            }

        } else {
            senda.sendMessage("§cThis command should be run in the void world for safety!");
            return true;
        }

    }

    /**
     * Set the biome of each block in the cuboid region. Starts in the lowest x, y position and works is way up.
     *
     * @param start      The lowest(x,z) corner of the region, with y in the vertical center.
     * @param size       The length and width of the region.
     * @param halfHeight Half the height of the region, since the start is in the vertical center.
     */
    private void setSectionBiome(Biome biome, World world, Vector start, int size, int halfHeight) {
        int startY = start.getBlockY() - halfHeight;
        for (int x = start.getBlockX(); x <= start.getBlockX() + size; x++) {
            for (int z = start.getBlockZ(); z <= start.getBlockZ() + size; z++) {
                for (int y = startY; y <= startY + (halfHeight * 2); y++) {
                    try {
                        world.getBlockAt(x, y, z).setBiome(biome);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

}

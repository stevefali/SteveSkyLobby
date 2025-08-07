package com.steve.steveSkyLobby.commands;

import com.steve.steveSkyLobby.worldoperation.CuboidOperation;
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

        if (args.length > 0) {
            if (args[0].equals("large")) {
                final Vector center = new Vector(0, 64, 0);

                // Visualization:
                // [(3)Jungle]   [(2)Desert]  [(9)End(The Void)]
                // [(4)Badlands] [(1)Plains]  [(8)Nether(The Void)]
                // [(5)Savanna]  [(6)Taiga]   [(7)Ice Spikes]

                setSectionBiome(Biome.PLAINS, world, new Vector(center.getBlockX() - 50, -36, center.getBlockZ() - 50), 100, 200, 100);
                setSectionBiome(Biome.DESERT, world, new Vector(center.getBlockX() - 50, -36, center.getBlockZ() - 150), 100, 200, 100);
                setSectionBiome(Biome.JUNGLE, world, new Vector(center.getBlockX() - 150, -36, center.getBlockZ() - 150), 100, 200, 100);
                setSectionBiome(Biome.BADLANDS, world, new Vector(center.getBlockX() - 150, -36, center.getBlockZ() - 50), 100, 200, 100);
                setSectionBiome(Biome.SAVANNA, world, new Vector(center.getBlockX() - 150, -36, center.getBlockZ() + 50), 100, 200, 100);
                setSectionBiome(Biome.TAIGA, world, new Vector(center.getBlockX() - 50, -36, center.getBlockZ() + 50), 100, 200, 100);
                setSectionBiome(Biome.ICE_SPIKES, world, new Vector(center.getBlockX() + 50, -36, center.getBlockZ() + 50), 100, 200, 100);
                setSectionBiome(Biome.THE_VOID, world, new Vector(center.getBlockX() + 50, -36, center.getBlockZ() - 50), 100, 200, 100);
                setSectionBiome(Biome.THE_VOID, world, new Vector(center.getBlockX() + 50, -36, center.getBlockZ() - 150), 100, 200, 100);

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
    }


    private void setSectionBiome(Biome biome, World world, Vector start, int sizeX, int sizeY, int sizeZ) {
        CuboidOperation.performCuboidSectionOperation(start, sizeX, sizeY, sizeZ,
                (v) -> world.getBlockAt(v.getBlockX(), v.getBlockY(), v.getBlockZ()).setBiome(biome));
    }

}

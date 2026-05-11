package com.steve.steveSkyLobby.event;


import com.fastasyncworldedit.core.FAWEPlatformAdapterImpl;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.steve.steveSkyLobby.worldoperation.SchematicOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class BlockEvent implements Listener {


    private Plugin plugin;

    public BlockEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIronBlockBreak(BlockBreakEvent event) {

        if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
            if (event.getPlayer().isOp()) {
                event.getPlayer().sendMessage("You are an OP!");
            } else {
                event.getPlayer().sendMessage("You are NOT an OP!");
            }

        }

        if (event.getBlock().getType() == Material.WAXED_COPPER_BLOCK) {
            Location location = event.getBlock().getLocation();
            Vector vec = new Vector(-0.5, 1.0, 0.0);
            location.subtract(vec);
            StorageMinecart minecart = (StorageMinecart) event.getBlock().getWorld().spawnEntity(location, EntityType.CHEST_MINECART);
            Inventory inventory = minecart.getInventory();
            inventory.setItem(0, new ItemStack(Material.SUNFLOWER, 21));
            inventory.setItem(26, new ItemStack(Material.WATER_BUCKET));
        }

        if (event.getBlock().getType() == Material.NETHERITE_BLOCK) {
            List<World> worlds = plugin.getServer().getWorlds();
            System.out.println("Number of worlds: " + worlds.size());
            for (World world : worlds) {
                System.out.println(world.getName());
            }

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                File currentFolder = new File("./");


                if (currentFolder.exists()) {
                    System.out.println("Current directory:");
                    for (File file : currentFolder.listFiles()) {
                        System.out.println(file.getName());
                    }
                }
            });


//            ServerLevel level = new ServerLevel()

        }

        if (event.getBlock().getType() == Material.IRON_BLOCK) {
           /* Plugin[] plugins =  event.getPlayer().getServer().getPluginManager().getPlugins();

            System.out.println("Plugins: ");
            for (Plugin plugin : plugins) {
                System.out.println(plugin.getName());
            }*/

//            Location spot = event.getBlock().getLocation();
//            World world = event.getBlock().getWorld();
//
//            world.getBlockAt(spot.add(1, 1,1)).setType(Material.BARRIER);
//
//
//            System.out.println("Iron block broken");



            /*try {

                Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                    World world = plugin.getServer().getWorld("voidBuildingWorld");
                    File file = new File("plugins/FastAsyncWorldEdit/schematics/island_plains.schem");
//                    System.out.println("File isand_plains.schem exists? " + file.exists());

                    ClipboardFormat format = ClipboardFormats.findByFile(file);

                    Clipboard clipboard;
                    try {
                        ClipboardReader reader = format.getReader(new FileInputStream(file));
                        clipboard = reader.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world));

                    ClipboardHolder holder = new ClipboardHolder(clipboard);

                    Operation operation = holder
                            .createPaste(editSession)
                            .to(BlockVector3.at(0, 64, 0))
                            .ignoreAirBlocks(true)
                            .build();

                    Operations.complete(operation);

                    editSession.close();

                    event.getPlayer().sendMessage("§aPasted the island!");

                });


            } catch (Exception e) {
                e.printStackTrace();
            }*/



            /*SchematicOperation.pasteSchematic(
                            "plugins/FastAsyncWorldEdit/schematics/island_jungle.schem",
                            world,
                            plugin,
                            new Vector(-100, 65, -100)
                    )
                    .exceptionally(throwable -> {
                        event.getPlayer().sendMessage("§cError: " + throwable.getMessage());
                        throwable.printStackTrace();
                        return false;
                    })
                    .thenAccept(success -> {
                        if (success) {
                            event.getPlayer().sendMessage("§aSuccessfully pasted schematic!");

                            Bukkit.getScheduler().runTask(plugin, () -> {
                                Location location = new Location(world, -100, 65, -100);
                                Block block = location.getBlock();
                                block.setType(Material.CHEST);
                                Chest chest = (Chest) block.getState();

                                Inventory inventory = chest.getInventory();
                                inventory.setItem(3, new ItemStack(Material.ACACIA_LOG, 4));
                            });

                        }
                    });*/
            World world = plugin.getServer().getWorld("voidBuildingWorld");

            SchematicOperation.pasteSchematic(
                            "plugins/FastAsyncWorldEdit/schematics/island_ice_spikes.schem",
                            world,
                            plugin,
                            new Vector(100, 65, 0)
                    )
                    .exceptionally(throwable -> {
                        event.getPlayer().sendMessage("§cError: " + throwable.getMessage());
                        throwable.printStackTrace();
                        return false;
                    })
                    .thenAccept(success -> {
                        if (success) {
                            event.getPlayer().sendMessage("§aSuccessfully pasted schematic!");
                        }
                    });


        }
    }

}

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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BlockEvent implements Listener {


    private Plugin plugin;

    public BlockEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIronBlockBreak(BlockBreakEvent event) {
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

            World world = plugin.getServer().getWorld("voidBuildingWorld");

            SchematicOperation.pasteSchematic(
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
                        }
                    });
        }
    }

}

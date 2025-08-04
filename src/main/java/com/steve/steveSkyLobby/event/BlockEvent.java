package com.steve.steveSkyLobby.event;


import com.fastasyncworldedit.core.FAWEPlatformAdapterImpl;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class BlockEvent implements Listener {

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


        }
    }

}

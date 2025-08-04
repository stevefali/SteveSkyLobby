package com.steve.steveSkyLobby.event;

import com.steve.steveSkyLobby.SteveSkyLobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TpServerEvent implements Listener {


    private SteveSkyLobby steveSkyLobby;

    public TpServerEvent(SteveSkyLobby steveSkyLobby) {
        this.steveSkyLobby = steveSkyLobby;
    }


    @EventHandler
    public void onPlayerRequestTp(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.GOLD_BLOCK) {

            Player player = event.getPlayer();


            String destinationServer = "skyblock";

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

            try  {
                dataOutputStream.writeUTF("Connect");
                dataOutputStream.writeUTF(destinationServer);

                player.sendPluginMessage(steveSkyLobby, "BungeeCord", byteArrayOutputStream.toByteArray());

                byteArrayOutputStream.close();
                dataOutputStream.close();

            } catch (IOException ioException) {
                System.out.println("Error encoding plugin message" + ioException.getMessage());
            }

        } else if (event.getBlock().getType() == Material.DIAMOND_BLOCK) {
            Player player = event.getPlayer();


            player.getServer().dispatchCommand(player, "server skyblock");

        }
    }

}

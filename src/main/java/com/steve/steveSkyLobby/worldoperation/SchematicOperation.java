package com.steve.steveSkyLobby.worldoperation;

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
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class SchematicOperation {

    /**
     * Pastes a given schematic at a given location.
     *
     * @param schematicPath The path of the .schem file of the schematic to be pasted.
     * @param world         The world that the schematic will be pasted into.
     * @param plugin        This plugin.
     * @param location      The block position where the schematic's origin will be placed.
     * @return A completable future boolean (true = successful paste, false = failed paste)
     */
    public static CompletableFuture<Boolean> pasteSchematic(String schematicPath, World world, Plugin plugin, Vector location) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                File schematicFile = new File(schematicPath);
                if (!schematicFile.exists()) {
                    future.completeExceptionally(new WorldOperationException("Schematic file not found"));
                    return;
                }

                ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);
                Clipboard clipboard;

                ClipboardReader reader = format.getReader(new FileInputStream(schematicFile));
                clipboard = reader.read();

                EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world));
                ClipboardHolder holder = new ClipboardHolder(clipboard);
                Operation operation = holder
                        .createPaste(editSession)
                        .to(BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
                        .ignoreAirBlocks(true)
                        .build();

                Operations.complete(operation);
                editSession.close();

                future.complete(true);

            } catch (IOException e) {
                future.completeExceptionally(new WorldOperationException("Failed to read schematic file", e));
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }
}

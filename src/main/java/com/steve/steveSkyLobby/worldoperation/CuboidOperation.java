package com.steve.steveSkyLobby.worldoperation;

import org.bukkit.util.Vector;

import java.util.function.Consumer;

public class CuboidOperation {

    /**
     * Perform a block operation of each block in the cuboid region. Starts in the lowest numerical position and works is way up.
     *
     * @param start     The lowest(x,y,z) corner of the region.
     * @param sizeX     The x-size of the region.
     * @param sizeY     The height of the region.
     * @param sizeZ     The Z-size of the region
     * @param operation The operation to be performed on each block
     */
    public static void performCuboidSectionOperation(Vector start, int sizeX, int sizeY, int sizeZ, Consumer<Vector> operation) {
        Vector finish = new Vector(start.getBlockX() + sizeX, start.getBlockY() + sizeY, start.getBlockZ() + sizeZ);
        performCuboidSectionOperation(start, finish, operation);
    }

    /**
     * Perform a block operation of each block in the cuboid region. Starts in the lowest numerical position and works is way up.
     *
     * @param start     The lowest(x,y,z) corner of the region.
     * @param finish    The highest(x,y,z) corner of the region.
     * @param operation The operation to be performed on each block
     */
    public static void performCuboidSectionOperation(Vector start, Vector finish, Consumer<Vector> operation) {
        for (int x = start.getBlockX(); x <= finish.getBlockX(); x++) {
            for (int z = start.getBlockZ(); z <= finish.getBlockZ(); z++) {
                for (int y = start.getBlockY(); y <= finish.getBlockY(); y++) {
                    try {
                        operation.accept(new Vector(x, y, z));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

}

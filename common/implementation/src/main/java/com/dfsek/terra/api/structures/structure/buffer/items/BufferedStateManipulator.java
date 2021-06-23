package com.dfsek.terra.api.structures.structure.buffer.items;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.structure.buffer.BufferedItem;
import com.dfsek.terra.vector.LocationImpl;
import com.dfsek.terra.api.block.state.BlockState;

public class BufferedStateManipulator implements BufferedItem {
    private final TerraPlugin main;
    private final String data;

    public BufferedStateManipulator(TerraPlugin main, String state) {
        this.main = main;
        this.data = state;
    }

    @Override
    public void paste(LocationImpl origin) {
        try {
            BlockState state = origin.getBlock().getState();
            state.applyState(data);
            state.update(false);
        } catch(Exception e) {
            main.logger().warning("Could not apply BlockState at " + origin + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

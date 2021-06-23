package com.dfsek.terra.bukkit;

import com.dfsek.terra.vector.LocationImpl;
import com.dfsek.terra.api.entity.Entity;
import com.dfsek.terra.api.world.World;
import com.dfsek.terra.bukkit.world.BukkitAdapter;

public class BukkitEntity implements Entity {
    private final org.bukkit.entity.Entity entity;

    public BukkitEntity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public org.bukkit.entity.Entity getHandle() {
        return entity;
    }

    @Override
    public LocationImpl getLocation() {
        return BukkitAdapter.adapt(entity.getLocation());
    }

    @Override
    public void setLocation(LocationImpl location) {
        entity.teleport(BukkitAdapter.adapt(location));
    }

    @Override
    public World getWorld() {
        return BukkitAdapter.adapt(entity.getWorld());
    }

    @Override
    public void sendMessage(String message) {
        entity.sendMessage(message);
    }
}

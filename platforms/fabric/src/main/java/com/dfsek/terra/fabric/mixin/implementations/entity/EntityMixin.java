package com.dfsek.terra.fabric.mixin.implementations.entity;

import com.dfsek.terra.vector.LocationImpl;
import com.dfsek.terra.api.world.World;
import com.dfsek.terra.fabric.util.FabricAdapter;
import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(Entity.class)
@Implements(@Interface(iface = com.dfsek.terra.api.entity.Entity.class, prefix = "terra$", remap = Interface.Remap.NONE))
public abstract class EntityMixin {
    @Shadow
    public net.minecraft.world.World world;

    @Shadow
    private BlockPos blockPos;

    @Shadow
    public abstract void teleport(double destX, double destY, double destZ);

    @Shadow
    public abstract void sendSystemMessage(Text message, UUID senderUuid);

    @Intrinsic
    public Object terra$getHandle() {
        return this;
    }

    public LocationImpl terra$getLocation() {
        return new LocationImpl((World) world, FabricAdapter.adapt(blockPos));
    }

    public void terra$setLocation(LocationImpl location) {
        teleport(location.getX(), location.getY(), location.getZ());
    }

    public World terra$getWorld() {
        return (World) world;
    }

    public void terra$sendMessage(String message) {
        sendSystemMessage(new LiteralText(message), UUID.randomUUID()); // TODO: look into how this actually works and make it less jank
    }
}

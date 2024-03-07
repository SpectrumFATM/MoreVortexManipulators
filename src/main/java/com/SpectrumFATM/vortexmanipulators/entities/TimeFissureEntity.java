package com.SpectrumFATM.vortexmanipulators.entities;

import com.SpectrumFATM.vortexmanipulators.network.PacketTimeFissure;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;
import java.util.Random;

public class TimeFissureEntity extends MobEntity implements ICapabilitySerializable<CompoundNBT> {

    public TimeFissureEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    // Attributes (Feels self-explanatory)
    public static AttributeModifierMap.MutableAttribute setCustomAttribute() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void remove() {
        super.remove();
        this.setNoAi(true);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true; // Make the entity invulnerable
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.serializeNBT();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_EXPLODE;
    }

    @Override
    public void playerTouch(PlayerEntity entity) {
        super.playerTouch(entity);
        CompoundNBT nbt = entity.getPersistentData();

        // Send packet only on the server
        if (this.level.isClientSide())
            NetworkHandler.INSTANCE.sendToServer(new PacketTimeFissure());
    }

    @Override
    protected void pushEntities() {
        super.pushEntities();
    }

    @Override
    protected void setRot(float p_70101_1_, float p_70101_2_) {
        Random random = new Random();
        int directionX = random.nextInt(360);
        super.setRot(directionX, p_70101_2_);
    }

    @Override
    public void spawnAnim() {
        super.spawnAnim();
    }

    @Override
    public void tick() {
        super.tick();
        Random random = new Random();

        if (random.nextInt(12000) == 1) {
            //Occurs approx every 10 minutes.
            //This is one minecraft day.
        }
    }
}

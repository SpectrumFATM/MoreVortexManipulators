package com.SpectrumFATM.vortexmanipulators.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class TimeFissureEntity extends MobEntity {

    public TimeFissureEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    //Attributes (Feels self-explanatory)
    public static AttributeModifierMap.MutableAttribute setCustomAtrribute() {
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
    public void playerTouch(PlayerEntity entity) {
        super.playerTouch(entity);
        //insert code here
    }

    @Override
    public void spawnAnim() {
        super.spawnAnim();
    }
}
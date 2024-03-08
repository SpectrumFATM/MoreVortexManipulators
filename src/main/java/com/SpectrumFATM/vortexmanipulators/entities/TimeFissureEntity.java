package com.SpectrumFATM.vortexmanipulators.entities;

import com.SpectrumFATM.vortexmanipulators.network.PacketTimeFissure;
import com.SpectrumFATM.vortexmanipulators.registries.ItemRegistry;
import com.SpectrumFATM.vortexmanipulators.registries.NetworkHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.tardis.mod.sounds.TSounds;

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
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        Item heldItem = playerEntity.getMainHandItem().getItem();
        if (playerEntity.getItemInHand(hand).getItem() == ItemRegistry.TIME_CONVERTER.get()) {
            heldItem.setDamage(playerEntity.getItemInHand(hand), heldItem.getDamage(playerEntity.getItemInHand(hand)) + 1);
            if (heldItem.getDamage(playerEntity.getItemInHand(hand)) >= 4) {
                playerEntity.setItemInHand(hand, ItemStack.EMPTY);
                playerEntity.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.0F);
            }
            this.remove();
            playerEntity.playSound(TSounds.VM_TELEPORT.get(), 1.0F, 1.0F);
        }
        return super.mobInteract(playerEntity, hand);
    }

    @Override
    public void spawnAnim() {
        super.spawnAnim();
    }

    @Override
    public void tick() {
        super.tick();

        Random random = new Random();

        if (random.nextInt(10) == 1) {
            this.level.addParticle(ParticleTypes.CRIMSON_SPORE, this.position().x, this.position().y, this.position().z, 0.0D, 0.0D, 0.0D);
        }

        if (random.nextInt(12000) == 1) {
            //Occurs approx every 10 minutes.
            //This is one minecraft day.
        }
    }
}

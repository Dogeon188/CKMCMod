package me.ckffmc.farm.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class OysterEntity extends WaterCreatureEntity {
    private static final TrackedData<Boolean> OPENED;
    private static final String OPENED_KEY = "Opened";
    private float prevOpenProgress;
    private float openProgress;

    public OysterEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    public static DefaultAttributeContainer.Builder createOysterAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.03f);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OPENED, false);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.dataTracker.set(OPENED, tag.getBoolean(OPENED_KEY));
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putBoolean(OPENED_KEY, this.dataTracker.get(OPENED));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(7, new OysterEntity.PeekGoal());
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        this.updateAnimations();
        super.tick();
    }

    private void updateAnimations() {
        this.prevOpenProgress = this.openProgress;
        if (this.dataTracker.get(OPENED)) this.openProgress -= 0.2F;
        else this.openProgress += 0.2F;
        this.openProgress = MathHelper.clamp(this.openProgress, 0.0F, 1.0F);
    }

    public void setOpen(boolean opened) {
        if (!this.world.isClient) {
            if (opened) this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 0.5F, 1.0F);
            else this.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 0.5F, 1.0F);
        }
        this.dataTracker.set(OPENED, opened);
    }

    @Environment(EnvType.CLIENT)
    public float getOpenProgress(float tickDelta) {
        return MathHelper.lerp(tickDelta, this.prevOpenProgress, this.openProgress);
    }

    static {
        OPENED = DataTracker.registerData(OysterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    class PeekGoal extends Goal {
        private int counter;

        private PeekGoal() { }

        public boolean canStart() { return OysterEntity.this.random.nextInt(150) == 0; }

        public boolean shouldContinue() {
            return this.counter > 0;
        }

        public void start() {
            this.counter = 20 + OysterEntity.this.random.nextInt(20);
            OysterEntity.this.setOpen(true);
        }

        public void stop() { OysterEntity.this.setOpen(false); }

        public void tick() { --this.counter; }
    }
}

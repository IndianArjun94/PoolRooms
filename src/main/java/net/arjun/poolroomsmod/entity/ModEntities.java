package net.arjun.poolroomsmod.entity;

import net.arjun.poolroomsmod.PoolRoomsMod;
import net.arjun.poolroomsmod.entity.custom.ChairEntity;
import net.arjun.poolroomsmod.entity.custom.MantisEntity;
import net.arjun.poolroomsmod.entity.custom.TomahawkProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> MANTIS_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, "mantis"));
    private static final RegistryKey<EntityType<?>> TOMAHAWK_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, "tomahawk"));
    private static final RegistryKey<EntityType<?>> CHAIR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, "chair_entity"));


    public static final EntityType<MantisEntity> MANTIS = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(PoolRoomsMod.MOD_ID, "mantis"),
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f).build(MANTIS_KEY));

    public static final EntityType<TomahawkProjectileEntity> TOMAHAWK = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(PoolRoomsMod.MOD_ID, "tomahawk"),
            EntityType.Builder.<TomahawkProjectileEntity>create(TomahawkProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build(TOMAHAWK_KEY));

    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(PoolRoomsMod.MOD_ID, "chair_entity"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build(CHAIR_KEY));



    public static void registerModEntities() {
        PoolRoomsMod.LOGGER.info("Registering Mod Entities for " + PoolRoomsMod.MOD_ID);
    }
}

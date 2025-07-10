package net.arjun.poolroomsmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.arjun.poolroomsmod.PoolRoomsMod;
import net.arjun.poolroomsmod.block.ModBlocks;
import net.arjun.poolroomsmod.block.entity.custom.GrowthChamberBlockEntity;
import net.arjun.poolroomsmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, "pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static final BlockEntityType<GrowthChamberBlockEntity> GROWTH_CHAMBER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, "growth_chamber_be"),
                    FabricBlockEntityTypeBuilder.create(GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER).build(null));


    public static void registerBlockEntities() {
        PoolRoomsMod.LOGGER.info("Registering Block Entities for " + PoolRoomsMod.MOD_ID);
    }
}

package net.arjun.poolroomsmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.arjun.poolroomsmod.PoolRoomsMod;
import net.arjun.poolroomsmod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER =
            registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);


    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(PoolRoomsMod.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        PoolRoomsMod.LOGGER.info("Registering Mod Enchantment Effects for " + PoolRoomsMod.MOD_ID);
    }
}

package net.arjun.poolroomsmod.item.custom;

import net.arjun.poolroomsmod.block.ModBlocks;
import net.arjun.poolroomsmod.component.ModDataComponentTypes;
import net.arjun.poolroomsmod.particle.ModParticles;
import net.arjun.poolroomsmod.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK,
                    Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK
            );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), ModSounds.CHISEL_USE, SoundCategory.BLOCKS);

                ((ServerWorld) world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, clickedBlock.getDefaultState()),
                        context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 1.0,
                        context.getBlockPos().getZ() + 0.5, 5, 0, 0, 0, 1);

                ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME,
                        context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 1.5,
                        context.getBlockPos().getZ() + 0.5, 10, 0, 0, 0, 3);

                ((ServerWorld) world).spawnParticles(ModParticles.PINK_GARNET_PARTICLE,
                        context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 1.0,
                        context.getBlockPos().getZ() + 0.5, 8, 0, 0, 0, 2);



                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if(Screen.hasShiftDown()) {
            textConsumer.accept(Text.translatable("tooltip.tutorialmod.chisel.shift_down"));
        } else {
            textConsumer.accept(Text.translatable("tooltip.tutorialmod.chisel"));
        }

        if(stack.get(ModDataComponentTypes.COORDINATES) != null) {
            textConsumer.accept(Text.literal("Last Block Changed at " + stack.get(ModDataComponentTypes.COORDINATES)));
        }

        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }

    int counter = 1;
    int chanceToAddLevitation = MathHelper.nextInt(Random.create(), 1, 5);

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(counter <= chanceToAddLevitation) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 50, 0), attacker);
            counter = 1;
        } else {
            counter++;
        }
    }
}

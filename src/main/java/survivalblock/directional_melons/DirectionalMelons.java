package survivalblock.directional_melons;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public class DirectionalMelons implements ModInitializer {
	public static final String MOD_ID = "directional_melons";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

	@Override
	public void onInitialize() {
	}

    public static boolean canNowRotate(BlockState blockState) {
        return canNowRotate(blockState.getBlock());
    }

    public static boolean canNowRotate(Block block) {
        Optional<ResourceKey<Block>> optional = BuiltInRegistries.BLOCK.getResourceKey(block);
        return optional.isPresent() && canNowRotate(optional.get());
    }

    public static boolean canNowRotate(ResourceKey<Block> resourceKey) {
        return Objects.equals(resourceKey, net.minecraft.references.Blocks.MELON) ||
                Objects.equals(resourceKey, net.minecraft.references.Blocks.PUMPKIN);
    }
}
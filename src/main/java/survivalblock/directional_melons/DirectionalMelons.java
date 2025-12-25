package survivalblock.directional_melons;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//? >=1.21.2
import survivalblock.directional_melons.mixin.BlockBehaviourAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DirectionalMelons implements ModInitializer {
	public static final String MOD_ID = "directional_melons";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

    //? <=1.21.1 {
    /*public static boolean changing = false;
    public static List<Block> changed = new ArrayList<>();
    *///?}

	@Override
	public void onInitialize() {
	}

    public static boolean canNowRotate(BlockState blockState) {
        return canNowRotate(blockState.getBlock());
    }

    public static boolean canNowRotate(Block block) {
        //? <=1.21.1 {
        /*if (changing || changed.contains(block)) {
            changed.add(block);
            changing = false;
            return true;
        }
        *///?}

        //? >=1.21.2 {
        ResourceKey<Block> resourceKey = ((BlockBehaviourAccessor.PropertiesAccessor) block.properties()).directional_melons$getId();
        return canNowRotate(resourceKey);
        //?}
        //? <=1.21.1 && >=1.20.4 {
        /*Optional<ResourceKey<Block>> optional = BuiltInRegistries.BLOCK.getResourceKey(block);
        return optional.isPresent() && canNowRotate(optional.get());
        *///?} else {
        return Objects.equals(block, Blocks.MELON) || Objects.equals(block, Blocks.PUMPKIN);
        //?}
    }

    //? >=1.20.4 {
    public static boolean canNowRotate(ResourceKey<Block> resourceKey) {
        return Objects.equals(resourceKey, net.minecraft.references.Blocks.MELON) ||
                Objects.equals(resourceKey, net.minecraft.references.Blocks.PUMPKIN);
    }
    //?}
}
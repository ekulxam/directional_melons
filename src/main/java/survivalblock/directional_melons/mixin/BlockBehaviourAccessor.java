package survivalblock.directional_melons.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.class)
public interface BlockBehaviourAccessor {
    @Mixin(BlockBehaviour.Properties.class)
    interface PropertiesAccessor {
        @Accessor("id")
        ResourceKey<Block> directional_melons$getId();
    }
}

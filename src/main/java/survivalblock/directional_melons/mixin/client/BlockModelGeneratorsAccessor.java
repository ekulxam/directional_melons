//? if >=1.21.2 {
package survivalblock.directional_melons.mixin.client;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.model.VariantMutator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockModelGenerators.class)
public interface BlockModelGeneratorsAccessor {
    @Accessor("ROTATION_FACING")
    static PropertyDispatch<VariantMutator> directional_melons$getRotationFacing() {
        throw new UnsupportedOperationException();
    }
}
//?}
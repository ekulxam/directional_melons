package survivalblock.directional_melons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.directional_melons.DirectionalMelons;

import static survivalblock.directional_melons.DirectionalMelons.FACING;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow
    protected abstract void registerDefaultState(BlockState blockState);

    @Shadow
    public abstract BlockState defaultBlockState();

    @Inject(method = "<init>", at = @At("RETURN"))
    private void registerDirectionalState(BlockBehaviour.Properties properties, CallbackInfo ci) {
        if (!DirectionalMelons.canNowRotate((Block) (Object) this)) {
            return;
        }
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.SOUTH));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
    private void addDirectionalState(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        if (!DirectionalMelons.canNowRotate((Block) (Object) this)) {
            return;
        }
        builder.add(FACING);
    }

    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    private BlockState getDirectionalState(BlockState original, BlockPlaceContext blockPlaceContext) {
        if (!DirectionalMelons.canNowRotate(original)) {
            return original;
        }
        return original.setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite());
    }
}

package survivalblock.directional_melons.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.directional_melons.DirectionalMelons;

import static survivalblock.directional_melons.DirectionalMelons.FACING;

@Mixin(StemBlock.class)
public class StemBlockMixin {

    @WrapOperation(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 0))
    private boolean changeFruitDirection(ServerLevel instance, BlockPos blockPos, BlockState blockState, Operation<Boolean> original, @Local Direction direction) {
        if (DirectionalMelons.canNowRotate(blockState)) {
            blockState = blockState.setValue(FACING, direction.getOpposite());
        }
        return original.call(instance, blockPos, blockState);
    }
}

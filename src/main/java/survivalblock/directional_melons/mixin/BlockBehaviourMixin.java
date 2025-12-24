package survivalblock.directional_melons.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.directional_melons.DirectionalMelons;

import static survivalblock.directional_melons.DirectionalMelons.FACING;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

	@ModifyReturnValue(method = "rotate", at = @At("RETURN"))
	private BlockState rotateDirectional(BlockState original, @Local(argsOnly = true) Rotation rotation) {
        if (!DirectionalMelons.canNowRotate(original)) {
            return original;
        }
        return original.setValue(FACING, rotation.rotate(original.getValue(FACING)));
    }

    @ModifyReturnValue(method = "mirror", at = @At("RETURN"))
    private BlockState mirrorDirectional(BlockState original, @Local(argsOnly = true) Mirror mirror) {
        if (!DirectionalMelons.canNowRotate(original)) {
            return original;
        }
        return original.rotate(mirror.getRotation(original.getValue(FACING)));
    }
}
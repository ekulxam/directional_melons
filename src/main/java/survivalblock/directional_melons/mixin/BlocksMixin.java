//? <=1.21.1 {
/*package survivalblock.directional_melons.mixin;

import net.minecraft.world.level.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.directional_melons.DirectionalMelons;

@SuppressWarnings("UnusedMixin")
@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(method = "<clinit>", at = {
            //? >=1.20.3 {
            @At(value = "FIELD", target = "Lnet/minecraft/references/Blocks;MELON:Lnet/minecraft/resources/ResourceKey;", shift = At.Shift.AFTER, ordinal = 0, opcode = Opcodes.GETSTATIC),
            @At(value = "FIELD", target = "Lnet/minecraft/references/Blocks;PUMPKIN:Lnet/minecraft/resources/ResourceKey;", shift = At.Shift.AFTER, ordinal = 0, opcode = Opcodes.GETSTATIC)
            //?} else {
            /^@At(value = "CONSTANT", args = "stringValue=melon", shift = At.Shift.AFTER, ordinal = 0),
            @At(value = "CONSTANT", args = "stringValue=pumpkin", shift = At.Shift.AFTER, ordinal = 0)
            ^///?}
    })
    private static void setChanging(CallbackInfo ci) {
        DirectionalMelons.changing = true;
    }

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clear(CallbackInfo ci) {
        DirectionalMelons.changed.clear();
        DirectionalMelons.changed = null;
        DirectionalMelons.changing = false;
    }
}
*///?}
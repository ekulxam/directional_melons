package survivalblock.directional_melons.mixin;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FabricDataGenerator.class)
public interface FabricDataGeneratorAccessor {
    @Mutable
    @Accessor("modContainer")
    void directional_melons$setModContainer(ModContainer modContainer);
}

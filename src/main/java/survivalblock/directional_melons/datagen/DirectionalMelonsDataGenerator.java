package survivalblock.directional_melons.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.Identifier;
import survivalblock.directional_melons.mixin.FabricDataGeneratorAccessor;

import java.util.Optional;

public class DirectionalMelonsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Optional<ModContainer> optional = FabricLoader.getInstance().getModContainer(Identifier.DEFAULT_NAMESPACE);
        if (optional.isEmpty()) {
            return;
        }
        ((FabricDataGeneratorAccessor) (Object) fabricDataGenerator).directional_melons$setModContainer(optional.get());
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DirectionalMelonsModelGenerator::new);
	}
}

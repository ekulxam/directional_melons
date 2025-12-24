package survivalblock.directional_melons.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import survivalblock.directional_melons.mixin.client.BlockModelGeneratorsAccessor;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class DirectionalMelonsModelGenerator extends FabricModelProvider {
    public static final PropertyDispatch<VariantMutator> ROTATION_FACING = BlockModelGeneratorsAccessor.directional_melons$getRotationFacing();

    public DirectionalMelonsModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        this.createDirectional(blockStateModelGenerator, Blocks.MELON);
        this.createDirectional(blockStateModelGenerator, Blocks.PUMPKIN);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    private void createDirectional(BlockModelGenerators generator, Block block) {
        MultiVariant multiVariant = plainVariant(ModelLocationUtils.getModelLocation(block));
        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block, multiVariant)
                                .with(ROTATION_FACING)
                );
    }
}

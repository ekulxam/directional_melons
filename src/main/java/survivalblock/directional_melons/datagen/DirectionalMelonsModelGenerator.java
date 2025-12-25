package survivalblock.directional_melons.datagen;

//? if >=1.21.2 {
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.model.VariantMutator;
//?} else {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.Identifier;
*///?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
//? if >=1.21.2
import survivalblock.directional_melons.mixin.client.BlockModelGeneratorsAccessor;

public class DirectionalMelonsModelGenerator extends FabricModelProvider {
    //? if >=1.21.2
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
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }

    private void createDirectional(BlockModelGenerators generator, Block block) {
        Identifier blockId = ModelLocationUtils.getModelLocation(block);
        //? if >=1.21.2 {
        MultiVariant multiVariant = BlockModelGenerators.plainVariant();
        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block, multiVariant)
                                .with(ROTATION_FACING)
                );
        //?} else {
        /*generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, blockId))
                                .with(BlockModelGenerators.createFacingDispatch())
                );
        *///?}
    }
}

package com.freezedown.metallurgica.foundation;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MBlockStateGen {
    public static <P extends Block> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> naturalMineralTypeBlock(
            String type) {
        return (c, p) -> {
            ConfiguredModel[] variants = new ConfiguredModel[4];
            for (int i = 0; i < variants.length; i++) {
                if(p.models().existingFileHelper.exists(p.modLoc("block/palettes/stone_types/mineral/" + type + "_top_" + i), new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures"))) {
                    variants[i] = ConfiguredModel.builder()
                            .modelFile(p.models()
                                    .cubeColumn(type + "_natural_" + i, p.modLoc("block/palettes/stone_types/mineral/" + type + "_" + i), p.modLoc("block/palettes/stone_types/mineral/" + type + "_top_" + i)))
                            .buildLast();
                } else {
                    variants[i] = ConfiguredModel.builder()
                            .modelFile(p.models()
                                    .cubeAll(type + "_natural_" + i, p.modLoc("block/palettes/stone_types/mineral/" + type + "_" + i)))
                            .buildLast();
                }
            }
            p.getVariantBuilder(c.get())
                    .partialState()
                    .setModels(variants);
        };
    }

    public static <P extends Block> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> naturalMineralDepositTypeBlock(
            String type) {
        return (c, p) -> {
            ConfiguredModel model;
            if(p.models().existingFileHelper.exists(p.modLoc("block/palettes/stone_types/mineral/" + type + "_top"), new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures"))) {
                model = ConfiguredModel.builder()
                        .modelFile(p.models()
                                .cubeColumn(type, p.modLoc("block/palettes/stone_types/mineral/" + type), p.modLoc("block/palettes/stone_types/mineral/" + type + "_top")))
                        .buildLast();
            } else {
                model = ConfiguredModel.builder()
                        .modelFile(p.models()
                                .cubeAll(type, p.modLoc("block/palettes/stone_types/mineral/" + type)))
                        .buildLast();
            }
            p.getVariantBuilder(c.get())
                    .partialState()
                    .setModels(model);
        };
    }
}

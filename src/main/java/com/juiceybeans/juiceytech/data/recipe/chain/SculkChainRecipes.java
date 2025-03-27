package com.juiceybeans.juiceytech.data.recipe.chain;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.juiceybeans.juiceytech.JTMain;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.juiceybeans.juiceytech.common.data.JTRecipeTypes.SCULK_CHAMBER_RECIPES;

public class SculkChainRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        sculkChainRecipes(provider);
    }

    private static void sculkChainRecipes(Consumer<FinishedRecipe> provider) {
        SCULK_CHAMBER_RECIPES.recipeBuilder(JTMain.id("echo_shard"))
                .inputItems(Items.AMETHYST_SHARD)
                .inputFluids(GTMaterials.Mutagen.getFluid(720)) // Replace with liquid experience
                .outputItems(Items.ECHO_SHARD)
                .duration(300).EUt(VA[EV])
                .save(provider);

        SCULK_CHAMBER_RECIPES.recipeBuilder(JTMain.id("sculk"))
                .inputItems(BlockTags.SCULK_REPLACEABLE)
                .inputFluids(GTMaterials.Mutagen.getFluid(144)) // Replace with liquid experience
                .outputItems(Items.SCULK)
                .duration(40).EUt(VA[LV])
                .save(provider);
    }
}

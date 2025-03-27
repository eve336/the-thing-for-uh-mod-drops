package com.juiceybeans.juiceytech.data.recipe.generated;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.juiceybeans.juiceytech.JTMain;
import com.juiceybeans.juiceytech.common.data.JTItems;
import com.juiceybeans.juiceytech.common.item.SoulCanisterItem;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.juiceybeans.juiceytech.common.data.JTRecipeTypes.MOB_DUPLICATION_RECIPES;
import static com.juiceybeans.juiceytech.common.item.SoulCanisterItem.getItemWithMob;

public class MobDuplicationRecipes {

    private static final List<String> defaultEntities = new ArrayList<String>();

    private static void setDefaultEntities() {
        defaultEntities.add("minecraft:zombie");
        defaultEntities.add("minecraft:blaze");
        defaultEntities.add("minecraft:slime");
        defaultEntities.add("minecraft:wither");
    }

    public static void init(Consumer<FinishedRecipe> provider) {
//        MOB_DUPLICATION_RECIPES.recipeBuilder(JTMain.id("blaze"))
//                .inputItems(getMobInCanister("minecraft:blaze"))
//                .inputFluids(GTMaterials.Mutagen.getFluid(720)) // Replace with liquid experience
//                .outputItems(Items.BLAZE_POWDER)
//                .duration(300).EUt(VA[EV])
//                .save(provider);
//
//        MOB_DUPLICATION_RECIPES.recipeBuilder(JTMain.id("slime"))
//                .inputItems(getMobInCanister("minecraft:slime"))
//                .inputFluids(GTMaterials.Mutagen.getFluid(720)) // Replace with liquid experience
//                .outputItems(Items.SLIME_BALL)
//                .duration(300).EUt(VA[EV])
//                .save(provider);

        generateDuplicationRecipes(provider);
    }

    public static void generateDuplicationRecipes(Consumer<FinishedRecipe> provider) {
        setDefaultEntities();
        for (String entityName : defaultEntities) {
            SoulCanisterItem.getEntityType(entityName).getDefaultLootTable();

            MOB_DUPLICATION_RECIPES.recipeBuilder(JTMain.id(entityName.replace(":","/")))
                    .notConsumable(getItemWithMob(entityName))
                    .inputFluids(GTMaterials.Mutagen.getFluid(720)) // Replace with liquid experience
                    .outputItems(Items.SLIME_BALL)
                    .duration(300).EUt(VA[EV])
                    .save(provider);
        }
    }
}

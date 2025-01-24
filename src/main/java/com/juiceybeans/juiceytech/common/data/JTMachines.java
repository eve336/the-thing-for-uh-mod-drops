package com.juiceybeans.juiceytech.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class JTMachines {
    // Multiblock Machines
    public static final MachineDefinition SCULK_GROWTH_CHAMBER = REGISTRATE.multiblock("sculk_growth_chamber", WorkableElectricMultiblockMachine::new)
            .langValue("Sculk Growth Chamber")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.sculk_growth_chamber")))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(JTRecipeTypes.SCULK_CHAMBER_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(GCYMBlocks.CASING_NONCONDUCTING) // Replace
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" CSC ", " GGG ", " GGG ", " GGG ", " CCC ")
                    .aisle("CCCCC", "G   G", "G   G", "G   G", "CCCCC")
                    .aisle("CCCCC", "G   G", "G   G", "G   G", "CCCCC")
                    .aisle("CCCCC", "G   G", "G   G", "G   G", "CCCCC")
                    .aisle(" CCC ", " GGG ", " GGG ", " GGG ", " CCC ")
                    .where('S', Predicates.controller(Predicates.blocks(definition.get())))
                    .where('G', Predicates.blocks(GTBlocks.FUSION_GLASS.get())) // Replace
                    .where('C', Predicates.blocks(GCYMBlocks.CASING_NONCONDUCTING.get()) // Replace
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.abilities(PartAbility.PARALLEL_HATCH).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1)))
                    .where('#', Predicates.any())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/gcym/nonconducting_casing"),
                    GTCEu.id("block/multiblock/implosion_compressor"), false)
            .register();

    public static void init() {
    }
}

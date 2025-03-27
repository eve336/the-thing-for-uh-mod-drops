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
import com.juiceybeans.juiceytech.JTMain;
import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.juiceybeans.juiceytech.JTMain.JT_REGISTRATE;

public class JTMultiblocks {
    public static final MachineDefinition SCULK_GROWTH_CHAMBER = JT_REGISTRATE.multiblock("sculk_growth_chamber", WorkableElectricMultiblockMachine::new)
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

    public static final MachineDefinition MOB_DUPLICATOR = JT_REGISTRATE.multiblock("mob_duplicator", WorkableElectricMultiblockMachine::new)
            .langValue("Mob Duplicator")
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .tooltips(Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", Component.translatable("gtceu.mob_duplicator")))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(JTRecipeTypes.MOB_DUPLICATION_RECIPES)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .appearanceBlock(GTBlocks.CASING_ALUMINIUM_FROSTPROOF)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                    .aisle("XXXXX", "G###G", "G#F#G", "G###G", "XXXXX")
                    .aisle("XXXXX", "G#F#G", "GFFFG", "G#F#G", "XXXXX")
                    .aisle("XXXXX", "G###G", "G#F#G", "G###G", "XXXXX")
                    .aisle("XXSXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(GTBlocks.CASING_HSSE_STURDY.get()))
                    .where('G', blocks(GTBlocks.FUSION_GLASS.get()))
                    .where('#', Predicates.air())
                    .where('X', blocks(GTBlocks.CASING_ALUMINIUM_FROSTPROOF.get()).setMinGlobalLimited(34)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.autoAbilities(true, false, true)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_frost_proof"),
                    JTMain.id("block/multiblock/mob_duplicator"), false)
            .register();

    public static void init() {
    }
}

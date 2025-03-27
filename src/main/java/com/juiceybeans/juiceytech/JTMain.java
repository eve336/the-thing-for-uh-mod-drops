package com.juiceybeans.juiceytech;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.juiceybeans.juiceytech.common.data.*;
import com.juiceybeans.juiceytech.common.item.SoulCanisterItem;
import com.juiceybeans.juiceytech.data.JTDatagen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.juiceybeans.juiceytech.JTMain.MOD_ID;
import static net.minecraft.world.level.storage.loot.entries.LootPoolEntries.ITEM;

@Mod(MOD_ID)
public class JTMain {
    public static final String MOD_ID = "juiceytech";
    public static final Logger LOGGER = LogManager.getLogger();
    public static GTRegistrate JT_REGISTRATE = GTRegistrate.create(JTMain.MOD_ID);

    public JTMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        JTTabs.init();
        JTItems.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);
        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);

        JTDatagen.init();
        JT_REGISTRATE.registerRegistrate();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(JTMain.MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Hello from common setup! This is *after* registries are done, so we can do this:");
            LOGGER.info("Look, I found a {}!", Items.DIAMOND);
            JTItems.generateSoulCanistersForMobs();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Hey, we're on Minecraft version {}!", Minecraft.getInstance().getLaunchedVersion());
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        JTLootTables.addSoulCanisterToLoot(event, "chests/nether_bridge", "minecraft:blaze");
    }

    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(JTMain.MOD_ID);
    }

    private void addMaterials(MaterialEvent event) {
        JTMaterials.init();
    }

    private void modifyMaterials(PostMaterialEvent event) {
        //JTMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        JTRecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        JTMachines.init();
        JTMultiblocks.init();
    }
}

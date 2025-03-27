package com.juiceybeans.juiceytech.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.juiceybeans.juiceytech.JTMain;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;

import static com.juiceybeans.juiceytech.JTMain.JT_REGISTRATE;
import static com.juiceybeans.juiceytech.common.item.SoulCanisterItem.createCanisterForEntity;
import static net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE;

public class JTTabs {
    public static RegistryEntry<CreativeModeTab> JT_MATERIALS = JT_REGISTRATE.defaultCreativeTab("juiceytech_items",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("juiceytech_items", JT_REGISTRATE))
                            .icon(() -> GTItems.WETWARE_CIRCUIT_BOARD.asStack())
                            .title(JT_REGISTRATE.addLang("itemGroup", JTMain.id("juiceytech_items"),  "JuiceyTech Materials"))
                            .displayItems((params, output) -> {
                                for (EntityType<?> entityType : ENTITY_TYPE) {
                                    if (entityType.getCategory() == MobCategory.MONSTER || entityType.getCategory() == MobCategory.CREATURE) {
                                        output.accept(createCanisterForEntity(entityType));
                                    }
                                }
                            })
                            .build())
            .register();

    static {
        JT_REGISTRATE.creativeModeTab(() -> JTTabs.JT_MATERIALS);
    }

    public static void init() {

    }
}

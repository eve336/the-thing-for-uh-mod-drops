package com.juiceybeans.juiceytech.common.data;

import com.juiceybeans.juiceytech.common.item.SoulCanisterItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static com.juiceybeans.juiceytech.JTMain.JT_REGISTRATE;
import static com.juiceybeans.juiceytech.common.item.SoulCanisterItem.createCanisterForEntity;
import static net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE;

public class JTItems {
    public static final ItemEntry<SoulCanisterItem> SOUL_CANISTER = JT_REGISTRATE.item("soul_canister", p -> new SoulCanisterItem())
            .lang("Soul Canister")
            .register();

    public static void generateSoulCanistersForMobs() {
        for (EntityType<?> entityType : ENTITY_TYPE) {
            if (entityType.getCategory() == MobCategory.MONSTER || entityType.getCategory() == MobCategory.CREATURE) {
                createCanisterForEntity(entityType);
            }
        }
    }

    public static void init() {

    }
}

package com.juiceybeans.juiceytech.common.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;

public class JTLootTables {
    public static void addItemToLootTable(LootTable lootTable, ItemStack item, int minCount, int maxCount) {
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .name("yourmodid_custom_pool")
                .setRolls(UniformGenerator.between(1, 1))
                .setBonusRolls(UniformGenerator.between(0, 0))
                .add(LootItem.lootTableItem(item.getItem())
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount))));
        lootTable.addPool(poolBuilder.build());
    }

    public static void addSoulCanisterToLoot(LootTableLoadEvent event, LootTable lootTable, String tablePath, String entity) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("entity", entity);

        if (event.getName().toString().equals("minecraft:" + tablePath)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .name("juiceytech/" + entity.substring(entity.indexOf(":")) + "_soul_canister")
                    .setRolls(UniformGenerator.between(0, 1))
                    .add(LootItem.lootTableItem(JTItems.SOUL_CANISTER)
                            .setWeight(1)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
                            .apply(SetNbtFunction.setTag(nbt)));
            lootTable.addPool(poolBuilder.build());
        }
    }
}

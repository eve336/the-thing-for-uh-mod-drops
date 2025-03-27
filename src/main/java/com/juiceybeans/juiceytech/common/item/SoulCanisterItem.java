package com.juiceybeans.juiceytech.common.item;

import com.juiceybeans.juiceytech.common.data.JTItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulCanisterItem extends Item {
    public SoulCanisterItem() {
        super(new Properties());
    }

    public static ItemStack createCanisterForEntity(EntityType<?> entityType) {
        ItemStack itemStack = new ItemStack(JTItems.SOUL_CANISTER.get());
        CompoundTag nbt = new CompoundTag();
        nbt.putString("entity", EntityType.getKey(entityType).toString());
        itemStack.setTag(nbt);
        return itemStack;
    }

    public static EntityType<?> getEntityType(String entityName) {
        return EntityType.byString(entityName).orElse(null);
    }

    public static EntityType<?> getEntityType(ItemStack itemStack) {
        if (itemStack.hasTag() && itemStack.getTag().contains("entity")) {
            String entityId = itemStack.getTag().getString("entity");
            return getEntityType(entityId);
        }
        return null;
    }
    public static String getEntityName(ItemStack itemStack) {
        String entityId = "None";
        if (itemStack.hasTag()) {
            assert itemStack.getTag() != null;
            if (itemStack.getTag().contains("entity")) {
                entityId = itemStack.getTag().getString("entity");
            }
        }
        return entityId;
    }

    public static ItemStack getItemStackWithMob(String entity) {
        EntityType<?> entityType = EntityType.byString(entity).orElse(null);
        assert entityType != null;
        ItemStack itemStack = new ItemStack(JTItems.SOUL_CANISTER.get());
        CompoundTag nbt = new CompoundTag();
        nbt.putString("entity", EntityType.getKey(entityType).toString());
        itemStack.setTag(nbt);

        return itemStack;
    }

    public static Item getItemWithMob(String entity) {
        return getItemStackWithMob(entity).getItem();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level pLevel, List<Component> lines, @NotNull TooltipFlag isAdvanced) {
        lines.add(Component.translatable("tooltip.item.juiceytech.soul_canister.1", getEntityName(itemStack)));
    }
}

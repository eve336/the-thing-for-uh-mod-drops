package com.juiceybeans.juiceytech.data.lang;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import java.util.Set;

public class JTLangHandler {
    private static final Set<Material> MATERIALS = Set.of(
    );

    private static void machineNames(RegistrateLangProvider provider) {
        provider.add("gtceu.sculk_chamber", "Sculk Chamber");
        provider.add("gtceu.mob_duplication", "Mob Duplication");
        provider.add("tooltip.item.juiceytech.soul_canister.1", "§7Stored Mob:§r §b%s");
    }

    public static void init(RegistrateLangProvider provider) {
        machineNames(provider);
    }
}

package com.juiceybeans.juiceytech.data;

import com.juiceybeans.juiceytech.data.lang.JTLangHandler;
import com.tterrag.registrate.providers.ProviderType;

import static com.juiceybeans.juiceytech.JTMain.JT_REGISTRATE;

public class JTDatagen {
    public static void init() {
        JT_REGISTRATE.addDataGenerator(ProviderType.LANG, JTLangHandler::init);
    }
}

package com.zzh.spear_origin.screen;

import com.zzh.spear_origin.SpearOrigin;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    // ✅ 正确：使用 static final 直接赋值。这样只会在类加载时执行一次。
    public static final ScreenHandlerType<SpearReforgingScreenHandler> SPEAR_REFORGING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(SpearOrigin.MOD_ID, "spear_reforging"),
                    new ExtendedScreenHandlerType<>(SpearReforgingScreenHandler::new));

    // 这个方法的内容可以是空的，或者只打印日志
    // 它的唯一作用是让 Java 加载这个类
    public static void registerScreenHandlers() {
        SpearOrigin.LOGGER.info("Registering Screen Handlers for " + SpearOrigin.MOD_ID);
    }
}
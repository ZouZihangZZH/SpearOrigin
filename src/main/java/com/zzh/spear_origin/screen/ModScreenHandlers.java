package com.zzh.spear_origin.screen;

import com.zzh.spear_origin.SpearOrigin;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    // 定义变量
    public static ScreenHandlerType<SpearReforgingScreenHandler> SPEAR_REFORGING_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        // 使用 ExtendedScreenHandlerType 注册
        // 这样我们才能在打开菜单时传递额外的数据（比如方块位置 BlockPos）
        SPEAR_REFORGING_SCREEN_HANDLER = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier(SpearOrigin.MOD_ID, "spear_reforging"),
                new ExtendedScreenHandlerType<>(SpearReforgingScreenHandler::new)
        );
    }
}
package com.zzh.spear_origin;

import com.zzh.spear_origin.screen.ModScreenHandlers;
import com.zzh.spear_origin.screen.SpearReforgingScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens; // 👈 关键导入

// 必须实现 ClientModInitializer 接口
public class SpearOriginClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // 这里的逻辑只会在客户端启动时运行

        // 🌟 核心绑定代码：
        // 当服务端请求打开 "SPEAR_REFORGING_SCREEN_HANDLER" 时，
        // 客户端会自动创建 "SpearReforgingScreen" 界面显示给玩家。
        HandledScreens.register(ModScreenHandlers.SPEAR_REFORGING_SCREEN_HANDLER, SpearReforgingScreen::new);
    }
}
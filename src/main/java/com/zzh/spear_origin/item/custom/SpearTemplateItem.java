package com.zzh.spear_origin.item.custom;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.List;

public class SpearTemplateItem extends SmithingTemplateItem {
    // 构造函数照搬
    public SpearTemplateItem(Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
    }

    // 重写方法：返回我们注册时的 ID
    @Override
    public String getTranslationKey() {
        return this.getOrCreateTranslationKey();
    }
}
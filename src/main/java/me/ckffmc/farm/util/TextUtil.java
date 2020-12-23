package me.ckffmc.farm.util;

import com.google.common.collect.Lists;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;

import java.util.List;

public class TextUtil {
    /**
     * connector should be one of the following: <br/>
     * {@code "space"} {@code "comma"}
     */
    public static MutableText concatenateText(String connector, MutableText... texts) {
        return concatenateText(connector, Lists.newArrayList(texts));
    }

    /**
     * connector should be one of the following: <br/>
     * {@code "space"} {@code "comma"}
     */
    public static MutableText concatenateText(String connector, List<MutableText> texts) {
        switch (texts.size()) {
            case 0: throw new IllegalArgumentException("Not enough MutableText arguments to parse!");
            case 1: return texts.get(0);
            default:
                return new TranslatableText("text.dogeon.concatenate." + connector, texts.get(0),
                        concatenateText(connector, texts.subList(1, texts.size())));
        }
    }
}

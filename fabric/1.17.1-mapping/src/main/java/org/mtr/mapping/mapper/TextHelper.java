package org.mtr.mapping.mapper;

import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.TextFormatting;
import org.mtr.mapping.tool.Dummy;

public final class TextHelper extends Dummy {

	@MappedMethod
	public static MutableText translatable(String key, Object... arguments) {
		return new MutableText(new TranslatableText(key, arguments));
	}

	@MappedMethod
	public static MutableText literal(String key) {
		return new MutableText(new LiteralText(key));
	}

	@MappedMethod
	public static MutableText format(MutableText mutableText, TextFormatting... textFormattingList) {
		final Formatting[] formatting = new Formatting[textFormattingList.length];
		for (int i = 0; i < textFormattingList.length; i++) {
			formatting[i] = textFormattingList[i].data;
		}
		return mutableText.formatted(formatting);
	}
}
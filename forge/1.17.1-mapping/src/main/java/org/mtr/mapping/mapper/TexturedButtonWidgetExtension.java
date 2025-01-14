package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.holder.TexturedButtonWidgetAbstractMapping;

public class TexturedButtonWidgetExtension extends TexturedButtonWidgetAbstractMapping {

	private final Identifier normalTexture;
	private final Identifier highlightedTexture;

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress) {
		this(x, y, width, height, normalTexture, highlightedTexture, onPress, "");
	}

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress, String message) {
		this(x, y, width, height, normalTexture, highlightedTexture, onPress, TextHelper.literal(message));
	}

	@MappedMethod
	public TexturedButtonWidgetExtension(int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, org.mtr.mapping.holder.PressAction onPress, MutableText message) {
		super(x, y, width, height, 0, 0, 0, normalTexture, 256, 256, onPress, new Text(message.data));
		this.normalTexture = normalTexture;
		this.highlightedTexture = highlightedTexture;
	}

	@MappedMethod
	public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
		final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
		guiDrawing.beginDrawingTexture(isHovered() ? highlightedTexture : normalTexture);
		guiDrawing.drawTexture(x, y, x + width, y + height, 0, 0, 1, 1);
		guiDrawing.finishDrawingTexture();
	}

	@Deprecated
	@Override
	public final void renderButton(PoseStack matrices, int mouseX, int mouseY, float delta) {
		GraphicsHolder.createInstanceSafe(matrices, null, graphicsHolder -> render(graphicsHolder, mouseX, mouseY, delta));
	}

	@Deprecated
	@Override
	public final boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		return mouseScrolled2(mouseX, mouseY, amount);
	}

	@MappedMethod
	public boolean mouseScrolled2(double mouseX, double mouseY, double amount) {
		return super.mouseScrolled(mouseX, mouseY, amount);
	}

	@MappedMethod
	public final int getX2() {
		return x;
	}

	@MappedMethod
	public final int getY2() {
		return y;
	}

	@MappedMethod
	public final void setX2(int x) {
		this.x = x;
	}

	@MappedMethod
	public final void setY2(int y) {
		this.y = y;
	}

	@MappedMethod
	@Override
	public final boolean isHovered() {
		return super.isHovered();
	}
}

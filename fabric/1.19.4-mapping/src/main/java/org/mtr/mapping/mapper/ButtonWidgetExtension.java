package org.mtr.mapping.mapper;

import net.minecraft.client.util.math.MatrixStack;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ButtonWidgetAbstractMapping;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;

import java.util.function.Supplier;

public class ButtonWidgetExtension extends ButtonWidgetAbstractMapping {

	@MappedMethod
	public ButtonWidgetExtension(int x, int y, int width, int height, org.mtr.mapping.holder.PressAction onPress) {
		this(x, y, width, height, "", onPress);
	}

	@MappedMethod
	public ButtonWidgetExtension(int x, int y, int width, int height, String message, org.mtr.mapping.holder.PressAction onPress) {
		this(x, y, width, height, TextHelper.literal(message), onPress);
	}

	@MappedMethod
	public ButtonWidgetExtension(int x, int y, int width, int height, MutableText message, org.mtr.mapping.holder.PressAction onPress) {
		super(x, y, width, height, new Text(message.data), onPress, Supplier::get);
	}

	@MappedMethod
	public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
		if (graphicsHolder.matrixStack != null) {
			super.renderButton(graphicsHolder.matrixStack, mouseX, mouseY, delta);
		}
	}

	@Deprecated
	@Override
	public final void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
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
		return super.getX();
	}

	@MappedMethod
	public final int getY2() {
		return super.getY();
	}

	@MappedMethod
	public final void setX2(int x) {
		super.setX(x);
	}

	@MappedMethod
	public final void setY2(int y) {
		super.setY(y);
	}

	@MappedMethod
	@Override
	public final boolean isHovered() {
		return super.isHovered();
	}
}

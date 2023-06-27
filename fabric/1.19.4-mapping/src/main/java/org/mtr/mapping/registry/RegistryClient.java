package org.mtr.mapping.registry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.network.PacketByteBuf;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockEntityRendererArgument;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.PacketBuffer;
import org.mtr.mapping.holder.ResourceLocation;
import org.mtr.mapping.mapper.BlockEntity;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.tool.Dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class RegistryClient extends Dummy {

	private static final List<Runnable> OBJECTS_TO_REGISTER = new ArrayList<>();

	@MappedMethod
	public static void init() {
		OBJECTS_TO_REGISTER.forEach(Runnable::run);
	}

	@MappedMethod
	public static <T extends BlockEntityType<U>, U extends BlockEntity> void registerBlockEntityRenderer(T blockEntityType, Function<BlockEntityRendererArgument, BlockEntityRenderer<U>> rendererInstance) {
		OBJECTS_TO_REGISTER.add(() -> BlockEntityRendererFactories.register(blockEntityType.data, context -> rendererInstance.apply(new BlockEntityRendererArgument(context))));
	}

	@MappedMethod
	public static void setupPackets(ResourceLocation resourceLocation) {
		ClientPlayNetworking.registerGlobalReceiver(resourceLocation.data, (client, handler, buf, responseSender) -> {
			final Function<PacketBuffer, ? extends PacketHandler> getInstance = Registry.PACKETS.get(buf.readString());
			if (getInstance != null) {
				final PacketHandler packetHandler = getInstance.apply(new PacketBuffer(buf));
				client.execute(packetHandler::run);
			}
		});
	}

	@MappedMethod
	public static <T extends PacketHandler> void sendPacketToServer(T data) {
		if (Registry.packetsResourceLocation != null) {
			final PacketByteBuf packetByteBuf = PacketByteBufs.create();
			packetByteBuf.writeString(data.getClass().getName());
			data.write(new PacketBuffer(packetByteBuf));
			ClientPlayNetworking.send(Registry.packetsResourceLocation.data, packetByteBuf);
		}
	}
}
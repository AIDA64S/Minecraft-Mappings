package org.mtr.mapping.mapper;

import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import org.apache.commons.io.IOUtils;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.tool.DummyClass;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class ResourceManagerHelper extends DummyClass {

	@MappedMethod
	public static void readResource(Identifier identifier, Consumer<InputStream> consumer) {
		try {
			readResource(MinecraftClient.getInstance().getResourceManager().getResource(identifier.data), consumer);
		} catch (Exception e) {
			logException(e);
		}
	}

	@MappedMethod
	public static String readResource(Identifier identifier) {
		final String[] string = {""};
		readResource(identifier, inputStream -> {
			try {
				string[0] = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			} catch (Exception e) {
				logException(e);
			}
		});
		return string[0];
	}

	@MappedMethod
	public static void readAllResources(Identifier identifier, Consumer<InputStream> consumer) {
		try {
			MinecraftClient.getInstance().getResourceManager().getAllResources(identifier.data).forEach(resource -> readResource(resource, consumer));
		} catch (Exception e) {
			logException(e);
		}
	}

	@MappedMethod
	public static void readDirectory(String path, BiConsumer<Identifier, InputStream> consumer) {
		try {
			MinecraftClient.getInstance().getResourceManager()
					.findResources(path, identifier -> true)
					.forEach(identifier -> readAllResources(new Identifier(identifier), inputStream -> consumer.accept(new Identifier(identifier), inputStream)));
		} catch (Exception e) {
			logException(e);
		}
	}

	private static void readResource(Resource resource, Consumer<InputStream> consumer) {
		try (final Resource newResource = resource) {
			try (final InputStream inputStream = newResource.getInputStream()) {
				consumer.accept(inputStream);
			} catch (Exception e) {
				logException(e);
			}
		} catch (Exception e) {
			logException(e);
		}
	}

	@MappedMethod
	public static int getResourcePackVersion() {
		return MinecraftVersion.create().getPackVersion();
	}

	@MappedMethod
	public static int getDataPackVersion() {
		return 0;
	}
}

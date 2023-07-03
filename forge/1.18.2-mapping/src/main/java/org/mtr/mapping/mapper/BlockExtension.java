package org.mtr.mapping.mapper;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockAbstractMapping;

public abstract class BlockExtension extends BlockAbstractMapping {

	public BlockExtension(Properties properties) {
		super(properties.blockSettings);
	}

	public static final class Properties {

		final BlockBehaviour.Properties blockSettings;

		@MappedMethod
		public Properties() {
			blockSettings = BlockBehaviour.Properties.of(Material.METAL);
		}

		private Properties(boolean blockPiston) {
			blockSettings = BlockBehaviour.Properties.of(blockPiston ? Material.HEAVY_METAL : Material.METAL);
		}

		private Properties(BlockBehaviour.Properties blockSettings) {
			this.blockSettings = blockSettings;
		}

		@MappedMethod
		public Properties blockPiston(boolean blockPiston) {
			return new Properties(blockPiston);
		}

		@MappedMethod
		public Properties luminance(int luminance) {
			return new Properties(blockSettings.lightLevel(blockState -> luminance));
		}
	}
}
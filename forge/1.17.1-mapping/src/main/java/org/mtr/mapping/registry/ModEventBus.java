package org.mtr.mapping.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockItemExtension;
import org.mtr.mapping.mapper.EntityExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModEventBus {

	final List<Supplier<Block>> BLOCKS = new ArrayList<>();
	final List<Supplier<BlockItemExtension>> BLOCK_ITEMS = new ArrayList<>();
	final List<Supplier<Item>> ITEMS = new ArrayList<>();
	final List<Supplier<BlockEntityType<? extends BlockEntityExtension>>> blockEntityTypes = new ArrayList<>();
	final List<Supplier<EntityType<? extends EntityExtension>>> entityTypes = new ArrayList<>();
	final List<Supplier<ParticleType<?>>> particleTypes = new ArrayList<>();
	final List<Supplier<SoundEvent>> soundEvents = new ArrayList<>();

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		BLOCKS.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		BLOCK_ITEMS.forEach(supplier -> event.getRegistry().register(supplier.get()));
		ITEMS.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}

	@SubscribeEvent
	public void registerBlockEntityTypes(RegistryEvent.Register<BlockEntityType<?>> event) {
		blockEntityTypes.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}

	@SubscribeEvent
	public void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
		entityTypes.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}

	@SubscribeEvent
	public void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) {
		particleTypes.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}

	@SubscribeEvent
	public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
		soundEvents.forEach(supplier -> event.getRegistry().register(supplier.get()));
	}
}

package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import com.google.gson.JsonObject
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance
import net.minecraft.advancements.critereon.ContextAwarePredicate
import net.minecraft.advancements.critereon.DeserializationContext
import net.minecraft.advancements.critereon.SimpleCriterionTrigger
import net.minecraft.core.Registry
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl

class HextrogenMain : ModInitializer {
	override fun onInitialize() {
		Registry.register(HexActions.REGISTRY, id("dash"), ActionRegistryEntry(HexPattern.fromAngles("qaqqqqdweee", HexDir.NORTH_EAST), OpDash()))
		Registry.register(HexActions.REGISTRY, id("refresh_dashes"), ActionRegistryEntry(HexPattern.fromAngles("qaqqeawawa", HexDir.NORTH_EAST), OpRefreshDashes()))
	}

	companion object {
		const val MOD_ID: String = "hextrogen"
		fun id(string: String) = ResourceLocation(MOD_ID, string)
		val DASH_CHANNEL: ResourceLocation = id("dash")
		val REFRESH_DASHES_CHANNEL: ResourceLocation = id("refresh_dashes")
	}
}
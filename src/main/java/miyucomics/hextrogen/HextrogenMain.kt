package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation

class HextrogenMain : ModInitializer {
	override fun onInitialize() {
		Registry.register(HexActions.REGISTRY, id("dash"), ActionRegistryEntry(HexPattern.fromAngles("qaqqqqdweee", HexDir.NORTH_EAST), OpDash()))
		Registry.register(HexActions.REGISTRY, id("refresh_dashes"), ActionRegistryEntry(HexPattern.fromAngles("qaqqadwdwd", HexDir.NORTH_EAST), OpRefreshDashes()))
	}

	companion object {
		private const val MOD_ID: String = "hextrogen"
		fun id(string: String) = ResourceLocation(MOD_ID, string)

		val DASH_CHANNEL: ResourceLocation = id("dash")
		val REFRESH_DASHES_CHANNEL: ResourceLocation = id("refresh_dashes")
	}
}
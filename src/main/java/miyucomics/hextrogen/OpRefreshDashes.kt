package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPlayer
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import dev.mayaqq.estrogen.registry.EstrogenEffects
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer

class OpRefreshDashes : SpellAction {
	override val argc = 1
	override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
		val player = args.getPlayer(0, argc)
		if (!player.hasEffect(EstrogenEffects.ESTROGEN_EFFECT.get()))
			throw NoEstrogenMishap()
		return SpellAction.Result(Spell(player), MediaConstants.CRYSTAL_UNIT, listOf())
	}

	private data class Spell(val player: ServerPlayer) : RenderedSpell {
		override fun cast(env: CastingEnvironment) {
			ServerPlayNetworking.send(player, HextrogenMain.REFRESH_DASHES_CHANNEL, PacketByteBufs.empty())
		}
	}
}
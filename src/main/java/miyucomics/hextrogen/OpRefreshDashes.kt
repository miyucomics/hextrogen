package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.misc.MediaConstants
import dev.mayaqq.estrogen.registry.EstrogenEffects
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player

class OpRefreshDashes : SpellAction {
	override val argc = 0
	override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
		if (env.castingEntity !is Player)
			throw MishapBadCaster()
		if (!env.castingEntity!!.hasEffect(EstrogenEffects.ESTROGEN_EFFECT.get()))
			throw NoEstrogenMishap()
		return SpellAction.Result(Spell(env.castingEntity as ServerPlayer), MediaConstants.CRYSTAL_UNIT, listOf())
	}

	private data class Spell(val player: ServerPlayer) : RenderedSpell {
		override fun cast(env: CastingEnvironment) {
			ServerPlayNetworking.send(player, HextrogenMain.REFRESH_DASHES_CHANNEL, PacketByteBufs.empty())
		}
	}
}
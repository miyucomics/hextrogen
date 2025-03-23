package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import dev.mayaqq.estrogen.registry.EstrogenEffects
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3

class OpDash : SpellAction {
	override val argc = 1
	override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
		if (env.castingEntity !is Player)
			throw MishapBadCaster()
		if (!env.castingEntity!!.hasEffect(EstrogenEffects.ESTROGEN_EFFECT.get()))
			throw NoEstrogenMishap()
		val direction = args.getVec3(0, argc)
		return SpellAction.Result(Spell(direction.normalize()), 0, listOf())
	}

	private data class Spell(val direction: Vec3) : RenderedSpell {
		override fun cast(env: CastingEnvironment) {
			val packet = PacketByteBufs.create()
			packet.writeDouble(direction.x)
			packet.writeDouble(direction.y)
			packet.writeDouble(direction.z)
			ServerPlayNetworking.send(env.castingEntity as ServerPlayer, HextrogenMain.DASH_CHANNEL, packet)
		}
	}
}
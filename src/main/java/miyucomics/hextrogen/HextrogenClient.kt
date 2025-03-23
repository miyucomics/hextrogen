package miyucomics.hextrogen

import dev.mayaqq.estrogen.client.features.dash.ClientDash
import dev.mayaqq.estrogen.config.EstrogenConfig
import dev.mayaqq.estrogen.features.dash.CommonDash
import dev.mayaqq.estrogen.networking.EstrogenNetworkManager
import dev.mayaqq.estrogen.networking.messages.c2s.DashPacket
import dev.mayaqq.estrogen.registry.blocks.DreamBlock
import miyucomics.hextrogen.HextrogenMain.Companion.DASH_CHANNEL
import miyucomics.hextrogen.HextrogenMain.Companion.REFRESH_DASHES_CHANNEL
import miyucomics.hextrogen.mixin.ClientDashAccessor
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.phys.Vec3

class HextrogenClient : ClientModInitializer {
	override fun onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(DASH_CHANNEL) { minecraft: Minecraft, _: ClientPacketListener, packet: FriendlyByteBuf, _: PacketSender ->
			val player = minecraft.player ?: return@registerGlobalReceiver
			val direction = Vec3(packet.readDouble(), packet.readDouble(), packet.readDouble())
			if (ClientDashAccessor.getDashes() == 0 || ClientDashAccessor.getDashCooldown() > 0)
				return@registerGlobalReceiver

			DreamBlock.lookAngle = direction
			CommonDash.setDashing(player.uuid)
			ClientDashAccessor.setDashCooldown(5)
			ClientDashAccessor.setDashLevel(ClientDashAccessor.getDashes())
			ClientDashAccessor.setDashes(ClientDashAccessor.getDashes() - 1)
			EstrogenNetworkManager.CHANNEL.sendToServer(DashPacket(true, ClientDashAccessor.getDashLevel()))
			ClientDashAccessor.setDashDirection(direction)
			ClientDashAccessor.setDashXRot(player.xRot.toDouble())
			ClientDashAccessor.setDashDeltaModifier(EstrogenConfig.server().dashDeltaModifier.get().toDouble())
		}

		ClientPlayNetworking.registerGlobalReceiver(REFRESH_DASHES_CHANNEL) { minecraft: Minecraft, _: ClientPacketListener, _: FriendlyByteBuf, _: PacketSender ->
			val player = minecraft.player ?: return@registerGlobalReceiver
			ClientDash.refresh(player)
		}
	}
}
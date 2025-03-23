package miyucomics.hextrogen.mixin;

import dev.mayaqq.estrogen.client.features.dash.ClientDash;
import miyucomics.hextrogen.HextrogenMain;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ClientDash.class, remap = false)
public class ClientDashMixin {
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Ldev/mayaqq/estrogen/features/dash/CommonDash;setDashing(Ljava/util/UUID;)V"))
	private static void unlockDashPage(CallbackInfo ci) {
		ClientPlayNetworking.send(HextrogenMain.DASH_CHANNEL, PacketByteBufs.empty());
	}
}
package miyucomics.hextrogen.mixin;

import dev.mayaqq.estrogen.client.features.dash.ClientDash;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientDash.class)
public interface ClientDashAccessor {
	@Accessor("dashes")
	static int getDashes() {
		throw new AssertionError();
	}

	@Accessor("dashes")
	static void setDashes(int dashes) {
		throw new AssertionError();
	}

	@Accessor("dashLevel")
	static int getDashLevel() {
		throw new AssertionError();
	}

	@Accessor("dashLevel")
	static void setDashLevel(int dashLevel) {
		throw new AssertionError();
	}

	@Accessor("dashCooldown")
	static int getDashCooldown() {
		throw new AssertionError();
	}

	@Accessor("dashCooldown")
	static void setDashCooldown(int dashCooldown) {
		throw new AssertionError();
	}

	@Accessor("dashDirection")
	static void setDashDirection(Vec3 dashDirection) {
		throw new AssertionError();
	}

	@Accessor("dashXRot")
	static void setDashXRot(double dashXRot) {
		throw new AssertionError();
	}

	@Accessor("dashDeltaModifier")
	static void setDashDeltaModifier(double dashDeltaModifier) {
		throw new AssertionError();
	}
}

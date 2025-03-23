package miyucomics.hextrogen

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.Mishap
import net.minecraft.world.item.DyeColor

class NoEstrogenMishap : Mishap() {
	override fun accentColor(ctx: CastingEnvironment, errorCtx: Context) = dyeColor(DyeColor.RED)
	override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context) = error(HextrogenMain.MOD_ID + ":no_estrogen")
	override fun execute(env: CastingEnvironment, errorCtx: Context, stack: MutableList<Iota>) {}
}
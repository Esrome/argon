package esrome.scienceMod.world;

import esrome.scienceMod.util.Reference;
import net.minecraft.util.DamageSource;

public class DamageSources {

	public static final DamageSource MERCURY_DAMAGE = new DamageSource(Reference.MODID+".mercury_burn").setMagicDamage();
	public static final DamageSource RADIATION_DAMAGE = new DamageSource(Reference.MODID+".radiation").setMagicDamage();
	
}

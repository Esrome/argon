package esrome.scienceMod.init;

import esrome.scienceMod.potions.CustomPotion;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {

	public static final Potion MERCURY_BURN_EFFECT = new CustomPotion("mercury_burn", true, 9118011, 0, 0);
	public static final Potion RADIATION_EFFECT = new CustomPotion("radiation", true, 15728421, 0, 0);
	
	//1200 is a minute

	public static final PotionType MERCURY_BURN = new PotionType("mercury_burn", new PotionEffect[] {new PotionEffect(MERCURY_BURN_EFFECT, 600)}).setRegistryName("mercury_burn");
	public static final PotionType MERCURY_BURN_LONG = new PotionType("mercury_burn", new PotionEffect[] {new PotionEffect(MERCURY_BURN_EFFECT, 1200)}).setRegistryName("long_mercury_burn");
	public static final PotionType MERCURY_BURN_STRONG = new PotionType("mercury_burn", new PotionEffect[] {new PotionEffect(MERCURY_BURN_EFFECT, 600, 1)}).setRegistryName("strong_mercury_burn");
	
	public static final PotionType RADIATION = new PotionType("radiation", new PotionEffect[] {new PotionEffect(RADIATION_EFFECT, 600)}).setRegistryName("radiation");
	public static final PotionType RADIATION_LONG = new PotionType("radiation", new PotionEffect[] {new PotionEffect(RADIATION_EFFECT, 1200)}).setRegistryName("long_radiation");
	public static final PotionType RADIATION_STRONG = new PotionType("radiation", new PotionEffect[] {new PotionEffect(RADIATION_EFFECT, 600, 1)}).setRegistryName("strong_radiation");
	
	public static void registerPotions() {
		registerPotion(MERCURY_BURN, MERCURY_BURN_LONG, MERCURY_BURN_STRONG, MERCURY_BURN_EFFECT);
		registerPotion(RADIATION, RADIATION_LONG, RADIATION_STRONG, RADIATION_EFFECT);
		
		registerPotionMixes();
	}
	
	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
		ForgeRegistries.POTION_TYPES.register(strongPotion);
	}
	
	private static void registerPotionMixes() {
		PotionHelper.addMix(MERCURY_BURN, Items.REDSTONE, MERCURY_BURN_LONG);
		PotionHelper.addMix(MERCURY_BURN, Items.GLOWSTONE_DUST, MERCURY_BURN_STRONG);
		PotionHelper.addMix(RADIATION, Items.REDSTONE, RADIATION_LONG);
		PotionHelper.addMix(RADIATION, Items.GLOWSTONE_DUST, RADIATION_STRONG);
	}
	
}

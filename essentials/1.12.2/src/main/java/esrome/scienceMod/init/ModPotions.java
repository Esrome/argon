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
	
	public static void registerPotions() {		
		registerPotionMixes();
	}
	
	private static void registerPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect) {
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
		ForgeRegistries.POTION_TYPES.register(strongPotion);
	}
	
	private static void registerPotionMixes() {
		
	}
	
}

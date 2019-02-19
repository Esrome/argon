package esrome.scienceMod.world.event;

import esrome.scienceMod.init.ModPotions;
import esrome.scienceMod.world.DamageSources;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class WorldEvents {
	
	@SubscribeEvent
	public static void mercuryBurnActive(PlayerTickEvent event) {
		boolean isActive = false;
		if(event.player.isPotionActive(ModPotions.MERCURY_BURN_EFFECT)) isActive = true;
		
		if(isActive) {
			event.player.attackEntityFrom(DamageSources.MERCURY_DAMAGE, 1 + event.player.getActivePotionEffect(ModPotions.MERCURY_BURN_EFFECT).getAmplifier());
		}
	}
	
	@SubscribeEvent
	public static void radiationActive(PlayerTickEvent event) {
		boolean isActive = false;
		if(event.player.isPotionActive(ModPotions.RADIATION_EFFECT)) isActive = true;
		
		if(isActive) {
			event.player.cameraPitch+=5;
			event.player.attackEntityFrom(DamageSources.RADIATION_DAMAGE, 1 + event.player.getActivePotionEffect(ModPotions.RADIATION_EFFECT).getAmplifier());
		}
	}

}

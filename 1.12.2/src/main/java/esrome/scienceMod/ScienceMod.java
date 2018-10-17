package esrome.scienceMod;

import esrome.scienceMod.proxy.ServerProxy;
import esrome.scienceMod.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ScienceMod {

	@Instance
	public static ScienceMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static ServerProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
	
}

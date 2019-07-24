package esrome.scienceMod;

import esrome.scienceMod.proxy.ServerProxy;
import esrome.scienceMod.util.Reference;
import esrome.scienceMod.util.compatibility.OreDictionaryCompatibility;
import esrome.scienceMod.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Argon {

	public static final CreativeTabs TAB = new ArgonTab("argon");
	
	@Instance
	public static Argon instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static ServerProxy proxy;
	
	static { FluidRegistry.enableUniversalBucket(); }
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInit();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.init();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		OreDictionaryCompatibility.registerOres();
	}
	
}

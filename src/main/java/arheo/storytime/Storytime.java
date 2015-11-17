package arheo.storytime;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = Storytime.MOD_ID, name = "Story Time with Uncle Arheo", version = "$GRADLEVERSION")
public class Storytime {

    public static final String MOD_ID = "storytime";
    public static final Logger logger = LogManager.getLogger(MOD_ID);
   
    
    @Mod.Instance
    public static Storytime instance;
    
   // @SidedProxy(serverSide="ttftcuts.physis.common.CommonProxy", clientSide="ttftcuts.physis.client.ClientProxy")
   // public static CommonProxy proxy;
        
    
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	GameRegistry.addRecipe(new BookRecipe());
    	
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    
}

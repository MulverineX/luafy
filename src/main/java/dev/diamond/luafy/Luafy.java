package dev.diamond.luafy;

import dev.diamond.luafy.commmand.LuaCommand;
import dev.diamond.luafy.config.LuafyConfig;
import dev.diamond.luafy.resource.CallbackScriptResourceLoader;
import dev.diamond.luafy.resource.LuaScriptResourceLoader;
import dev.diamond.luafy.resource.SandboxStrategyResourceLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luafy implements ModInitializer {
	public static final String MODID = "luafy";
	public static final String LUAJ_VER = "3.0.8";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final LuaScriptResourceLoader LUA_SCRIPT_RESOURCES = new LuaScriptResourceLoader();
	public static final CallbackScriptResourceLoader CALLBACK_RESOURCES = new CallbackScriptResourceLoader();
	public static final SandboxStrategyResourceLoader SANDBOXES = new SandboxStrategyResourceLoader();

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Luafy with LuaJ Version {}. Thank you FiguraMC for maintaining LuaJ!", LUAJ_VER);

		CommandRegistrationCallback.EVENT.register(LuaCommand::registerLuaCommand);

		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(LUA_SCRIPT_RESOURCES);
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(CALLBACK_RESOURCES);
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(SANDBOXES);


		LuafyConfig.initializeConfig();
	}


	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}


}
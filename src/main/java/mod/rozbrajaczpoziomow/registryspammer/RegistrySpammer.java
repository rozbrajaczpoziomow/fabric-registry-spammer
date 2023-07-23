package mod.rozbrajaczpoziomow.registryspammer;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class RegistrySpammer implements ModInitializer {
	protected static final String MODID = "registry-spammer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		final int $default = 200;
		String fpath = (Util.getOperatingSystem() == Util.OperatingSystem.WINDOWS? System.getenv("TEMP") + "\\" : "/tmp/") + "rspam.iamlazy";
		File file = new File(fpath);

		if(file.exists() && file.canRead()) {
			try(Scanner handle = new Scanner(new FileInputStream(file))) {
				// Empty file - use default
				if(!handle.hasNext()) {
					register($default);
					return;
				}

				// Absolute value - add x
				if(handle.hasNextInt()) {
					register(handle.nextInt());
					return;
				}

				// Filler value - fill registry upto x items
				int amount = Integer.parseInt(handle.next().substring(1));
				register(Math.max(amount - Registries.ITEM.size(), 0));
				return;
			} catch(IOException ignored) {}
		}

		// If all else fails (File can't be read, IOException, etc.); return to default
		register($default);
	}

	private void register(final int amount) {
		LOGGER.warn("Adding " + amount + " items");
		for(int i = 0; i < amount; i++)
			Registry.register(Registries.ITEM, new Identifier(MODID, "spam_" + intToSomething(i)), new SpamItem(i, amount - 1));
	}

	private String intToSomething(final int from) {
		StringBuilder to = new StringBuilder();
		for(char i : String.valueOf(from).toCharArray())
			to.append((char) ((int) i + 49));
		return to.toString();
	}
}
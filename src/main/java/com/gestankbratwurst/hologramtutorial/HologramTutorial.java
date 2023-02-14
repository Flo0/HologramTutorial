package com.gestankbratwurst.hologramtutorial;

import co.aikar.commands.BukkitCommandManager;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramFactory;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramListener;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramManager;
import com.gestankbratwurst.hologramtutorial.commands.HologramCommand;
import com.gestankbratwurst.hologramtutorial.implementations.nms.NMSHologramFactory;
import com.gestankbratwurst.hologramtutorial.implementations.protocollib.PlibHologramFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HologramTutorial extends JavaPlugin {

  @Override
  public void onEnable() {
    // Choose one of those implementations
    HologramFactory nmsFactory = new NMSHologramFactory();
    HologramFactory plibFactory = new PlibHologramFactory();

    HologramManager hologramManager = new HologramManager(plibFactory);

    HologramListener hologramListener = new HologramListener(hologramManager);
    Bukkit.getPluginManager().registerEvents(hologramListener, this);

    setupCommands(hologramManager);
  }

  // Just for the purpose of testing. You can ignore this.
  private void setupCommands(HologramManager hologramManager) {
    BukkitCommandManager commandManager = new BukkitCommandManager(this);
    commandManager.registerCommand(new HologramCommand(hologramManager));
    commandManager.getCommandCompletions().registerCompletion("Holograms", context -> hologramManager.getHologramNames());
  }

}

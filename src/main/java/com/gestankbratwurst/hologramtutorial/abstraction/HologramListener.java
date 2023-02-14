package com.gestankbratwurst.hologramtutorial.abstraction;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HologramListener implements Listener {

  private final HologramManager hologramManager;

  public HologramListener(HologramManager hologramManager) {
    this.hologramManager = hologramManager;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    hologramManager.initPlayer(event.getPlayer());
  }

}

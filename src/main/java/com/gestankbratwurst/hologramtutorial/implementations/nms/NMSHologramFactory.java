package com.gestankbratwurst.hologramtutorial.implementations.nms;

import com.gestankbratwurst.hologramtutorial.abstraction.Hologram;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramFactory;
import org.bukkit.Location;

public class NMSHologramFactory implements HologramFactory {
  @Override
  public Hologram createHologram(Location location, String hologramName) {
    return new NMSHologram(location, hologramName);
  }
}

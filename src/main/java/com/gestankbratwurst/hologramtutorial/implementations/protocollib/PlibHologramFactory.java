package com.gestankbratwurst.hologramtutorial.implementations.protocollib;

import com.gestankbratwurst.hologramtutorial.abstraction.Hologram;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramFactory;
import org.bukkit.Location;

public class PlibHologramFactory implements HologramFactory {
  @Override
  public Hologram createHologram(Location location, String hologramName) {
    return new PlibHologram(location, hologramName);
  }
}

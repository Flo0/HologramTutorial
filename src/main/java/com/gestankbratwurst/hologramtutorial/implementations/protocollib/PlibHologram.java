package com.gestankbratwurst.hologramtutorial.implementations.protocollib;

import com.gestankbratwurst.hologramtutorial.abstraction.AbstractHologram;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramLine;
import org.bukkit.Location;

public class PlibHologram extends AbstractHologram {
  public PlibHologram(Location location, String name) {
    super(location, name);
  }

  @Override
  protected HologramLine createLine(Location location) {
    return new PlibHologramLine(location);
  }
}

package com.gestankbratwurst.hologramtutorial.implementations.nms;

import com.gestankbratwurst.hologramtutorial.abstraction.AbstractHologram;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramLine;
import org.bukkit.Location;

public class NMSHologram extends AbstractHologram {

  public NMSHologram(Location location, String name) {
    super(location, name);
  }

  @Override
  protected HologramLine createLine(Location location) {
    return new NMSHologramLine(location);
  }

}

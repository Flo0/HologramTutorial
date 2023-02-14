package com.gestankbratwurst.hologramtutorial.abstraction;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface HologramLine {

  String getText();

  void showTo(Player player);

  void hideFrom(Player player);

  void teleport(Location location);

  void setText(String text);

}

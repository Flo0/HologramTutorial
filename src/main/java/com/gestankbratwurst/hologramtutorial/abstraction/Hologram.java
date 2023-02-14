package com.gestankbratwurst.hologramtutorial.abstraction;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Hologram {

  // We are using String instead of UUIDs for user experience
  String getId();

  int size();

  void addLine(String line);

  void setLine(int index, String line);

  String getLine(int index);

  Location getLocation();

  void teleport(Location target);

  void showTo(Player player);

  void hideFrom(Player player);

  void removeLine(int index);
}

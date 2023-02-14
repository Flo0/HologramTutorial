package com.gestankbratwurst.hologramtutorial.implementations.nms;

import com.gestankbratwurst.hologramtutorial.abstraction.HologramLine;
import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSHologramLine implements HologramLine {

  private final ArmorStand nmsArmorStandBackbone;
  private String currentText;

  public NMSHologramLine(Location loc) {
    World world = loc.getWorld();
    Preconditions.checkArgument(world != null);
    Level level = ((CraftWorld) world).getHandle();
    this.nmsArmorStandBackbone = new ArmorStand(level, loc.getX(), loc.getY(), loc.getZ());
    this.nmsArmorStandBackbone.setMarker(true);
    this.nmsArmorStandBackbone.setCustomNameVisible(true);
    this.nmsArmorStandBackbone.setInvisible(true);
  }

  @Override
  public String getText() {
    return currentText;
  }

  @Override
  public void showTo(Player player) {
    ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
    connection.send(createAddPacket());
    connection.send(createDataPacket());
  }

  @Override
  public void hideFrom(Player player) {
    ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
    connection.send(createRemovePacket());
  }

  @Override
  public void teleport(Location location) {
    nmsArmorStandBackbone.setPos(location.getX(), location.getY(), location.getZ());
    ClientboundTeleportEntityPacket tpPacket = createMovePacket();
    Bukkit.getOnlinePlayers().forEach(player -> {
      ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
      connection.send(tpPacket);
    });
  }

  @Override
  public void setText(String text) {
    this.currentText = text;
    this.nmsArmorStandBackbone.setCustomName(Component.literal(text.replace("&", "§")));
    ClientboundSetEntityDataPacket dataPacket = createDataPacket();
    Bukkit.getOnlinePlayers().forEach(player -> {
      ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
      connection.send(dataPacket);
    });
  }

  private ClientboundAddEntityPacket createAddPacket() {
    return new ClientboundAddEntityPacket(nmsArmorStandBackbone);
  }

  private ClientboundSetEntityDataPacket createDataPacket() {
    SynchedEntityData data = nmsArmorStandBackbone.getEntityData();
    return new ClientboundSetEntityDataPacket(nmsArmorStandBackbone.getId(), data.getNonDefaultValues());
  }

  private ClientboundTeleportEntityPacket createMovePacket() {
    return new ClientboundTeleportEntityPacket(nmsArmorStandBackbone);
  }

  private ClientboundRemoveEntitiesPacket createRemovePacket() {
    return new ClientboundRemoveEntitiesPacket(IntList.of(nmsArmorStandBackbone.getId()));
  }

}

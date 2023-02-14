package com.gestankbratwurst.hologramtutorial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Values;
import com.gestankbratwurst.hologramtutorial.abstraction.HologramManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("hologram")
@CommandPermission("op")
public class HologramCommand extends BaseCommand {

  private final HologramManager hologramManager;

  public HologramCommand(HologramManager hologramManager) {
    this.hologramManager = hologramManager;
  }

  @Default
  public void onDefault(CommandSender sender) {
    sender.sendMessage("§e/hologram create <name>");
    sender.sendMessage("§e/hologram delete <name>");
    sender.sendMessage("§e/hologram here <name>");
    sender.sendMessage("§e/hologram create <name>");
  }

  @Subcommand("create")
  public void onHologramCreate(Player player, String hologramName) {
    hologramManager.createHologram(player.getLocation(), hologramName);
    player.sendMessage("§9> §fHologram §9%s §fcreated.".formatted(hologramName));
  }

  @Subcommand("delete")
  @CommandCompletion("@Holograms")
  public void onHologramDelete(Player player, @Values("@Holograms") String hologramName) {
    hologramManager.deleteHologram(hologramName);
    player.sendMessage("§9> §fHologram §9%s §fdeleted.".formatted(hologramName));
  }

  @Subcommand("here")
  @CommandCompletion("@Holograms")
  public void onHologramHere(Player player, @Values("@Holograms") String hologramName) {
    hologramManager.getHologram(hologramName).teleport(player.getLocation());
    player.sendMessage("§9> §fHologram §9%s §fmoved to you.".formatted(hologramName));
  }

  @Subcommand("addline")
  @CommandCompletion("@Holograms @nothing")
  public void onHologramAddLine(Player player, @Values("@Holograms") String hologramName, String line) {
    hologramManager.getHologram(hologramName).addLine(line);
    player.sendMessage("§9> §fAdded line to §9%s §f.".formatted(hologramName));
  }

  @Subcommand("setline")
  @CommandCompletion("@Holograms @nothing @nothing")
  public void onHologramSetLine(Player player, @Values("@Holograms") String hologramName, int lineIndex, String line) {
    int maxIndex = hologramManager.getHologram(hologramName).size() - 1;
    if (lineIndex > maxIndex) {
      player.sendMessage("§c> §fHologram only has §c%d §flines.".formatted(maxIndex + 1));
      return;
    }
    hologramManager.getHologram(hologramName).setLine(lineIndex, line);
    player.sendMessage("§9> §fSet line in §9%s §f.".formatted(hologramName));
  }

  @Subcommand("removeLine")
  @CommandCompletion("@Holograms @nothing")
  public void onHologramRemoveLine(Player player, @Values("@Holograms") String hologramName, int lineIndex) {
    int maxIndex = hologramManager.getHologram(hologramName).size() - 1;
    if (lineIndex > maxIndex) {
      player.sendMessage("§c> §fHologram only has §c%d §flines.".formatted(maxIndex + 1));
      return;
    }
    hologramManager.getHologram(hologramName).removeLine(lineIndex);
    player.sendMessage("§9> §fSet line in §9%s§f.".formatted(hologramName));
  }

}

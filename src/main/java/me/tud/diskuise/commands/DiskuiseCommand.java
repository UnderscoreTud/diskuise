package me.tud.diskuise.commands;

import me.tud.diskuise.Diskuise;
import me.tud.diskuise.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class DiskuiseCommand extends BukkitCommand {

    public DiskuiseCommand() {
        super("diskuise", "Reload config for Diskuise", "/diskuise reload", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission("diskuise.reload")) {
            sender.sendMessage(Util.colored("&cYou do not have permission to execute this command."));
            return true;
        }
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload"))
            return false;
        Diskuise.getInstance().reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded config");
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1)
            return Collections.singletonList("reload");
        return Collections.emptyList();
    }
}

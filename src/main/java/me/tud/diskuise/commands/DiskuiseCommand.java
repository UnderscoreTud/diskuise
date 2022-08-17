package me.tud.diskuise.commands;

import me.tud.diskuise.Diskuise;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class DiskuiseCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) return false;
        Diskuise.getInstance().reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded config");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1)
            return Collections.singletonList("reload");
        return Collections.emptyList();
    }
}

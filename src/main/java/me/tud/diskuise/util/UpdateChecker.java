package me.tud.diskuise.util;

import me.tud.diskuise.Diskuise;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    public final Diskuise plugin;
    public final int resourceId;

    public UpdateChecker(Diskuise plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }

    public void sendDownloadLatest(CommandSender sender) {
        TextComponent message = new TextComponent();
        TextComponent string = new TextComponent("Click here to download");
        string.setColor(ChatColor.GREEN);
        string.setUnderlined(true);
        string.setItalic(true);
        string.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Click to download").color(ChatColor.GRAY).italic(true).create()));
        string.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/diskuise.101529/"));
        message.addExtra(string);
        getVersion(version -> {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&6" + plugin.getDescription().getName() + " v" + version + " &7is now available. &cCurrent version: v" + plugin.getDescription().getVersion()));
            sender.spigot().sendMessage(message);
        });
    }

    public void checkForUpdates(CommandSender sender) {
        this.getVersion(version -> {
            if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) return;
            if (!sender.isOp()) return;
            this.sendDownloadLatest(sender);
        });
    }
}

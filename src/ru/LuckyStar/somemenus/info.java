package ru.LuckyStar.somemenus;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import ru.LuckyStar.main.Main;
import xyz.upperlevel.spigot.book.BookUtil;

public class info implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
        BookUtil.openPlayer(Bukkit.getPlayer(sender.getName()), book);
        return true;
    }
}

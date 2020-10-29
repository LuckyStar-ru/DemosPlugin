package ru.LuckyStar.somemenus;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ItemStack book = BookUtil.writtenBook()
                .author("DemosWorld")
                .title("Книга с правилами сервера")
                .pages(
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("Определения:"))
                                .newLine()
                                .add(new TextComponent("Донатер - игрок, получивший привилегию, которую возможно купить.\n" +
                                        "Администрация - наборный персонал сервера."))
                                .newLine()
                                .newLine()
                                .add(new TextComponent("§lВ нашем "))
                                .add(
                                        BookUtil.TextBuilder.of(" §nVK§r ")
                                                .color(ChatColor.DARK_AQUA)
                                                .style(ChatColor.BOLD)
                                                .onClick(BookUtil.ClickAction.openUrl("https://vk.com/topic-192248647_44060890"))
                                                .onHover(BookUtil.HoverAction.showText("§7Кликните, чтобы открыть VK"))
                                                .build()
                                )
                                .add(new TextComponent("§r§l также написаны правила сервера."))
                                .newLine()
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 1]")
                                                .onClick(BookUtil.ClickAction.changePage(2))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add("## §lОсновные положения§r ##")
                                .newLine()
                                .add(new TextComponent("§l1.1§r - Заходя на сервер, вы принудительно соглашаетесь с актуальными правилами проекта."))
                                .newLine()
                                .add(new TextComponent("§l1.2§r - Незнание правил не освобождает от ответственности."))
                                .newLine()
                                .add(new TextComponent("§l1.3§r - Администрация имеет право изменять актуальные правила в любой момент без уведомления игроков."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 1] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(3))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("§l1.4§r - Администрация не обязана предоставлять услуги, не указанные на сайте."))
                                .newLine()
                                .add(new TextComponent("§l1.5§r - Администрация имеет право наказать игрока по несуществующей причине в актуальных правилах."))
                                .newLine()
                                .add(new TextComponent("§l1.6§r - Администрация не несёт ответственность за игровые блокировки."))
                                .newLine()
                                .add(new TextComponent("§l1.7§r - §a§n§lРазрешено§r продавать что-либо за донат-валюту §7(но §7не за реальные деньги)§r."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 1] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(4))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                            .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("§l1.8§r - Запрещено клеветать на администрацию или донатеров."))
                                .newLine()
                                .add(new TextComponent("§l1.9§r - Администрация в праве выдать наказание на своё усмотрение."))
                                .newLine()
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 2] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(5))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("##§l Игровой аккаунт§r ##"))
                                .newLine()
                                .add(new TextComponent("§l2.1§r - Запрещена передача/продажа аккаунта третьим лицам."))
                                .newLine()
                                .add(new TextComponent("§l2.2§r - Запрещено содержание мата, расизма и оскорблений в нике. (Не касается донатеров)"))
                                .newLine()
                                .add(new TextComponent("§l2.3§r - Игрок несёт полную личную ответственность за аккаунт."))
                                .newLine()
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 3] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(6))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("##§l Действия игроков§r ##"))
                                .newLine()
                                .add(new TextComponent("§l3.1§r - Запрещено мошенничество, обман игроков на игровую валюту и реальные деньги."))
                                .newLine()
                                .add(new TextComponent("§l3.2§r - Запрещено использование модов, читов и т.п, которые облегчают игровой процесс."))
                                .newLine()
                                .add(new TextComponent("§l3.3§r - Игрок имеет право проводить раздачи, если эти вещи были добыты в режиме выживания."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 3] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(7))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("§l3.4§r - Запрещено использование багов или их распространение."))
                                .newLine()
                                .add(new TextComponent("§l3.5§r - Запрещено намеренно вызывать фризы/баги/глюки или пытаться крашнуть/выключить сервер."))
                                .newLine()
                                .add(new TextComponent("§l3.6§r - Запрещено использование нацистской символики, а так же флагов и гербов Третьего рейха."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 4] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(8))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("##§l Действия донатеров§r ##"))
                                .newLine()
                                .add(new TextComponent("§l4.1§r - Запрещено использование средств наказания без доказательств/причины."))
                                .newLine()
                                .add(new TextComponent("§l4.2§r - Запрещено использовать /tp без весомой причины."))
                                .newLine()
                                .add(new TextComponent("§l4.3§r - Запрещено вмешиваться в игровой процесс без причины."))
                                .newLine()
                                .add(new TextComponent("§l4.4§r - §a§l§nРазрешено выдавать ресурсы игрокам."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 5] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(9))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("##§l Чат§r ##"))
                                .newLine()
                                .add(new TextComponent("§l5.1§r - Запрещено рекламировать проекты, не относящиеся к WilfadCraft."))
                                .newLine()
                                .add(new TextComponent("§l5.2§r - Запрещено материться (в т.ч. завуалированно) в глобальном чате."))
                                .newLine()
                                .add(new TextComponent("§l5.3§r - Запрещено отправлять более 3-х сообщений с одинаковым содержанием за 3 минуты."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 5] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(10))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("§l5.4§r - Запрещено использование в сообщении заглавных букв более, чем 25% всего текста."))
                                .newLine()
                                .add(new TextComponent("§l5.5§r - Запрещено отправлять рекламные сообщения чаще, чем раз в 2 минуты."))
                                .newLine()
                                .add(new TextComponent("§l5.6§r - Запрещены выражения, унижающие достоинства кого-либо."))
                                .newLine()
                                .add(new TextComponent("§l5.7§r - Запрещено попрошайничество."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 5] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(11))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("§l5.8§r - Запрещено обсуждение политики, разжигание межнациональной розни или дискриминация кого-либо."))
                                .newLine()
                                .add(new TextComponent("§l5.9§r - Запрещено обсуждение/критика действий администрации, а также оскорбление администрации."))
                                .newLine()
                                .add(new TextComponent("§l5.10§r - Запрещено подстрекательство на нарушение правил третьих лиц."))
                                .newLine()
                                .add(new TextComponent("§l5.11§r - Запрещено выдавать себя за администрацию."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§a§lДалее [Категория 6] ⇉")
                                                .onClick(BookUtil.ClickAction.changePage(12))
                                                .onHover(BookUtil.HoverAction.showText("§7Далее"))
                                                .build()
                                )
                                .build(),
                        new BookUtil.PageBuilder()
                                .add(new TextComponent("##§l Прочее §r##"))
                                .newLine()
                                .add(new TextComponent("§l6.1§r - Если с кейса вам выпала более низкая привилегия, то предыдущую никто выдавать не обязан."))
                                .newLine()
                                .add(new TextComponent("§l6.2§r - Стоимость привилегий, выпавших из кейсов, ни коем образом не суммируется."))
                                .newLine()
                                .add(new TextComponent("§l6.3§r - Администрация имеет полное право отказать в предоставлении услуг без возврата денег за: нетерпение, оскорбление."))
                                .newLine()
                                .add(
                                        BookUtil.TextBuilder.of("§c§lНа первую страницу")
                                                .onClick(BookUtil.ClickAction.changePage(1))
                                                .onHover(BookUtil.HoverAction.showText("§7На первую страницу"))
                                                .build()
                                )
                                .build()
                )
                .build();
        BookUtil.openPlayer(Bukkit.getPlayer(sender.getName()), book);
        return true;
    }
}

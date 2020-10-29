package ru.LuckyStar.donate.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.LuckyStar.main.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.*;

public class dm implements CommandExecutor {
    public static HashMap<String, Double> money = new HashMap<>();
    public static HashMap<String, Double> givehistory = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "pay":
                    if (money.get(sender.getName().toLowerCase()) == null && !sender.equals(Bukkit.getConsoleSender())) {
                        sender.sendMessage(new String[] {Main.prefix + " §cВы не можете пользоваться услугами §b§lDonate§aMoney§c, пока не приобрели ни одного рубля.",
                                Main.prefix + " §cПополнить баланс можно на сайте: §a§lWilfad-Craft.ru"});
                        return true;
                    }
                    if (args.length == 3) {
                        if (!Main.isNumber(args[2])) {
                            sender.sendMessage(Main.prefix + " §cНеправильная сумма.");
                            return true;
                        }
                        if (money.get(sender.getName().toLowerCase()) < Double.parseDouble(args[2])) {
                            sender.sendMessage(Main.prefix + " §cТранзакция отменена. Вы не имеете столько средств.");
                        }
                        money.replace(sender.getName(), money.get(sender.getName().toLowerCase()) - Double.parseDouble(args[2]));
                        if (money.get(args[1]) != null) {
                            money.replace(args[1], money.get(args[1]) + Double.parseDouble(args[2]));
                            sender.sendMessage(Main.prefix + " §aТранзакция успешно завершена.");
                        } else {
                            money.put(args[1], Double.parseDouble(args[2]));
                                sender.sendMessage(Main.prefix + " §aТранзакция успешно завершена.");
                        }
                    } else {
                        sender.sendMessage(new String[]{Main.prefix + " §fИспользование команды: §6/dm pay §l[ник] [сумма]", Main.prefix + " §fУчтите, что обман на эту валюту §n§lстрого наказывается§f администрацией."});
                    }
                    break;
                case "buy":
                    if (money.get(sender.getName().toLowerCase()) == null && !sender.equals(Bukkit.getConsoleSender())) {
                        sender.sendMessage(new String[] {Main.prefix + " §cВы не можете пользоваться услугами §b§lDonate§aMoney§c, пока не приобрели ни одного рубля.",
                                Main.prefix + " §cПополнить баланс можно на сайте: §a§lWilfad-Craft.ru"});
                        return true;
                    }
                    if (args.length > 1) {
                        double discount = getDiscount(sender.getName());
                        Double cost;
                        String text;
                        switch (args[1].toLowerCase()) {
                            case "vip":
                                cost = 5 - discount;
                                text = "§2ВИП";
                                break;
                            case "premium":
                                cost = 10 - discount;
                                text = "§dПремиум";
                                break;
                            case "creative":
                                cost = 35 - discount;
                                text = "§aКреатив";
                                break;
                            case "moderator":
                                cost = 45 - discount;
                                text = "§3Модератор";
                                break;
                            case "admin":
                                cost = 75 - discount;
                                text = "§cАдмин";
                                break;
                            case "osnovatel":
                                cost = 100 - discount;
                                text = "§eОснователь";
                                break;
                            case "head":
                                cost = 210 - discount;
                                text = "§4Хед";
                                break;
                            case "yprav":
                                cost = 350 - discount;
                                text = "§6§lУправляющий";
                                break;
                            case "demokrat":
                                cost = 600 - discount;
                                text = "§a§l❅ §c§l§nДемократ§a§l ❅";
                                break;
                            case "cmd_protect":
                                cost = 500.00;
                                text = "§e§lPROTECT";
                                break;
                            case "random":
                                cost = 35.00;
                                text = "§e§lСЛУЧАЙНУЮ ПРИВИЛЕГИЮ";
                                break;
                            default:
                                sender.sendMessage(Main.prefix + " §cНеверный донат!");
                                return true;
                        }
                        if (cost <= 0) {
                            sender.sendMessage(Main.prefix + " §cВы уже имеете эту привилегию или выше.");
                            break;
                        }
                        if (money.get(sender.getName().toLowerCase()) >= cost) {
                            Bukkit.getConsoleSender().sendMessage("BUY COST -> " + cost + " | DISCOUNT -> |" + discount);
                            money.replace(sender.getName().toLowerCase(), money.get(sender.getName().toLowerCase()) - cost);
                            if (args[1].equals("cmd_protect")) {
                                sender.sendMessage(Main.prefix + " §aВы успешно приобрели команду §7/§e§lrgprotect [регион]");
                                sender.sendMessage(Main.prefix + " §cКоманду разрешено использовать §nТОЛЬКО ДЛЯ СВОИХ РЕГИОНОВ\n" + Main.prefix + " §cКому попало ставить защиту §nЗАПРЕЩЕНО.");
                                sender.sendMessage(Main.prefix + " §7(Исключения по выдаче можно узнать подробнее, задав соответствующий вопрос в группе ВК)");
                                Bukkit.getConsoleSender().sendMessage("DONATION_ALERT -> " + sender.getName() + " | PROTECT");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " add rgprotect.protect");
                            } else if (args[1].equals("random")) {
                                Random random = new Random();
                                String privilege;
                                sender.sendMessage(Main.prefix + " §aВы успешно приобрели " + text + "§a.");
                                switch (random.nextInt(7)) {
                                    case 0:
                                        text = "§2ВИП";
                                        privilege = "vip";
                                        break;
                                    case 1:
                                        text = "§dПремиум";
                                        privilege = "premium";
                                        break;
                                    case 2:
                                        text = "§aКреатив";
                                        privilege = "creative";
                                        break;
                                    case 3:
                                        text = "§3Модератор";
                                        privilege = "moderator";
                                        break;
                                    case 4:
                                        text = "§cАдмин";
                                        privilege = "admin";
                                        break;
                                    case 5:
                                        text = "§eОснователь";
                                        privilege = "osnovatel";
                                        break;
                                    case 6:
                                        text = "§4Хед";
                                        privilege = "head";
                                        break;
                                    default:
                                        return true;
                                }
                                Bukkit.getConsoleSender().sendMessage("DONATION_ALERT -> " + sender.getName() + " | " + privilege);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " group set " + privilege);
                                Bukkit.broadcastMessage(Main.prefix + " §aИгрок §l" + sender.getName() + " §aвыбил из случайного доната §l" + text + "§a!");
                            } else {
                                Bukkit.getConsoleSender().sendMessage("BUY COST -> " + cost + " | DISCOUNT -> |" + discount);
                                sender.sendMessage(Main.prefix + " §aВы успешно приобрели привилегию " + text + "§a.");
                                Bukkit.getConsoleSender().sendMessage("DONATION_ALERT -> " + sender.getName() + " | " + args[1]);
                                Bukkit.broadcastMessage(Main.prefix + " §aИгрок §l" + sender.getName() + " §aприобрел донат §l" + text + "§a!");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + sender.getName() + " group set " + args[1]);
                            }
                        } else {
                            sender.sendMessage(Main.prefix + " §cТранзакция отменена. Вы не имеете столько средств. §7(Недостаточно: §c" + Math.abs(money.get(sender.getName().toLowerCase()) - cost) + "§7)\n" + Main.prefix + "§fКупить донат-валюту можно на сайте → §a§lWilfad-Craft.ru");
                        }
                        break;
                    } else {
                        sender.sendMessage(Main.prefix + " §aПишите в сообщении группы, если хотите получить уникальные предложения.");
                    }
                    break;
                case "bal":
                case "balance":
                    if (args.length == 1) {
                        if (money.get(sender.getName().toLowerCase()) != null) {
                            sender.sendMessage(Main.prefix + " §fБаланс донат валюты: §a§l" + money.get(sender.getName().toLowerCase()));
                        } else {
                            sender.sendMessage(Main.prefix + " §fВы никогда не имели донат валюту.");
                        }
                    } else {
                        if (money.get(args[1].toLowerCase()) != null) {
                            sender.sendMessage(Main.prefix + " §fБаланс донат валюты игрока §l" + args[1] + "§f: §a§l" + money.get(args[1].toLowerCase()));
                        } else {
                            sender.sendMessage(Main.prefix + " §fЭтот игрок никогда не имел донат валюты.");
                        }
                    }
                    break;
                case "give":
                    if (sender.hasPermission("dm.give")) {
                        if (givehistory.get(args[1].toLowerCase()) != null) {
                            givehistory.replace(args[1].toLowerCase(), givehistory.get(args[1].toLowerCase()) + Double.parseDouble(args[2]));
                        } else {
                            givehistory.put(args[1].toLowerCase(), Double.parseDouble(args[2]));
                        }
                        if (money.get(args[1].toLowerCase()) != null) {
                            money.replace(args[1].toLowerCase(), money.get(args[1].toLowerCase()) + Double.parseDouble(args[2]));
                        } else {
                            money.put(args[1].toLowerCase(), Double.parseDouble(args[2]));
                        }
                        Bukkit.getConsoleSender().sendMessage("DONATION_ALERT -> " + args[1].toLowerCase() + " | MONEY GIVE | " + args[2]);
                    } else {
                        return true;
                    }
                    break;
                case "top":
                    int k = 1;
                    sender.sendMessage(Main.prefix + " §f§lТОП по донату:");
                    for (Map.Entry<String, Double> i : sortByValue(dm.givehistory).entrySet()) {
                        sender.sendMessage(Main.prefix + " §e§l" + k + " §7| §e§l" + i.getKey() + " §f-> §e§l" + i.getValue() + " §fруб.");
                        k++;
                        if (k == 11) {
                            break;
                        }
                    }
                    break;
                default:
                    sender.sendMessage(Main.prefix + " §fИспользование команды:\n§e/dm §lbalance (ник)\n§e/dm §lpay [ник] [сумма]");
                    sender.sendMessage(Main.prefix + " §fКупить что-то за эту валюту возможно прямо в меню -> §e§l/donate§f !");
            }
        } else {
            sender.sendMessage(Main.prefix + " §fИспользование команды:\n§e/dm §lbalance (ник)\n§e/dm §lpay [ник] [сумма]\n§e/dm §ltop");
            sender.sendMessage(Main.prefix + " §fКупить что-то за эту валюту возможно прямо в меню -> §e§l/donate§f !");
        }
        return true;
    }

    public static Map<String, Double> sortByValue(Map<String, Double> unsortedMap) {
        Map<String, Double> sortedMap = new TreeMap<String, Double>(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    public static Double getTopInt(int z) {
        int k = 1;
        for (Map.Entry<String, Double> i : sortByValue(dm.givehistory).entrySet()) {
            if (k == z) {
                return i.getValue();
            }
            k++;
        }
        return 0.0;
    }
    public static String getTopName(int z) {
        int k = 1;
        for (Map.Entry<String, Double> i : sortByValue(dm.givehistory).entrySet()) {
            if (k == z) {
                return i.getKey();
            }
            k++;
        }
        return "Ещё никто не занял топ " + z;
    }
    public static Double getDiscount(String name) {
        if (PermissionsEx.getUser(name).inGroup("demokrat")) {
            return 600.00;
        } else if (PermissionsEx.getUser(name).inGroup("yprav")) {
            return 350.00;
        } else if (PermissionsEx.getUser(name).inGroup("head")) {
            return 210.00;
        } else if (PermissionsEx.getUser(name).inGroup("osnovatel")) {
            return 100.00;
        } else if (PermissionsEx.getUser(name).inGroup("admin")) {
            return 75.00;
        } else if (PermissionsEx.getUser(name).inGroup("moderator")) {
            return 45.00;
        } else if (PermissionsEx.getUser(name).inGroup("creative")) {
            return 35.00;
        } else if (PermissionsEx.getUser(name).inGroup("premium")){
            return 10.00;
        } else if (PermissionsEx.getUser(name).inGroup("vip")) {
            return 5.00;
        }
        return 0.0;
    }
}

package ru.LuckyStar.sqlite;

import org.bukkit.Bukkit;
import ru.LuckyStar.bonus.HandlerB;
import ru.LuckyStar.bonus.commands;
import ru.LuckyStar.donate.command.dm;
import ru.LuckyStar.main.Main;
import ru.LuckyStar.punishment.commands.fullban;
import ru.LuckyStar.punishment.commands.fullbanip;
import ru.LuckyStar.rgprotect.event.HandlerRG;

import java.io.File;
import java.sql.*;
import java.util.Map;

public class SQLDataBase {

    public static String url;

    public SQLDataBase() {
        try {
            Main.plugin.getDataFolder().mkdirs();
            url = "jdbc:sqlite:" + Main.plugin.getDataFolder() + File.separator + "database.db";
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_bans_ip (`ip` varchar(255) NOT NULL UNIQUE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_bans_name (`name` varchar(255) NOT NULL UNIQUE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_donate (`name` varchar(255) NOT NULL UNIQUE, `donate` int(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_bonus (`ip` varchar(255) NOT NULL, `name` varchar(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_region (`name` varchar(255) NOT NULL UNIQUE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_ban (`string` varchar(255) NOT NULL UNIQUE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS demos_Monastery (`name` varchar(255) NOT NULL UNIQUE, `donate` int(255))");
            statement.close();
            getConnection().close();
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("Неудалось создать базу данных." + e.getCause() + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void getPunish() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet demos_bans_name = statement.executeQuery("SELECT * FROM demos_bans_name");
            for (int i = 1; demos_bans_name.next(); i++) {
                fullban.fullBanList_names.add(demos_bans_name.getString(i));
            }
            ResultSet demos_bans_ip = statement.executeQuery("SELECT * FROM demos_bans_ip");
            for (int i = 1; demos_bans_ip.next(); i++) {
                fullbanip.fullBanList_ips.add(demos_bans_ip.getString(i));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void savePunish() {
        try {
            Connection connection = getConnection();
            Statement remover = connection.createStatement();
            PreparedStatement statement;
            remover.executeUpdate("DELETE FROM demos_bans_name");
            for (String i : fullban.fullBanList_names) {
                statement = connection.prepareStatement("INSERT INTO demos_bans_name (name) VALUES (?)");
                statement.setString(1, i);
                statement.executeUpdate();
            }
            remover.executeUpdate("DELETE FROM demos_bans_ip");
            for (String i : fullbanip.fullBanList_ips) {
                statement = connection.prepareStatement("INSERT INTO demos_bans_name (ip) VALUES (?)");
                statement.setString(1, i);
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getDM() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet demos_bans_donate = statement.executeQuery("SELECT * FROM demos_donate");
            while (demos_bans_donate.next()) {
                dm.money.put(demos_bans_donate.getString(1), demos_bans_donate.getDouble(2));
            }//demos_Monastery
            ResultSet demos_history = statement.executeQuery("SELECT * FROM demos_Monastery");
            while (demos_bans_donate.next()) {
                dm.givehistory.put(demos_history.getString(1), demos_history.getDouble(2));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveDM() {
        try {
            Connection connection = getConnection();
            Statement remover = connection.createStatement();
            PreparedStatement statement;
            remover.executeUpdate("DELETE FROM demos_donate");
            for (Map.Entry<String, Double> i : dm.money.entrySet()) {
                Bukkit.getConsoleSender().sendMessage(i.getKey() + " ||| " + i.getValue());
                statement = connection.prepareStatement("INSERT INTO demos_donate (name, donate) VALUES (?, ?)");
                statement.setString(1, i.getKey());
                statement.setDouble(2, i.getValue());
                statement.executeUpdate();
            }
            remover.executeUpdate("DELETE FROM demos_Monastery");
            for (Map.Entry<String, Double> i : dm.givehistory.entrySet()) {
                Bukkit.getConsoleSender().sendMessage(i.getKey() + " ||| " + i.getValue());
                statement = connection.prepareStatement("INSERT INTO demos_Monastery (name, donate) VALUES (?, ?)");
                statement.setString(1, i.getKey());
                statement.setDouble(2, i.getValue());
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getRegions() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet demos_bans_name = statement.executeQuery("SELECT * FROM demos_region");
            while (demos_bans_name.next()) {
                HandlerRG.protectedrg.add(demos_bans_name.getString(1));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveRegions() {
        try {
            Connection connection = getConnection();
            Statement remover = connection.createStatement();
            PreparedStatement statement;
            remover.executeUpdate("DELETE FROM demos_region");
            for (String i : HandlerRG.protectedrg) {
                statement = connection.prepareStatement("INSERT INTO demos_region (name) VALUES (?)");
                statement.setString(1, i);
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getUsesIP() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet demos_bans_name = statement.executeQuery("SELECT * FROM demos_bonus");
            while (demos_bans_name.next()) {
                commands.usesIPs.add(demos_bans_name.getString(1));
                commands.usesNames.add(demos_bans_name.getString(2));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveUsesIP() {
        try {
            Connection connection = getConnection();
            Statement remover = connection.createStatement();
            PreparedStatement statement;
            remover.executeUpdate("DELETE FROM demos_bonus");
            for (int i = commands.usesIPs.size() - 1; i >= 0; i--) {
                statement = connection.prepareStatement("INSERT INTO demos_bonus (ip, name) VALUES (?, ?)");
                statement.setString(1, commands.usesIPs.get(i));
                statement.setString(2, commands.usesNames.get(i));
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

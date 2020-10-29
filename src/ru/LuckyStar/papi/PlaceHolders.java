package ru.LuckyStar.papi;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import ru.LuckyStar.donate.command.dm;
import ru.LuckyStar.main.Main;

public class PlaceHolders extends PlaceholderExpansion {
    private Main plugin;

    public PlaceHolders(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "demos";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean register(){
        return super.register();
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.entity.Player Player}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if (player == null){
            return "";
        }

        if(identifier.equals("dmoney")){
            if (dm.money.get(player.getName().toLowerCase()) != null) {
                return "" + dm.money.get(player.getName().toLowerCase()) + "§r";
            } else {
                return "0§r";
            }
        }
        if(identifier.startsWith("dmtopnameF")) {
            return dm.getTopName(Integer.parseInt(identifier.split("F")[1]));
        }
        if (identifier.startsWith("dmtopintF")) {
            return String.valueOf(dm.getTopInt(Integer.parseInt(identifier.split("F")[1])));
        }
        return null;
    }

}


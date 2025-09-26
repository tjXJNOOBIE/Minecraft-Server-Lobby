package info.techwizmatt.ServerSelector;

import info.techwizmatt.ServerCore.API.CoinAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;

/**
 * Created by TJ on 3/30/2018.
 */
public class CosmeticAPI {

    public static Map<String, Integer> cosmetics = new HashMap<>();
    public static HashMap<String, String> cosmeticsdata = new HashMap<>();
    public static List<String> names = new ArrayList<>();
    public static List<String> items = new ArrayList<>();

    public static void GetCosmeticsData() {
        String mat = "";
        String cosmetic = "";
        try {
            PreparedStatement statement = info.techwizmatt.ServerCore.Main.sqlConnection.prepareStatement("SELECT * FROM cosmetics;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mat = rs.getString("ITEM");
                cosmetic = rs.getString("COSMETIC");
                cosmeticsdata.put(mat, cosmetic);
                names.add(cosmetic);
                items.add(mat);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void GetCosmetics() {
        int p = 0;

        try {
            String cosmetic;
            PreparedStatement error = info.techwizmatt.ServerCore.Main.sqlConnection.prepareStatement("SELECT * FROM cosmetics;");
            ResultSet rs = error.executeQuery();
            while (rs.next()) {
                cosmetic = rs.getString("COSMETIC");
                p = rs.getInt("PRICE");
                cosmetics.put(cosmetic, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String GetType(String cosmetic) {
        try {
            Statement statement = Main.sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cosmetics WHERE cosmetic='" + cosmetic + "'");
            while (rs.next()) {
                cosmetic = rs.getString("TYPE");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cosmetic;
    }


    public static String GetCosmeticNameFromID(int id) {
        String cosmetic = "";
        try {
            Statement statement = Main.sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cosmetics WHERE ID='" + id + "'");
            while (rs.next()) {
                cosmetic = rs.getString("TYPE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cosmetic;
    }


    public static int GetID(String cosmetic) {
        int id = Integer.valueOf(0);
        try {
            Statement error = info.techwizmatt.ServerCore.Main.sqlConnection.createStatement();
            ResultSet rs = error.executeQuery("SELECT * FROM cosmetics WHERE cosmetic='" + cosmetic + "'");
            while (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public static String GetCurrentHat(String uuid) {
        String cosmetic = "";
        try {
            Statement statement = Main.sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_settings WHERE UUID='" + uuid + "'");
            while (rs.next()) {
                cosmetic = rs.getString("CURRENTHAT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cosmetic;
    }


    public static String GetCurrentEffects(String uuid) {
        String cosmetic = "";
        try {
            Statement statement = Main.sqlConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_settings WHERE UUID='" + uuid + "'");
            while (rs.next()) {
                cosmetic = rs.getString("CURRENTEFFECT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cosmetic;
    }

    public static Map<String, Integer> GetCosmeticMap() {
        return cosmetics;
    }

    public static HashMap<String, String> GetCosmeticDataMap() {
        return cosmeticsdata;
    }

    public static List<String> GetPrices() {
        List<String> price = new ArrayList<>();
        Iterator iterator = cosmetics.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Bukkit.getLogger().log(Level.INFO, pair.getKey() + " = " + pair.getValue());
            price.add(pair.getValue() + "");
            iterator.remove();
        }
        return price;
    }

    public static List<String> GetDisplayNames() {
        List<String> names = new ArrayList<>();
        Iterator iterator = cosmeticsdata.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Bukkit.getLogger().log(Level.INFO, pair.getKey() + " = " + pair.getValue());
            names.add(pair.getValue() + "");
            iterator.remove();
        }
        return names;
    }

    public static String GetItems() {

        StringBuilder sb = new StringBuilder();
        for(String str : items){
            sb.append(str).append(", ");

        }

        return sb.toString();
    }

    public static String GetNames() {
        for (int i = 0; i < names.size(); i++) {
            names.get(i);
        }
        return "Error";
    }

    public void addItems(Player user) {

    }
}


package info.techwizmatt.ServerSelector;

import info.techwizmatt.ServerCore.*;
import info.techwizmatt.ServerCore.Main;
import info.techwizmatt.ServerCore.SqlDB.DatabaseCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by TJ on 1/5/2018.
 */
public class Keys {

    public static int GetKeys(Player user) {
        DatabaseCore.SQLCONNECTIONOPEN();

        try {
            Statement error = Main.sqlConnection.createStatement();
            ResultSet ConnectionRefusedError1 = error.executeQuery("SELECT * FROM `players_data` WHERE `UUID`= \'" + user.getUniqueId().toString() + "\';");
            if(ConnectionRefusedError1.next()) {
                return ConnectionRefusedError1.getInt("crateKeys");
            }
        } catch (SQLException var3) {
            Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + var3);
            String ConnectionRefusedError = "" + var3;
            ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
            Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
            if(ConnectionRefusedError == "com.mysql.jdbc.exceptions.jdbc4.Communications Exception") {
                Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
            }
        }

        return 0;
    }

    public static void setKeys(Player user, int NewKeyAmount) {
        DatabaseCore.SQLCONNECTIONOPEN();

        try {
            Statement error = info.techwizmatt.ServerCore.Main.sqlConnection.createStatement();
            error.executeUpdate("UPDATE `players_data` SET `crateKeys`=\'" + NewKeyAmount + "\' WHERE `UUID` = \'" + user.getUniqueId().toString() + "\';");
        } catch (SQLException var4) {
            Bukkit.getLogger().warning("MYSQL: SQLException Debug Command- " + var4);
            String ConnectionRefusedError = "" + var4;
            ConnectionRefusedError = ConnectionRefusedError.split(":")[0];
            Bukkit.getLogger().warning("Hey Matt - " + ConnectionRefusedError);
        }

    }
    public static void addKeys(Player user, int AddingAmount) {
        int CurrentKeys = GetKeys(user);
        int NewKeyAmount = CurrentKeys + AddingAmount;
        setKeys(user, NewKeyAmount);
    }



    public static void subtractKeys(Player user, int SubtractingAmount) {
        int CurrentKeys = GetKeys(user);
        int NewKeyAmount = CurrentKeys - SubtractingAmount;
        setKeys(user, NewKeyAmount);
    }




}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acesso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author friend
 */
public class TesteAcessoSQLite {

    public static final String URL = "jdbc:sqlite:C:\\development\\java\\projects\\dw2ed\\bd\\java\\sqlite\\companies_1000.db";

    public static void main(String[] args) throws ClassNotFoundException {

        Connection con;
        Statement st;
        ResultSet result;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            result = st.executeQuery("select * from compania");
            while (result.next()) {
                for (int i = 1; i <= 11; i++) {
                    System.out.print(result.getString(i) + " | ");
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.print("Erro no SQL: " + ex.getMessage());
        }
    }

}

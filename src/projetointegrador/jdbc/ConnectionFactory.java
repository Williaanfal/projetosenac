/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointegrador.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author willian.falcao
 */

public class ConnectionFactory {
    public static Connection getConnection(){
        final String url = "jdbc:mysql://localhost:3306/nextleveldb?verifyServerCertificate=false&useSSL=true";
        final String user = "teste";
        final String password = "123456789";
        
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //lançar um erro na tela do usuário quando o programa executa
            throw new RuntimeException(e);
        }
    }
}

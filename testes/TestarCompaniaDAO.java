/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import dao.CompaniaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Compania;

/**
 *
 * @author friend
 */
public class TestarCompaniaDAO {
    
    CompaniaDAO pdao = new CompaniaDAO();
    
    public void listarCompanias(){
        System.out.println("Lista de Companias:");       
        for (Compania p : pdao.retornarCompanias()){;
            System.out.println(p);
        }        
        System.out.println("Fim da lista de Companias");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        TestarCompaniaDAO teste = new TestarCompaniaDAO();
        CompaniaDAO pdao = new CompaniaDAO();
        //importa do csv
//        try {
//            pdao.importFromFile();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TestarCompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(TestarCompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }

            //adiciona compania
//        Compania c = new Compania();
//        c.setNome("Fui adicionada");
//        System.out.println(pdao.incluirCompania(c));

        //limpa todos os registros
//        pdao.clearAllData();

        System.out.println("* Listar Companias");
        teste.listarCompanias();
        System.out.println("FIM DOS TESTES");
    }
}

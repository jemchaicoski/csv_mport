/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import dao.CompaniaDAO;
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
//        System.out.println("Fim da lista de Companias");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        TestarCompaniaDAO teste = new TestarCompaniaDAO();
        System.out.println("* Listar Companias");
        teste.listarCompanias();
        System.out.println("FIM DOS TESTES");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import modelo.Compania;

public interface CompaniaDAOInterface {    
    public ArrayList<Compania> retornarCompanias();	
    public boolean incluirCompania(Compania nova);	
    //public boolean removerCompania(String nome);	   
    //public Compania buscarCompania(String nome);
    //public boolean AtualizarCompania(Compania novosDados);
}

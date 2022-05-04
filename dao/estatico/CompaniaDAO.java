package dao.estatico;

import dao.CompaniaDAOInterface;
import java.util.ArrayList;
import modelo.Compania;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class CompaniaDAO implements CompaniaDAOInterface{

    @Override
    public ArrayList<Compania> retornarCompanias() {
        ArrayList<Compania> retorno = new ArrayList<Compania>();
        retorno.add(new Compania("Compania do DAO"));
        return retorno;
    }

    @Override
    public boolean incluirCompania(Compania nova) {
        return true;
    }

//    @Override
//    public boolean removerCompania(String id) {
//        return true;
//    }
    
//    @Override
//    public Compania buscarCompania(String cpf) {
//        return new Compania();
//    } 
//    
//    @Override
//    public boolean AtualizarCompania(Compania novosDados) {
//        return true;
//    }

}
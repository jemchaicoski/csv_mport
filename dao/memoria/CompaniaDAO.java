/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.memoria;

/**
 *
 * @author friend
 */
import dao.CompaniaDAOInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Compania;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.XsiNilLoader.Array;
import java.sql.PreparedStatement;

public class CompaniaDAO implements CompaniaDAOInterface {

    private ArrayList<Compania> companias = new ArrayList();
    public static final String URL = "jdbc:sqlite:C:\\development\\java\\projects\\dw2ed\\bd\\java\\sqlite\\companies.db";


    @Override
    public ArrayList<Compania> retornarCompanias(){
        Connection con;
        Statement st;
        
        ArrayList result = new ArrayList<>();
        
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            ResultSet resultFromQuery = st.executeQuery("select * from compania");
            
            while(resultFromQuery.next()){
                result.add(toEntity(resultFromQuery));
            }
        } catch (SQLException ex) {
            System.out.print("Erro no SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public boolean incluirCompania(Compania nova) {
        Connection con;
        Statement st;
        
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO compania (\"\",name,domain,\"year founded\",industry,\"size range\"," +
                "locality,country,\"linkedin url\",\"current employee estimate\",\"total employee estimate\") " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)"
            );
            populateStatement(ps, nova);
            ps.executeUpdate();
            return true;
            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    protected void populateStatement(PreparedStatement preparedStatement, Compania compania) throws SQLException {
        preparedStatement.setString(1, compania.getId());
        preparedStatement.setString(2, compania.getNome());
        preparedStatement.setString(3, compania.getDominio());
        preparedStatement.setString(4, compania.getAno());
        preparedStatement.setString(5, compania.getIndustria());
        preparedStatement.setString(6, compania.getTamanho());
        preparedStatement.setString(7, compania.getLocalizacao());
        preparedStatement.setString(8, compania.getPais());
        preparedStatement.setString(9, compania.getLinkedin());
        preparedStatement.setInt(10, compania.getEmpregados_atual());
        preparedStatement.setInt(11, compania.getEmpregados_total());
    }
    
    public Compania toEntity(ResultSet result){
        Compania compania = new Compania(); 
        try{
            compania.setId(result.getString(""));
            compania.setAno(result.getString("Year founded"));
            compania.setDominio(result.getString("domain"));
            compania.setEmpregados_atual(result.getInt("current employee estimate"));
            compania.setEmpregados_total(result.getInt("total employee estimate"));
            compania.setNome(result.getString("name"));
            compania.setIndustria(result.getString("industry"));
            compania.setLinkedin(result.getString("linkedin url"));
            compania.setPais(result.getString("country"));
            compania.setLocalizacao(result.getString("locality"));
            compania.setTamanho(result.getString("size range"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compania;
    }
    
    public static void importFromFile() throws ClassNotFoundException, SQLException{
        File arquivoCSV = new File("C:\\development\\java\\projects\\dw2ed\\fund\\webap\\monojava\\04-crd-com-dois-daos\\src\\java\\companies_sorted.csv");
        Connection con;
        Statement st;
        ArrayList result = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            String linha = new String();
            Scanner leitor = new Scanner(arquivoCSV);
            leitor.nextLine();
//            while(leitor.hasNext()){
                    linha = leitor.nextLine();
                    String[] vetor = linha.split(",");              
                    //name,domain,year founded,industry,size range,locality,country,linkedin url,current employee estimate,total employee estimate
                    Compania c = new Compania(
                    vetor[0]!=null ? vetor[0]: "",
                    vetor[1]!=null ? vetor[1]: "",
                    vetor[2]!=null ? vetor[2]: "",
                    vetor[3]!=null ? vetor[3]: "",
                    vetor[4]!=null ? vetor[4]: "",
                    vetor[5]!=null ? vetor[5]: "",
                    vetor[6]!=null ? vetor[6]: "",
                    vetor[7]!=null ? vetor[7]: "",
                    vetor[8]!=null ? vetor[8]: "");
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            new CompaniaDAO().incluirCompania(c);
//            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
        public void clearAllData(){
        try {
            Connection con;
            Statement st;
            
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            st.executeUpdate("delete from compania");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

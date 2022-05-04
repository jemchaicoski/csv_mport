/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.memoria;

/**
 *
 * @author friend
 */
import static acesso.TesteAcessoSQLite.URL;
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

public class CompaniaDAO implements CompaniaDAOInterface {

    private ArrayList<Compania> companias = new ArrayList();
    public static final String URL = "jdbc:sqlite:C:\\development\\java\\projects\\dw2ed\\bd\\java\\sqlite\\companies_1000.db";


    @Override
    public ArrayList<Compania> retornarCompanias(){
        Connection con;
        Statement st;
        
        try {
            importFromFile();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CompaniaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        try {
            companias.add(nova);
            return true;
        } catch (Exception e) {
            System.out.println("depuração: " + e.getMessage());
            return false;
        }
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
            while(leitor.hasNext()){
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
            st.executeQuery("insert into compania("
                    + "id,"
                    + "name,"
                    + "domain,"
                    + "year founded,"
                    + "industry,"
                    + "size range,"
                    + "locality,"
                    + "country,"
                    + "linkedin url)"
                    + "VALUES "
                    + c.getId() + ","
                    + c.getNome()+ ","
                    + c.getDominio()+ ","
                    + c.getAno()+ ","
                    + c.getIndustria()+ ","
                    + c.getTamanho()+ ","
                    + c.getLocalizacao()+ ","
                    + c.getPais()+ ","
                    + c.getLinkedin()+ ","
                    + "0" + ","
                    + "0"
                    ); 
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexaobd.ConexaoBDPostgres;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class Cidade {
       
    private String nomeCidade;
    private String estado ;
    

    public Cidade() {
    }

    public Cidade( String nomeCidade, String estado) {        
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }   

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
           
    public void inserirCidade() throws SQLException{
 
       ConexaoBDPostgres conexao = new ConexaoBDPostgres();                   
       
       PreparedStatement ps = null;
       String sql = null; 
             
       sql = "INSERT INTO cidade (cidade,estado)"
               + "VALUES(?,?);";        
        ps = conexao.getConexao().prepareStatement(sql);
        ps.setString(1, getNomeCidade());
        ps.setString(2, getEstado());
        
        ps.executeUpdate();
        ps.close();
       // banco.disconnect(); //desconecta o banco        
        JOptionPane.showMessageDialog( null, "Cidade Cadastrada com Sucesso.", "Aviso", JOptionPane.PLAIN_MESSAGE );
        
        conexao.disconnect();
     }
    
    public ResultSet buscarCidade(String tipoBusca, int id, String nome) throws SQLException{

     PreparedStatement ps = null;
     ResultSet rs = null;
     String sql = null;      
     
       ConexaoBDPostgres conexao = new ConexaoBDPostgres();   //  Conexao com o Banco
     
    if(tipoBusca.equals("nome"))
    {
        sql = " SELECT id_cidade, cidade, estado FROM cidade"+
              " WHERE  cidade  like "+ "'"+nome+"%'" ;
    }else{
       
        sql = " SELECT id_cidade, cidade,estado FROM cidade"+
              " WHERE id_cidade = " + id ;
    }
     //System.out.println(sql);
     ps = conexao.getConexao().prepareStatement(sql);     
     rs = ps.executeQuery();   

     conexao.disconnect();
     return rs;     
  }    
 
    
    
    
    
}

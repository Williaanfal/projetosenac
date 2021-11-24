/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import java.awt.HeadlessException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Enderecos;

/**
 *
 * @author Leonardo Drews Montibeller at ldmontibeller@gmail.com
 */
public class EnderecosDAO {

    //Atributos
    private Connection conexao;

    //Construtor
    public EnderecosDAO() {
        //1º passo: disponibilizar uma conexão com o BD
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrarEndereco(Enderecos obj) {
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into tb_enderecos(cep, rua, numero, complemento, bairro, cidade, uf, id_cliente)"
                    + "values (?,?,?,?,?,?,?,?)";

            //3º passo: preparar o comando SQL para o driver
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCep());
            comando.setString(2, obj.getRua());
            comando.setInt(3, obj.getNumero());
            comando.setString(4, obj.getComplemento());
            comando.setString(5, obj.getBairro());
            comando.setString(6, obj.getCidade());
            comando.setString(7, obj.getUf());
            comando.setInt(8, obj.getCliente().getId());

            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();

            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Enderecos> listarEnderecos() {
        try {
            //1º passo: criar uma lista para armazenar os endereços
            List<Enderecos> listaEnderecos = new ArrayList<>();
            
            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de endereços
            String sql = "select * from tb_enderecos";
            
            //3º passo: preparar o comando colocando na conexao que será
            //utilizada para executá-lo no BD
            PreparedStatement comando = conexao.prepareStatement(sql);
            
            //4º passo: quando usamos JDBC, o resultado de um comando select 
            //precisa ser armazenado em um objeto do tipo ResultSet
            ResultSet rs = comando.executeQuery();
            
            //5º passo: criar um laço de repetição para adicionar os itens do
            //ResultSet na lista criada no primeiro passo.
            while(rs.next()){ //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Enderecos obj = new Enderecos();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setCep(rs.getString("cep"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));
                //obj.getCliente().setId(rs.getInt("id_cliente"));              
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaEnderecos.add(obj);       
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return listaEnderecos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public List<Enderecos> listarEnderecos(String busca) {
        try {
            //1º passo: criar uma lista para armazenar os endereços
            List<Enderecos> listaEnderecos = new ArrayList<>();
            
            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de endereços
            String sql = "select * from tb_enderecos WHERE concat_ws(id, cep, rua, numero, complemento, bairro, cidade, uf, id_cliente) like ?";
            
            //3º passo: preparar o comando colocando na conexao que será
            //utilizada para executá-lo no BD
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1,'%'+busca+'%');
            
            //4º passo: quando usamos JDBC, o resultado de um comando select 
            //precisa ser armazenado em um objeto do tipo ResultSet
            ResultSet rs = comando.executeQuery();
            
            //5º passo: criar um laço de repetição para adicionar os itens do
            //ResultSet na lista criada no primeiro passo.
            while(rs.next()){ //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Enderecos obj = new Enderecos();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setCep(rs.getString("cep"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));
                //obj.getCliente().setId(rs.getInt("id_cliente"));              
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaEnderecos.add(obj);       
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return listaEnderecos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}

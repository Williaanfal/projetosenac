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
import jdk.dynalink.linker.support.Guards;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Clientes;

/**
 *
 * @author Leonardo Drews Montibeller at ldmontibeller@gmail.com
 */
public class ClientesDAO {

    //Atributos
    private Connection conexao;

    //Construtor
    public ClientesDAO() {
        //1º passo: disponibilizar uma conexão com o BD
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrarCliente(Clientes obj) {
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into tb_clientes(cpf, email, nome, telefone)"
                    + "values (?,?,?,?)";

            //3º passo: preparar o comando SQL para o driver
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCpf());
            comando.setString(2, obj.getEmail());
            comando.setString(3, obj.getNome());
            comando.setString(4, obj.getTelefone());
            

            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //5º passo: pegar o id gerado pelo banco de dados atraves do cpf
            sql = "select id from tb_clientes where cpf=?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1,obj.getCpf());
            
            //com o comando preparado, executo o comando
            //esse comando é de leitura do banco de dados, logo ele retorna um resultset
            ResultSet rs = comando.executeQuery();
            
            //percorro o resultado até achar o campo "id"
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
            }
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Clientes> listarClientes() {
        try {
            //1º passo: criar uma lista para armazenar os Clientes
            List<Clientes> listaClientes = new ArrayList<>();
            
            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de endereços
            String sql = "select * from tb_clientes";
            
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
                Clientes obj = new Clientes();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo clientes
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaClientes.add(obj);       
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return listaClientes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    }
    
    public List<Clientes> listarClientes(String busca) {
        try {
            //1º passo: criar uma lista para armazenar os Clientes
            List<Clientes> listaClientes = new ArrayList<>();
            
            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de clientes
            String sql = "select * from tb_clientes WHERE concat_ws(id, cpf, email, nome, telefone) like ?";
            
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
                Clientes obj = new Clientes();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo clientes
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaClientes.add(obj);       
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return listaClientes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    }
    
    public void deletarCliente(Clientes obj){
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente?", "", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == 0){
            try {
                /* maneira antiga de remover os endereços, atualizado para "on delete cascade" do banco de dados
                String sql = "delete from tb_enderecos where id_cliente = ?";
                PreparedStatement comando = conexao.prepareStatement(sql);
                comando.setInt(1, obj.getId());
                  
                comando.execute();
                comando.close();
                */
                
                //2º passo: criar uma string de comando SQL
                String sql = "delete from tb_clientes where id = ?";

                //3º passo: preparar o comando SQL para o driver
                PreparedStatement comando = conexao.prepareStatement(sql);
                comando.setInt(1, obj.getId());

                //4º passo: executar o comando sql e fechar a conexão
                comando.execute();
                comando.close();

                //Se chegar aqui a exclusao foi efetuada com sucesso
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void atualizarCliente(Clientes obj){
            try {
            //2º passo: criar uma string de comando SQL
            String sql = "update tb_clientes set nome=?, email=?, cpf=?, telefone=? where id=?";

            //3º passo: preparar o comando SQL para o driver
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getNome());
            comando.setString(2, obj.getEmail());
            comando.setString(3, obj.getCpf());
            comando.setString(4, obj.getTelefone());
            comando.setInt(5, obj.getId());
            

            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //5º passo: pegar o id gerado pelo banco de dados atraves do cpf
            sql = "select id from tb_clientes where cpf=?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1,obj.getCpf());
            
            //com o comando preparado, executo o comando
            //esse comando é de leitura do banco de dados, logo ele retorna um resultset
            ResultSet rs = comando.executeQuery();
            
            //percorro o resultado até achar o campo "id"
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
            }
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cadastro de cliente atualizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import projetointegrador.model.Clientes;
import projetointegrador.model.Enderecos;

/**
 *
 * @author Leonardo Drews Montibeller at ldmontibeller@gmail.com
 */
public class TesteDAO {
    public static void main(String[] args) {
//         TESTE DO CADASTRAR
//        Enderecos enderecoTeste = new Enderecos();
//        
//        enderecoTeste.setCep("88888-88");
//        enderecoTeste.setRua("RUA GRANDE");
//        enderecoTeste.setNumero(888);
//        enderecoTeste.setComplemento("D");
//        enderecoTeste.setBairro("CENTRO");
//        enderecoTeste.setCidade("CHAPECO");
//        enderecoTeste.setUF("SC");
//        
//        EnderecosDAO dao = new EnderecosDAO();
//        dao.cadastrarEndereco(enderecoTeste);

//        TESTE DO LISTAR
          EnderecosDAO dao = new EnderecosDAO();
          dao.listarEnderecos();
          
          Clientes clienteTeste = new Clientes();
          clienteTeste.setCpf("123.456.789-50");
          clienteTeste.setEmail("cliente@email.com");
          clienteTeste.setId(0);
          clienteTeste.setNome("nome");
          clienteTeste.setTelefone("3");
          

    }
   
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.cci;

import br.ifes.cdp.Endereco;
import br.ifes.cdp.Usuario;
import br.ifes.cgd.Dados;
import br.ifes.cih.View;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 20121bsi0414
 */
public class ControleGeral {
    
    private View impressora = new View();
    private Usuario usuario;
    private Dados dados = new Dados();
    public void run(){
        
        
        try{
            usuario = dados.loadUsuario();
        }catch(Exception e){
            usuario = null;
        }
        
        impressora.imprimirMenu();
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        while(true){
            
            switch(opcao){
                case(0):{
                    if(usuario == null){
                        usuario = cadastraUsuario();
                    }
                    break;
                }
                case(1):{
                    if(usuario!=null){
                        usuario.adicionarAmigo(cadastraUsuario());
                    }
                    break;
                }
                case(2):{
                    impressora.imprimirAmigos(usuario.listarAmigos());
                    break;
                }
                
                case(3):{
                    int contador;
                    impressora.imprimirDeleteUsuario();
                    String nome = scanner.nextLine();
                    boolean achou = false;
                    for(contador = 0; contador < usuario.listarAmigos().size(); contador++){
                        if(usuario.getNome().equals(nome)){
                            usuario.listarAmigos().remove(contador);
                            achou=true;
                            break;
                        }
                    }
                    if(achou == false){
                        impressora.imprimirAmigoNaoEncontrado();
                    }
                    break;
                }
                case(4):{
                    int contador;
                    impressora.imprimirPesquisarUsuario();
                    String nome = scanner.nextLine();
                    boolean achou = false;
                    for(contador = 0; contador < usuario.listarAmigos().size(); contador++){
                        if(usuario.getNome().equals(nome)){
                            usuario.listarAmigos().get(contador).toString();
                            achou=true;
                            break;
                        }
                    }
                    if(achou == false){
                        impressora.imprimirAmigoNaoEncontrado();
                    }
                    break;
                }
                case(5):{
                    dados.saveUsuario(usuario);  
                    System.exit(0);
                }            
            }
            impressora.imprimirMenu();
            opcao = scanner.nextInt();
        }       
    }
    
    public Usuario cadastraUsuario(){
        Usuario newUser = new Usuario();
        Endereco newEnd = new Endereco();
        Scanner scanner = new Scanner(System.in);
        impressora.imprimirCadastroNome();
        newUser.setNome(scanner.nextLine());
        impressora.imprimirCadastroIdade();
        newUser.setIdade(scanner.nextLine());
        impressora.imprimirCadastroTelefone();
        newUser.setTelefone(scanner.nextLine());
        impressora.imprimirCadastroLogradouro();
        newEnd.setLogradouro(scanner.nextLine());
        //newUser.getEndereco().setNumero(scanner.nextLine());
        impressora.imprimirCadastroNumero();
        newEnd.setNumero(scanner.nextLine());
        //newUser.getEndereco().setLogradouro(scanner.nextLine());
        impressora.imprimirCadastroBairro();
        newEnd.setBairro(scanner.nextLine());
        //newUser.getEndereco().setBairro(scanner.nextLine());
        impressora.imprimirCadastroCidade();
        newEnd.setCidade(scanner.nextLine());
        //newUser.getEndereco().setCidade(scanner.nextLine()); 
        impressora.imprimirCadastroEstado();
        newEnd.setEstado(scanner.nextLine());
        //newUser.getEndereco().setCidade(scanner.nextLine()); 
        newUser.setEndereco(newEnd);
        return newUser;

    }
    
    
}

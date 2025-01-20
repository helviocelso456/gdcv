/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pungo.andongo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author helvi
 */
public class proprietario {
    private int identificador = 100;
    private String nome;
    private String contacto;
    
    //Metodos Getter
    public int getIdentificador()
    {
        return identificador;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public String getContacto()
    {
        return contacto;
    }
    
    //Metodos Setter
    private void setIdentificador(int id)
    {
        this.identificador = id;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public void setContacto(String contacto)
    {
        this.contacto = contacto;
    }
    
     public void atualizarID() {
        String caminho = "src/Arquivos/proprietario.txt";
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String l;
            String ultimo_id = null;
            
            // Ler o arquivo para encontrar o último ID
            while ((l = leitor.readLine()) != null) {
                String[] row = l.split("\\|");
                if (row.length > 1) {
                    ultimo_id = row[1];
                }
            }
            
            // Atualizar o ID do cliente
            if (ultimo_id != null) {
                try {
                    // Extrai apenas os dígitos do ID encontrado
                    String numeroId = ultimo_id.replaceAll("\\D", "");
                    int valor = Integer.parseInt(numeroId);
                    valor++;
                    setIdentificador(valor); // Incrementa e atualiza o ID
                    System.out.println(valor);
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter o ID do cliente");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo de clientes");
            
        }
    }
    
    //Registro de Proprietarios
    public void registarProprietario()
    {   //caminho onde ficara armazenado o arquivo txt
        String caminho = "src/Arquivos/proprietario.txt";
        //id concatenado com a string
        String propId = "d"+identificador+"ao";
        //Para verificar se o arquivo existe usamos a classe file
        File linha = new File(caminho);
        
        //Caso os campos estejam vazios
        if(nome.isEmpty() || contacto.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Preencha os campos vazios.");
        }
        
        else
        {   //Se n estiverem verificamos o diretorio
            if(!linha.exists())
            {  
                //Caso n exista criamos um novo diretorio com os dados a cadastrar
                //Usamos a classe bufferedwriter para escrever os dados no arquivo
                try(BufferedWriter br = new BufferedWriter(new FileWriter(caminho)))
                {   
                    //Aqui ele escreve
                    br.write(propId+"|"+nome+"|"+contacto);
                    //Quebra uma linha
                    br.newLine();
                    br.flush();
                    //Encerra a variavel para n haver comportamentos inesperados
                    br.close();
                    JOptionPane.showMessageDialog(null,"Proprietário cadastrado com sucesso"); 
                }
                
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Não foi possível registar o cliente"); 
                }
            }
            
            else
            {  //Abrimos o tratamento de exceptions
                try(BufferedWriter br = new BufferedWriter(new FileWriter(caminho)))
                {   //Atualizamos o id
                    atualizarID();
                    //Aqui ele escreve
                    br.write(propId+"|"+nome+"|"+contacto);
                    //Quebra uma linha
                    br.newLine();
                    br.flush();
                    //Encerra a variavel para n haver comportamentos inesperados
                    br.close();
                    JOptionPane.showMessageDialog(null,"Proprietário cadastrado com sucesso");
                }
                
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Não foi possível registar o cliente"); 
                }
            }  
               
        }
        
    }
    
    
}


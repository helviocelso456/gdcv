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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author helvi
 */
public class proprietario {
    private static int identificador = 001;
    private String nome;
    private String contacto;
    
    //Construtor
    public proprietario(String nome, String contacto)
    {
         setNome(nome);
         setContacto(contacto);
         registarProprietario();
    }
    
    //Copy Constructor
    public proprietario(String identificador)
    {
        consultaProprietario(identificador);
    }
    
    
    public proprietario()
    {
        
    }
    
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
    
    private void setNome(String nome)
    {
        this.nome = nome;
    }
    
    private void setContacto(String contacto)
    {
        this.contacto = contacto;
    }
    
     private void atualizarID() {
        String caminho = "src/Arquivos/proprietario.txt";
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String l;
            String ultimo_id = null;
            
            // Ler o arquivo para encontrar o último ID
            while ((l = leitor.readLine()) != null) {
                 //Criamos um array string que ira receber as linhas, o split diz que cada pos esta delimitada por |
                String[] row = l.split("\\|");
                //Verifica se a largura ou tam é maior que zero
                if (row.length > 0) {
                    //Pega o id que esta na pos 0
                    ultimo_id = row[0];
                }
            }
            
            // Atualizar o ID do proprietario
            if (ultimo_id != null) {
                try {
                    // Extrai apenas os dígitos do ID encontrado
                    String numeroId = ultimo_id.replaceAll("\\D", "");
                    int valor = Integer.parseInt(numeroId);
                    valor++;
                    setIdentificador(valor); // Incrementa e atualiza o ID
                    System.out.println(valor);
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter o ID do proprietario");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo do proprietario");
            
        }
    }
    
    //Registro de Proprietarios
    public void registarProprietario()
    {   //caminho onde ficara armazenado o arquivo txt
        String caminho = "src/Arquivos/proprietario.txt";
        //id concatenado com a string
        String propId = "d"+getIdentificador()+"ao";
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
                   //Validando o id
                  if(identificador > 999)
                  {
                   JOptionPane.showMessageDialog(null,"O sistema excedeu o limite de registos de animais"); 
                   return;
                  }
                    //Aqui ele escreve
                    br.write(propId+"|"+nome+"|"+contacto);
                    //Quebra uma linha
                    br.newLine();
                    //Encera a variavel
                    br.close();
                    JOptionPane.showMessageDialog(null,"Proprietário cadastrado com sucesso\n"+toString());
                    
                }
                
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Não foi possível registar o cliente"); 
                }
            }
            
            else
            {  //Abrimos o tratamento de exceptions
                try(BufferedWriter br = new BufferedWriter(new FileWriter(caminho,true)))
                {   
                    //Atualizamos o id
                    atualizarID();
                    //Validando o id
                  if(identificador > 999)
                  {
                   JOptionPane.showMessageDialog(null,"O sistema excedeu o limite de registos de animais"); 
                   return;
                  }
                    //Novo valor do identificador
                    propId = "d"+getIdentificador()+"ao";
                    //Aqui ele escreve
                    br.write(propId+"|"+nome+"|"+contacto); 
                    //Quebra uma linha
                    br.newLine();
                    //Encerra a variavel
                    br.close();
                    JOptionPane.showMessageDialog(null,"Proprietário cadastrado com sucesso\n"+toString());
                    
                }
                
                catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Não foi possível registar o cliente"); 
                }
            }  
               
        }
        
    }
    
    //Consulta o proprietario
    public static String consultaProprietario(String identificador)
    {
        //Caminho
        String caminho = "src/Arquivos/proprietario.txt";
        //Tipo file para posterior verificacao
        File arquivo = new File(caminho);
        //Verifica se existe
        if(!arquivo.exists())
        {       
               JOptionPane.showMessageDialog(null,"Nenhum proprietário registado");
               return null;
        }
           
        else
        {   //Acede a memória para leitura
            try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
            {
             String l;
             String id = null;
             //Percorre o arquivo linha por linha
             while((l =leitor.readLine()) != null)
             {   //Cria um array de string e diz que ela é delimitada por | e armazena cada parte da linha no array
                 String row[] = l.split("\\|");
                 //Verifica se o id que esta na pos 0 é igual ao do parametro
                 if(row[0].equals(identificador))
                 {  // Retorna o id caso encontre
                     return row[0];
                 }
                 
             }
             leitor.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Não foi possível consultar o proprietário");
                return null;
            }
        }
           
       return null;     
        
    }
    
    public ArrayList<String[]> ListarProprietario()
    {   
        //Caminho do arquivo
        String caminho = "src/Arquivos/proprietario.txt";
        //Tipo File
        File arquivo = new File(caminho);
        
        //Lista que armazena os dados
        ArrayList<String[]> lista = new ArrayList<>();
        
        if(!arquivo.exists())
        {
           JOptionPane.showMessageDialog(null,"Não existem proprietários cadastrados no sistema.");
           return null;
        }
        
        else
        {
            //Vai ser usada para comparação no laço
            String l;
            try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
            {
                while((l = leitor.readLine()) != null)
                {
                    String row[] = l.split("\\|");
                    lista.add(row);
                }
                
                leitor.close();
            }
            
            catch(Exception e )
            {
                JOptionPane.showMessageDialog(null,"Não foi possível listar os proprietários.");
                return null;
            }
            
        }
          return lista;
    }
    
    //Metodo toString para exibir os dados
    public String toString()
    {
        return "d"+identificador+"ao"+"\n"+nome+"\n"+contacto;
    }
    
    public static void main(String [] args )
    {
        String id = consultaProprietario("d1ao");
        System.out.println(id);
    }
    
    
}


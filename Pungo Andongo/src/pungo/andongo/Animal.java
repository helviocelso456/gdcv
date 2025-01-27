/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pungo.andongo;

/**
 *
 * @author helvi
 */
//Biblioteca Local Date para manipular datas
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
//Biblioteca period para calcular a diferença entre datas
import java.time.Period;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Animal {
    private static int identificador = 001;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private proprietario proprietario;
    
    //Construtores
    public Animal()
    {
        
    }
    
    public Animal(String codProp,String nome, String especie, String raca, String dataNasc)
    {  
        //Atribuindo valores
        setNome(nome);
        setEspecie(especie);
        setRaca(raca);
        setIdade(dataNasc);
        setProprietario();
        
        //Consulta pra ver se o proprietario existe
        if(proprietario.consultaProprietario(codProp) != null)
        {  
           if(nome.isEmpty() || dataNasc.isEmpty())
           {
            JOptionPane.showMessageDialog(null,"Preencha os campos vazios");
           }
           else
           {
            registarAnimal(codProp);
           }
        }
        
        else
        {
            JOptionPane.showMessageDialog(null,"Proprietário Inexistente");
        }
    }
    
    public Animal(String codAnimal)
    {
        consultarAnimal(codAnimal);
    }
    
    //Registar animal
    private void registarAnimal(String codProp)
    {
        //Criando o caminho
        String caminho = "src/arquivos/animal.txt";
        // Identificador Concatenado
        String animalId = "cli"+getIdentificador()+"ao";
        //Tipo File
        File arquivo = new File(caminho);
        
        if(idade > 5)
        {
           JOptionPane.showMessageDialog(null,"Idade superior a 5, "+idade+" anos");  
        }
        
        else
        {
           //Caso o arquivo n exista
           if(!arquivo.exists())
           {
            //Cria um novo arquivo ao cadastrar
            //Tratamento de excessões
             try(BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho)))
             {
                //registando
                escritor.write(animalId+"|"+nome+"|"+especie+"|"+raca+"|"+idade+"|"+codProp);
                //Quebra a linha
                escritor.newLine();
                //Encerra a variavel
                escritor.close();
                //Mensagem
                JOptionPane.showMessageDialog(null, "Animal Registado com Sucesso\n"+toString());
                
             }
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(null,"Não foi possivel cadastrar o animal");
                System.out.println(e);
             }
          }
           
          else
          {
             //Tratamento de excessões
             //Levou o parametro true para n sobescrever os dados antigos
             try(BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho,true)))
             {
                 //Incrementa o id
                atualizarIdentificador();
                //Com o novo valor do identificador
                animalId = "cli"+getIdentificador()+"ao";
                //registando
                escritor.write(animalId+"|"+nome+"|"+especie+"|"+raca+"|"+idade+"|"+codProp);
                //Quebra a linha
                escritor.newLine();
                //Encerra a variavel
                escritor.close();
                //Mensagem
                JOptionPane.showMessageDialog(null, "Animal Registado com Sucesso\n"+toString());
                
             }
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(null,"Não foi possivel cadastrar o animal");
                System.out.println(e);
             }  
          }
        }
    }
    
    public static String consultarAnimal(String identificador)
    {
        //Caminho
        String caminho = "src/Arquivos/animal.txt";
        //Tipo file para posterior verificacao
        File arquivo = new File(caminho);
        //Verifica se existe
        if(!arquivo.exists())
        {       
               JOptionPane.showMessageDialog(null,"Nenhum proprietário registado");
               return null;
        }
        else
        {
             //Acede a memória para leitura
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
             
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Não foi possível consultar o animal");
                return null;
            }
        }
        return null;
    }
    
    //Listagem de animal
    public ArrayList<String[]> ListarAnimal()
    {
        //Caminho
        String caminho = "src/Arquivos/animal.txt";
        //Tipo File
        File arquivo = new File(caminho);
        //Lista
        ArrayList<String[]> lista = new ArrayList<>();
        
        if(!arquivo.exists())
        {
            JOptionPane.showMessageDialog(null,"Não existem animais registados no sistema.");
            return null;
        }
        
        else
        {
            try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
            {
               //Vai receber as linhas
               String l;
               
               while((l = leitor.readLine()) != null)
               {
                   //Pega as linhas e delimita elas
                   String row[] = l.split("\\|");
                   //Adiciona a linha a lista
                   lista.add(row);
               }
               
               leitor.close();
               
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Não foi possível listar os animais."+e);
                return null;
            }
            
        }
        
        return lista;
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
    
    public String getEspecie()
    {
        return especie;
    }
    
    public String getRaca()
    {
        return raca;
    }
    
    public int getIdade()
    {
        return idade;
    }
    
    public proprietario getProprietario()
    {
        return proprietario;
    }
    
    //Metodos Setter
    private void setIdentificador(int valor)
    {
        this.identificador = valor;
    }
    
    private void setNome(String nome)
    {
        this.nome = nome;
    }
    
    private void setEspecie(String especie)
    {
        this.especie = especie;
    }
    
    private void setRaca(String raca)
    {
        this.raca = raca;
    }
    
    private void setProprietario()
    {
        this.proprietario = new proprietario();
    }
    
    private void setIdade(String dataNasc)
    {
       //Tratamento de Exceções
       try
       {   //Convertemos a data nasc em local date
           LocalDate dataNascimento = LocalDate.parse(dataNasc);
           //Criamos a data atual
           LocalDate dataAtual = LocalDate.now();
           //Calculando a idade
           this.idade = Period.between(dataNascimento, dataAtual).getYears();
       }
       
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null,"Não foi possível calcular a idade");
       }
    }
    
    //Atualizando o identificador
    private void atualizarIdentificador()
    {
        //Caminho
        String caminho = "src/arquivos/animal.txt";
        //Tratamento de Excessões
        try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
        {   //Ira receber o ultimo id
            String ultimo_id = null;
            //Ira receber as linhas
            String l;
            
            //laço
            while((l = leitor.readLine()) != null)
            {   
                //Criamos um array string que ira receber as linhas, o split diz que cada pos esta delimitada por |
                String[] row = l.split("\\|");
                //Verifica se a largura ou tam é maior que zero
                if (row.length > 0) {
                    //Pega o id que esta na pos 0
                    ultimo_id = row[0];
                }
            }
            
            if(ultimo_id != null)
            {
                //Tratamento de excessões para o formato do numero
                try
                {
                    //Descarta todos os caracteres e mantem os digitos
                    String numeroId = ultimo_id.replaceAll("\\D","");
                    //Criamos um inteiro que ira receber os digitos do id
                    int valor = Integer.parseInt(numeroId);
                    //Incrementamos o id
                    valor++;
                    //Agora atribuimos o novo valor
                    setIdentificador(valor);
                    //Para verificar o funcionamento da função
                    System.out.println(valor);
                }
                catch(NumberFormatException e)
                {
                   JOptionPane.showMessageDialog(null,"Não foi possivel atualizar o id"); 
                }
            }
            
            leitor.close();
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo do animal");
        }
    }
    
    //Metodo toString
    public String toString()
    {
        return "cli"+identificador+"ao"+"\n"+"Nome: "+nome+"\n"+"Especie: "+especie+"\n"+"Raça: "+raca+"\n"+"Idade: "+idade+" ano(s)de idade";
    }
    
    public static void main(String [] args)
    {
        System.out.println(consultarAnimal("cli1ao"));
    }
}

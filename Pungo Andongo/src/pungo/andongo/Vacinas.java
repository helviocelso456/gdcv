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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author helvi
 */
public class Vacinas extends Visitas {
    private String nomeVacina;
    
    //Construtores
    public Vacinas()
    {
        
    }
    
    public Vacinas(String nVacinas, String codAnimal, int custo, String dataVacinas)
    {   
        //Atribui o nome do valor
        super(dataVacinas, custo);
        setNomeVacina(nVacinas);
        Animal animal = new Animal();
        if(animal.consultarAnimal(codAnimal) != null)
        {
           registarVacinas(codAnimal); 
        }
        
        else
        {
            JOptionPane.showMessageDialog(null,"Animal não encontrado");
        }
    }
    
    //Registando as vacinas
    private void registarVacinas(String codAnimal)
    {
       try{
       LocalDate dataV = LocalDate.parse(getDataVisita());
       if (dataV == null) 
       {
           JOptionPane.showMessageDialog(null, "O campo da data da visita encontra-se nulo.");
           return; // Evita que o código continue caso o campo esteja vazio
       }
       
       if(nomeVacina.isEmpty()) 
       {
           JOptionPane.showMessageDialog(null,"Preencha os campos vazios.");
           return;
       }
       
       if(validarData(dataV) == false)
       {
           return;
       }
       
       else
       {
           //Caminho
          String caminho = "src/Arquivos/visita.txt";
          //Tipo File
          File arquivo = new File(caminho);
          //Atributos
          int custo = getCustoPrestado();
          
          //Caso o arquivo não exista
          if(!arquivo.exists())
          {
              try(BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho)))  
             {
                 //Validando o id
                if( getIdentificador() > 999)
                {
                  JOptionPane.showMessageDialog(null,"O sistema excedeu o limite de registos de animais"); 
                  return;
                }
                 //Registando
                 escritor.write(getIdentificador()+"|"+"Vacina"+"|"+nomeVacina+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Vacina Registado com sucesso\n"+toString());
             }
              
             
             catch(Exception e)
             {
                JOptionPane.showMessageDialog(null,"Não foi possível registar a visita\n"+e); 
             }
          }
          
          else
          {
              try(BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho,true)))  
             {
                 //Atualizando o identificador
                 atualizarId();
                 //Validando o id
                if( getIdentificador() > 999)
                {
                  JOptionPane.showMessageDialog(null,"O sistema excedeu o limite de registos de animais"); 
                  return;
                }
                 //Registando
                 escritor.write(getIdentificador()+"|"+"Vacina"+"|"+nomeVacina+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Vacina Registado com sucesso\n"+toString());
             }
              
             
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null,"Não foi possível registar a visita\n"+e); 
             }
          }
       }
       }
       catch(DateTimeParseException d)
       {
                 JOptionPane.showMessageDialog(null,"Insira o formato correto da data(y-mm-dd)");
                 System.out.println(d);
       }
    }
    //Metodo Get
    public String getVacina()
    {
        return nomeVacina;
    }
    
    //Metodo Setter
    private void setNomeVacina(String nVacina)
    {
        this.nomeVacina = nVacina;
    }
    
    
    public String toString()
    {
       return getIdentificador()+"\n"+"Tipo de Visita: Vacina"+"\n"+"Nome da Vacina: "+nomeVacina+"\n"+"Custo: "+getCustoPrestado()+" AOA"+"\n"+"Data da Visita: "+getDataVisita(); 
    }
}

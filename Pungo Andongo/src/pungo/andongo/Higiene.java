/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pungo.andongo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author helvi
 */
public class Higiene extends Visitas {
    
    public Higiene()
    {
        
    }
    
    public Higiene(String dataVisita,String codAnimal ,int custo)
    {
        //Chamando a superclass
        super(dataVisita, custo);
        Animal animal = new Animal();
        if(animal.consultarAnimal(codAnimal) != null)
        {
           registarHigiene(codAnimal); 
        }
        
        else
        {
            JOptionPane.showMessageDialog(null,"Animal não encontrado");
        }
    }
    
    //Registando as vacinas
    private void registarHigiene(String codAnimal)
    {
        try{
       LocalDate dataV = LocalDate.parse(getDataVisita());
       if (dataV == null) 
       {
           JOptionPane.showMessageDialog(null, "O campo da data da visita encontra-se nulo.");
           return; // Evita que o código continue caso o campo esteja vazio
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
                 escritor.write(getIdentificador()+"|"+"Higiene"+"|"+"Banho"+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Visita para Higiene Registada com sucesso\n"+toString());
             }
             
             catch(DateTimeParseException d)
             {
                 JOptionPane.showMessageDialog(null,"Insira o formato correto da data(y-mm-dd)"+d);
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
                 escritor.write(getIdentificador()+"|"+"Higiene"+"|"+"Banho"+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Visita para Higiene Registada com sucesso\n"+toString());
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
    
    public String toString()
    {
       return getIdentificador()+"\n"+"Tipo de Visita: Higiene"+"\n"+"Motivo: Banho"+"\n"+"Custo: "+getCustoPrestado()+" AOA"+"\n"+"Data da Visita: "+getDataVisita(); 
    }
    
}

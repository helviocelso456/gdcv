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
public class Consulta extends Visitas {
    private String motivo;
   
    //Construtor
    public Consulta()
    {
        
    }
    
    public Consulta(String motivo, String codAnimal, int custo, String dataVisita)
            
    {   
        super(custo,dataVisita);
        setMotivo(motivo);
        Animal animal = new Animal();
        if(animal.consultarAnimal(codAnimal) != null)
        {
           registarConsultas(codAnimal); 
        }
        
        else
        {
            JOptionPane.showMessageDialog(null,"Animal não encontrado");
        }
    }
    
    //Criando Consultas
    private void registarConsultas(String codAnimal)
    {
        try{
       LocalDate dataV = LocalDate.parse(getDataVisita());
       if (dataV == null) 
       {
           JOptionPane.showMessageDialog(null, "O campo da data da visita encontra-se nulo.");
           return; // Evita que o código continue caso o campo esteja vazio
       }
       
       if(motivo.isEmpty()) 
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
                 escritor.write(getIdentificador()+"|"+"Consulta"+"|"+motivo+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Escritor Registado com sucesso\n"+toString());
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
                 escritor.write(getIdentificador()+"|"+"Consulta"+"|"+motivo+"|"+custo+"|"+codAnimal+"|"+dataV);
                 //Quebrando a linha
                 escritor.newLine();
                 //Encerrando a variavel
                 escritor.close();
                 //Mensagem
                 JOptionPane.showMessageDialog(null,"Escritor Registado com sucesso\n"+toString());
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
    
    //Metodo Getter
    public String getMotivo()
    {
        return motivo;
    }
    
    //Metodo Setter
    private void setMotivo(String motivo)
    {
       this.motivo = motivo; 
    }
    
    public String toString()
    {
       return getIdentificador()+"\n"+"Tipo de Visita: Consulta"+"\n"+"Motivo: "+motivo+"\n"+"Custo: "+getCustoPrestado()+" AOA"+"\n"+"Data da Visita: "+getDataVisita(); 
    }
    
    public static void main(String [] args)
    {
       
    }
}

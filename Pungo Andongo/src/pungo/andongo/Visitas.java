/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pungo.andongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author helvi
 */
public class Visitas {
    private static int identificador = 001;
    private String tipo_de_visita;
    private int custo_prestado;
    private String data_da_visita;
    
    //Construtores
    public Visitas()
    {
        
    }
    
    public Visitas(String tipoVisita, int custo, String motivo, String codAnimal ,String dataVisita)
    {
        //Caso seja consulta
        //Atribuindo valores
        setTipoVisita(tipoVisita);
        setCustoPrestado(custo);
        setDataVisita(dataVisita);
        new Consulta(motivo, codAnimal,custo,dataVisita);
        
    }
    
    public Visitas(String tipoVisita, String dataVisita, String nVacinas, String codAnimal ,int custo)
    {
       //Caso seja vacina 
       setTipoVisita(tipoVisita);
       setCustoPrestado(custo);
       setDataVisita(dataVisita);
       new Vacinas(nVacinas, codAnimal, custo, dataVisita);
    }
    
    //Construtor com as consultas
    public Visitas(int custo, String dVisita)
    {
        setCustoPrestado(custo);
        setDataVisita(dVisita);
    }
    
    //Construtor com as vacinas
    public Visitas(String dVisitas, int custo)
    {
       setCustoPrestado(custo);
       setDataVisita(dVisitas);
    }
    
    //Metodos Getter
    public String getTipoDeVisita()
    {
        return tipo_de_visita;
    }
    
    public int getCustoPrestado()
    {
        return custo_prestado;
    }
    
    public String getDataVisita()
    {
        return data_da_visita;
    }
    
    public int getIdentificador()
    {
        return identificador;
    }
    
    //Atualizando o id
    public void atualizarId()
    {
        //Caminho
       String caminho = "src/Arquivos/visita.txt";
       try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
       {
           //Vai receber as linhas
           String l;
           //Vai receber o ultimo id
           String ultimo_id = null;
           while((l = leitor.readLine()) != null)
           {
              //Array de string, informamos que a linha e delimitada por | e cada elemento dentro do | entra no indice
              String row[] = l.split("\\|");
              //Se o tamanho for maior que 0, o ultimo id recebe o valor do indice 0
              if(row.length > 0)
              {
                  ultimo_id = row[0];
              }
           }
           
           //Caso seja diferente de null
           if(ultimo_id != null)
           {
               try
               {
               //O valor recebe o digito dentro do ultimo id
               String uId = ultimo_id.replaceAll("\\D","");
               int valor = Integer.parseInt(uId);
               //Incrementa-se
               valor++;
               //Atribui - se o novo valor
               setIdentificador(valor);
               }
               catch(NumberFormatException e)
               {
                   JOptionPane.showMessageDialog(null,"Não foi possível converter o identificador\n"+e);
               }
           }
           //Encerramos a variavel
           leitor.close();
       }
       
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null,"Não foi possível atualizar o identificador");
       }
    }
    
    //Listagem de visitas
    public ArrayList<String[]> ListarVisita()
    {
        //Lista
        ArrayList<String[]> lista = new ArrayList<>();
        //Caminho
        String caminho = "src/Arquivos/visita.txt";
        //Tipo File
        File arquivo = new File(caminho);
        
        if(!arquivo.exists())
        {
            JOptionPane.showMessageDialog(null,"Não existem visitas registadas");
            return null;
        }
        
        else
        {  
            //Tratamento de Excessões
            try(BufferedReader leitor = new BufferedReader(new FileReader(caminho)))
            {
                //Vai receber as linhas
                String l;
                //While
                while((l = leitor.readLine()) != null)
                {
                    String row[] = l.split("\\|");
                    lista.add(row);
                }
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Não foi possível listar as visitas");
                return null;
            }
        }
        
        
        return lista;
    }
    
    
    //Metodos Setter
    private void setTipoVisita(String tipo_de_visita)
    {
      this.tipo_de_visita = tipo_de_visita;
    }
    
    private void setCustoPrestado(int custo_prestado)
    {
        this.custo_prestado = custo_prestado;
    }
    
    private void setDataVisita(String dataVisita)
    {
        this.data_da_visita = dataVisita;
    }
    
    private void setIdentificador(int valor)
    {
        this.identificador = valor;
    }
    
   
    
}

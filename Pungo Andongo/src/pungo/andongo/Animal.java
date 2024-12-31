/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pungo.andongo;

/**
 *
 * @author helvi
 */
public class Animal {
    private int identificador;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private proprietario proprietario;
    
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Thealex7
 */
public class generador_numerico {
    private int dato;
    private int dato1;
    private int cont=1;
    private int cont1=0;
    private String num="";
    private String num1="";
    private String numB="";
    private String numR="";
    
public void Generar_cliente(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        num = "" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        num = "00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        num = "000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        num = "0000" + cant;
    }
   
        
}

public String serie_cli(){
    return this.num;
}
 //////////////////////////////////////////////////////////////////////////////////////////////
public void Generar_Bar(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        numB = "B" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        numB = "B00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        numB = "B000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        numB = "B0000" + cant;
    }
   
        
}

public String serie_bar(){
    return this.numB;
}
//////////////////////////////////////////////////////////////////////////////////////////
public void Generar_rest(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        numR = "R" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        numR = "R00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        numR = "R000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        numR = "R0000" + cant;
    }
   
        
}

public String serie_rest(){
    return this.numR;
}

 public void Generar_persona(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        num = "" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        num = "00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        num = "000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        num = "0000" + cant;
    }

        
}

public String serie_per(){
    return this.num;
}   
    
     public void Generar_provedor(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        num = "" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        num = "00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        num = "000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        num = "0000" + cant;
    }
  
        
}

public String serie_prove(){
    return this.num;
}   
  public void Generar_inv(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        num = "" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        num = "00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        num = "000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        num = "0000" + cant;
    }
  
        
}

public String serie_inv(){
    return this.num;
}   
public void Generar_usu(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=cont+this.dato;
        num = "" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=cont+this.dato;
        num = "00" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=cont+this.dato;
        num = "000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=cont+this.dato;
        num = "0000" + cant;
    }
  
        
}

public String serie_usu(){
    return this.num;
}  

public void Generar_comp(int dato){
    this.dato1 = dato;
    
    
    if ((this.dato1>=10000) && (this.dato1<99999)){
        num1 = "B0100" + this.dato1;
    }
    
    if ((this.dato1>=100) && (this.dato1<1000)){
        num1 = "B0100" + this.dato1;
    }
    
    if ((this.dato1>3) && (this.dato1<100)){
        num1 = "B0100" + this.dato1;
    }
    if ((this.dato1>1) && (this.dato1<=3)){
        num1 = "B01000000" + this.dato1;
    }
  
        
}
public String Serie_comp(){
    return this.num1;
}

public void Generar_comp_gb(int dato){
    this.dato = dato;
    
    
    if ((this.dato>=10000) && (this.dato<99999)){
        int cant=this.dato;
        num = "B15000" + cant;
    }
    
    if ((this.dato>=100) && (this.dato<1000)){
        int cant=this.dato;
        num = "B150000" + cant;
    }
    
    if ((this.dato>=9) && (this.dato<100)){
        int cant=this.dato;
        num = "B1500000" + cant;
    }
    if ((this.dato>=1) && (this.dato<9)){
        int cant=this.dato;
        num = "B15000000" + cant;
    }
  
        
}

public String Serie_comp_gb(){
    return this.num;
}
    
}

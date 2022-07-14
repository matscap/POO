
/**
 * Projeto POO 17/18 
 * LCC
 * 
 * Matias Capitao A82726
 * Jose Oliveira  A79346
 * Rafael Antunes A77457
 */
import java.io.*;
import java.time.LocalDateTime;

public class Despesa implements Serializable
{
    String numFiscalEmit; //Numero fiscal do emitente
    String designEmit;    //Designaçao do emitente
    LocalDateTime data;   //Data da despesa
    String numFiscal;     //Numero fiscal do cliente
    String desc;          //Descriçao da despesa
    String natureza;      //Natureza da despesa (isto e, a actividade a que diz respeito)
    double vDesp;            //Valor da despesa
    
    public Despesa () {
        this.numFiscalEmit = " ";
        this.designEmit = " ";
        this.data = LocalDateTime.now();
        this.numFiscal = " ";
        this.desc = " ";
        this.natureza = " ";
        this.vDesp = 0.0;
        
    }
    
    public Despesa (String numE, String desE, LocalDateTime data, String nF, String desc, String nat, double vD) {
        this.numFiscalEmit = numFiscalEmit;
        this.designEmit = designEmit;
        this.data = data;
        this.numFiscal = numFiscal;
        this.desc = desc;
        this.natureza = natureza;
        this.vDesp = vDesp;
       
    }
    
    public Despesa (Despesa d) {
        this.numFiscalEmit = d.getNFEmit();
        this.designEmit =d.getDEmit();
        this.data= d.getData();
        this.numFiscal = d.getNFiscal();
        this.desc = d.getDescri();
        this.natureza = d.getNatur();
        this.vDesp = d.getVDesp();
       
    }
    
    public String getNFEmit () {
        return numFiscalEmit;
    }
    public String getDEmit () {
        return designEmit;
    }
    public LocalDateTime getData () {
        return this.data;
    }
    public String getNFiscal () {
        return this.numFiscal;
    }
    public String getDescri() {
        return this.desc;
    }
    public String getNatur() {
        return this.natureza;
    }
    public double getVDesp() {
        return this.vDesp;
    }
    
        
    public void setNFEmit (String numFEmit) {
        this.numFiscalEmit=numFEmit;
    }
    public void setDEmit (String dEmit) {
        this.designEmit=dEmit;
    }
    public void setData (LocalDateTime d) {
        this.data=d;
    }
    public void setNFiscal (String nF) {
        this.numFiscal=nF;
    }
    public void setDescri(String des) {
        this.desc=des;
    }
    public void setNatur(String nat) {
        this.natureza=nat;
    }
    public void setVDesp( double despesa) {
        this.vDesp=despesa;
    }
    
    
    public String toString(){
        return this.numFiscalEmit +" | " 
                + this.designEmit +" | " 
                + this.data.getHour()+":"+this.data.getMinute()+"  " 
                + this.data.getDayOfMonth()+"/"+this.data.getMonthValue()+"/"+this.data.getYear()+" | " 
                + this.numFiscal +" | " 
                + this.desc +" | " 
                + this.natureza +" | " 
                + this.vDesp +" | " + "\n"; 
    }
    public Despesa clone () {
        return new Despesa (this);
    }
    
    public boolean equals (Object o)
   { if (this == o) return true;
     if ((null == o) ||this.getClass() != o.getClass()) return false;
      
     Despesa d = (Despesa) o;
     return this.numFiscalEmit == d.getNFEmit() && 
            this.designEmit == d.getDEmit() && 
            this.data == d.getData() &&
            this.numFiscal == d.getNFiscal () &&
            this.desc == d.getDescri () &&
            this.natureza == d.getNatur () &&
            this.vDesp == d.getVDesp ();
    }   
	
}

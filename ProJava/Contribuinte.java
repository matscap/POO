/**
 * Projeto POO 17/18 
 * LCC
 * 
 * Matias Capitao A82726
 * Jose Oliveira  A79346
 * Rafael Antunes A77457
 */
import java.io.*;
public class Contribuinte implements Serializable{
    String numFiscal;
    String mail;
    String nome;
    String morada;
    String password;
    
    public Contribuinte(){
        this.numFiscal="";
        this.mail="";
        this.nome="";
        this.morada="";
        
        }
    public Contribuinte(String n,String m,String nom,String md, String pass){
        this.numFiscal =n;
        this.mail=m;
        this.nome=nom;
        this.morada=md;
        this.password=pass;
        
        }
    
    public Contribuinte (Contribuinte c) {
       this.numFiscal = c.getNumFiscal();
       this.mail = c.getMail();
       this.nome = c.getNome();
       this.morada = c.getMorada();
       this.password = c.getPassword();
    }
       
    public String getNumFiscal(){
        return this.numFiscal;
        }
    public String getMail(){
        return this.mail;
        }
    public String getNome(){
        return this.nome;
        }
    public String getMorada(){
        return this.morada;
        }
    public String getPassword(){
        return this.password;
        }
    public void setNumFiscal(String n){
        this.numFiscal=n;
        }
    public void setMail(String m){
        this.mail = m;
        }
    public void setNome(String n){
        this.nome = n;
        }
    public void setMorada(String m){
        this.morada=m;
        }
    public void setPassword(String pass){
        this.password = pass;
        }
    
    public String toString() {
        return "Numero Fiscal: " +this.numFiscal +
               "\nNome: " +this.nome +
               "\nMorada: " +this.morada +
               "\nPassword: "+ this.password;
    }
    
    public Contribuinte clone () {
        return new Contribuinte (this);
    }
     public boolean equals (Object o) { 
       if (this == o) return true;
       if ((null == o) ||this.getClass() != o.getClass()) return false;
      
       Contribuinte c = (Contribuinte) o;
       return this.numFiscal == c.getNumFiscal() && 
              this.mail == c.getMail() && 
              this.nome == c.getNome() &&
              this.morada == c.getMorada () &&
              this.password == c.getPassword ();
    }
      
}

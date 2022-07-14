
/**
 * Projeto POO 17/18 
 * LCC
 * 
 * Matias Capitao A82726
 * Jose Oliveira  A79346
 * Rafael Antunes A77457
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
public class ContribuinteIndividual extends Contribuinte implements Serializable{
	
    int nDep;           //número de dependentes do agregado familiar
    String nFiscais[];  //números fiscais do agregado familiar
          //coeciente fiscal para efeitos de dedução (um factor multiplicativo que é associado a cada despesa elegível)
    String cod[];       //códigos das actividades económicas para as quais um determinado contribuinte tem possibilidade de deduzir despesas
    
    ArrayList<Despesa> desp = new ArrayList<>();
    ArrayList<Despesa> despPorCla = new ArrayList<>();
    
    public ContribuinteIndividual(){
		super();
        this.nDep=0;
        
    }
    public ContribuinteIndividual(String n,String m, String nom, String mor, String pass,int x,String[] nF, String [] cd){
        super(n,m,nom,mor,pass);
        this.nDep=x;
        this.nFiscais=nF;
       
        this.cod=cd;
    }
    //Rafa- Isto e util para que possa fazer um clone
    public ContribuinteIndividual (ContribuinteIndividual c) {
       
        this.nDep = c.getNdep();
        this.nFiscais = c.getNfiscais();
        
        this.cod = c.getCod();
    }
   
    public int getNdep(){
        return this.nDep;
        }
    public String[] getNfiscais(){
        return this.nFiscais;
        }
    public String[] getCod(){
        return this.cod;
        }
    public ArrayList getFaturas(){
	return this.desp;
        }
    public ArrayList getFatPorCla(){
	return this.despPorCla;
        }
        
   
    public void setNdep(int n){
        this.nDep=n;
        }
    public void setNfiscais(String []n){
        this.nFiscais=n;
        }
    public void setCod(String[] k){
        this.cod=k;
        }
     public void setFaturas(ArrayList x){
		 this.desp = x;
		 
		 }
	public void setFatPorCla(ArrayList x){
		 this.despPorCla = x;
		 
		 }	 
	public double vTotal(){
		double sum=0;
		int i;
		ArrayList x = this.desp;
		Despesa d = new Despesa();
		for(i=0;i<this.desp.size();i++){
			d=(Despesa)x.get(i);
			sum=sum+d.getVDesp();
			}
		
		return sum;
		}
    public double vDeduzido(){
		Map<String,Double> cods = codigos();
		
		double sum = 0;
		int i;
		ArrayList x = this.desp;
		Despesa d = new Despesa();
		String cod;
		for(i=0;i<this.desp.size();i++){
			d=(Despesa)x.get(i);
			cod = (String)d.getNatur();
			if(cods.containsKey(cod))
				sum=sum+d.getVDesp()*cods.get(cod);
			}
		
		return sum;
		}
	
	public static Map codigos(){
		Map<String,Double> cod = new HashMap<String,Double>();
		
		cod.put("0000",0.15); // Saúde
		cod.put("0001",0.15); // Prémios de seguros de saúde
		cod.put("0010",0.15); // Juros de empréstimos para habitação própria e permanente
		cod.put("0011",0.15); // Rendas de imóveis para habitação permanente
		cod.put("0100",0.30); // Encargos com a reabilitação de imóveis
		cod.put("0101",0.30); // Educação
		cod.put("0110",0.35); // Despesas Gerais
		cod.put("0111",0.15); // IVA de faturas
		cod.put("1000",0.25); // Lares
		cod.put("1001",0.20); // Pensões de alimentos
		cod.put("1010",0.20); // PPR e fundos de pensões
		cod.put("1011",0.20); // Regime público de capitalização
		cod.put("1100",0.25); // Donativos
		
		return cod;
		}
    //Rafa 
    //Adicionei o toString
    public String toString(){
        return "Contribuinte: " + this.numFiscal + 
               "\n Nº de dependentes do agregado: " + this.nDep + 
               "\n Nº Fiscais do agregado: " + this.nFiscais +
               "\n Codigos das atividades: " + this.cod;
       }
    //Criar um clone do contribuinte individual-    
    public ContribuinteIndividual clone() {
    return new ContribuinteIndividual (this);
    }

    public boolean equals (Object o) { 
       if (this == o) return true;
       if ((null == o) ||this.getClass() != o.getClass()) return false;
      
       ContribuinteIndividual c = (ContribuinteIndividual) o;
       return this.nDep == c.getNdep() && 
              this.nFiscais == c.getNfiscais() &&
              this.cod == c.getCod ();
    }
    
    }

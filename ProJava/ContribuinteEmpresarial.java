
/**
 * Projeto POO 17/18 
 * LCC
 * 
 * Matias Capitao A82726
 * Jose Oliveira  A79346
 * Rafael Antunes A77457
 */
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
public class ContribuinteEmpresarial extends Contribuinte implements Serializable{
    
    String []d;
    double factor;
    ArrayList<Despesa> desp = new ArrayList<>();
    
    public ContribuinteEmpresarial(){
		super();
        this.factor=0.0;
        }
    public ContribuinteEmpresarial(String n,String m, String nom, String mor, String pass, String []de, double f){
        super(n,m,nom,mor,pass);
        this.d=de;
        this.factor=f;
        }
    
    public ContribuinteEmpresarial (ContribuinteEmpresarial ce) {
      
       this.d = ce.getDescricao();
       this.factor = ce.getFactor();
       }
       
   
    public String[] getDescricao(){
        return this.d;
        }
    public double getFactor(){
        return this.factor;
        }
    public ArrayList getFaturas(){
	return this.desp;
        }

    public void setDescricao(String []s){
        this.d=s;
        }
    public void setFactor(double k){
        this.factor=k;
        }
    public void setFaturas(ArrayList x){
		 this.desp = x;
		 
		 }
	public void vTotalIDatas(){
		System.out.print("Insira o dia 1: ");
		int d,m,a;
		Scanner sc = new Scanner(System.in);
		do{
			d=sc.nextInt();
			}while((d<1)||(d>30));
			System.out.print("/");
		do{
			m=sc.nextInt();
			
			}while((d<1)||(d>12));
			System.out.print("/");
		do{
			a=sc.nextInt();
			
			}while((d<1)||(d>2018));
			
		System.out.print("\nInsira o dia 2: ");
		int d2,m2,a2;
		do{
			d2=sc.nextInt();
			sc.nextLine();
			}while((d<1)||(d>30));
			System.out.print("/");
		do{
			m2=sc.nextInt();
			sc.nextLine();
			}while((d<1)||(d>12));
			System.out.print("/");
		do{
			a2=sc.nextInt();
			
			}while((d<1)||(d>2018));	
		
			System.out.print("\n");	
			
		double sum = 0;
		ArrayList fat = this.desp;
		Despesa des = new Despesa();
		for(int i = 0;i<fat.size();i++){
			des = (Despesa)fat.get(i);
			if(((des.getData().getYear()>=a) && (des.getData().getYear()>=a2)) &&
				((des.getData().getMonthValue()>=m) && (des.getData().getMonthValue()>=m2)) &&
				((des.getData().getDayOfYear()>=d) && (des.getData().getDayOfYear()>=d2)))
				
					sum +=des.getVDesp();
			
			}
			
		System.out.printf("O valor total faturado entre %d/%d/%d e %d/%d/%d foi de %.2f\n",d,m,a,d2,m2,a2,sum);
		}	
	public boolean existe(String nFiscal){
		ArrayList x = this.desp;
		int i;
		Despesa d = new Despesa();
		for(i=0;i<this.desp.size();i++){
			d=(Despesa)x.get(i);
			if(d.getNFiscal().equals(nFiscal))
				return true;
			}
		return false;	
		}
		
	public void faturasCIndividual(){
		String nFiscal;
		Scanner sc = new Scanner(System.in);
		System.out.print("Insira o numero fical: ");
		do{
		nFiscal = sc.next();
		}while(!existe(nFiscal));
		int i;
		ArrayList x = this.desp;
		Despesa d = new Despesa();
		for(i=0;i<this.desp.size();i++){
			d=(Despesa)x.get(i);
			if(d.getNFiscal().equals(nFiscal))
				System.out.print(d.toString());
			}
		
		} 
	public void vTotal(){
		double sum=0;
		int i;
		ArrayList x = this.desp;
		Despesa d = new Despesa();
		for(i=0;i<this.desp.size();i++){
			d=(Despesa)x.get(i);
			sum=sum+d.getVDesp();
			}
		
		System.out.printf("O valor total faturado foi de %.2f\n",sum);
		}
	public double deducaoFiscal(){
		ArrayList x = this.desp;
		int i ;
		double sum = 0;
		Despesa d = new Despesa();
		Map<String,Double> y = codigos();
		String c;
		for(i=0;i<x.size();i++){
			
			d = (Despesa)x.get(i);
			c=d.getNatur();
			sum+=d.getVDesp()*y.get(c);
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
    
    public String toString () {
        return "Contribuinte: " +this.numFiscal +
               "\nDescriçao: " +this.d +
               "\nFactor: " +this.factor;
    }
    
    public ContribuinteEmpresarial clone()  {
        return new ContribuinteEmpresarial (this);
    }
    
     public boolean equals (Object o) { 
       if (this == o) return true;
       if ((null == o) ||this.getClass() != o.getClass()) return false;
      
       ContribuinteEmpresarial c = (ContribuinteEmpresarial) o;
       return  this.d == c.getDescricao() && 
              this.factor == c.getFactor();
    }
    
}


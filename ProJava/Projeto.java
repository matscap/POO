/**
 * Projeto POO 17/18 
 * LCC
 * 
 * Matias Capitao A82726
 * Jose Oliveira  A79346
 * Rafael Antunes A77457
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.io.*;


public class Projeto {
    static Scanner sc=new Scanner(System.in);
    
    
      public static void main (String [] args) throws IOException,ClassNotFoundException{
         
        
        Map <String, ContribuinteEmpresarial> he = new HashMap<String, ContribuinteEmpresarial>();
        Map <String, ContribuinteIndividual> hi = new HashMap<String, ContribuinteIndividual>();
        
        global b = new global();
        
        try{
			b=b.leEstado("teste.txt");
			he =b.getEmp();
			hi =b.getInd();
			}catch(IOException e){
			System.out.println("Nada em registo!");
			}
  
        int op=0,op2;
        
           System.out.print("*****************************************\n");
           System.out.print("*               Bem vindo               *\n");
           System.out.print("*****************************************\n\n\n"); 
           
        do{
          
           if(he.isEmpty()&& hi.isEmpty()){
             System.out.print("Nenhuma conta em registo.\nIremos encaminha-lo para a pagina de registo.\n\n");
             
                    paginaRegisto(he,hi); 
                    
            } else{
                    System.out.print("Escolha uma opcao:\n");
                    System.out.print(" 1-Login\n");
                    System.out.print(" 2-Registo\n\n");
                    System.out.print("Opcao: ");
                    op=sc.nextInt();
                    System.out.print("\n\n!");
                   } 
                   
         
           switch(op){
            case 1: logar(he,hi); break;
            case 2: paginaRegisto(he,hi);  break;
           }
			b.setEmp(he);
			b.setInd(hi);
			b.guardaEstado("teste.txt");
			
           }while(op!=-1);
		
     }
    
      public static void logar(Map he,Map hi){
		String admin = "admin";
		String passA = "12345";
        String k;
        String op;
        
        System.out.print("\nNumero Fiscal: ");
         do{
           k=sc.next();
		   if(k.equals(admin)) break;
			    
           if((he.containsKey(k)==false) && (hi.containsKey(k)==false)){ 
             System.out.println("Nao existe nenhum registo com o numero fiscal indicado.\n");
            
             System.out.print("Deseja voltar ao menu inicial? s/n: ");
                 op=sc.next();
                
             switch(op){
              case "s": return;
              case "n": System.out.print("Numero Fiscal: "); break;
             }
          }
        }while((he.containsKey(k)==false) && (hi.containsKey(k)==false));
        
          ContribuinteEmpresarial y= new ContribuinteEmpresarial();
          ContribuinteIndividual x= new ContribuinteIndividual();
          Contribuinte c= new Contribuinte();
          String passo;
        
        if(he.containsKey(k)==true){
            
            y=(ContribuinteEmpresarial)he.get(k);
			passo = y.getPassword();
            
            }else if(hi.containsKey(k)==true){
                x=(ContribuinteIndividual)hi.get(k);
                passo = x.getPassword();
                
                }else{
					passo= passA;
					}
                
         
         String pass;
         
        do{
           System.out.print("Password: ");
           pass=sc.next();
          if(!pass.equals(passo)){
             System.out.print("Password incorreta. Tente novamente.\n");
          }
         }while(!pass.equals(passo));
          System.out.println("");
      
          if(he.containsKey(k)==true){
            
           menuEmpresarial(hi,he,k); 
            
            }else if(hi.containsKey(k)==true){
                menuIndividual(x,hi,he); 
                }else{
					menuAdmnistrador(hi,he);
					}
               
         
                
     }
    
      public static void paginaRegisto(Map he,Map hi){
        
        ContribuinteIndividual ci = new ContribuinteIndividual();
        ContribuinteEmpresarial ce = new ContribuinteEmpresarial();
        System.out.print("-----------------------------------------\n");
        System.out.print("          Pagina de registo\n-----------------------------------------\n\n");
        
        String x;
        int op;
        char c;
        System.out.print(" 1-Contribuinte Individual\n 2-Contribuinte Empresarial\nIndique o tipo de Contribuinte: ");
        do{
        op = sc.nextInt();
		}while(((op==1)||(op==2))==false);
        
        System.out.print("\nNumero Fiscal (9 digitos): ");
        do{ //Este filtro faz com que o NIF seja apenas constituido por 9 numeros (se tiver letras ou  menos de 9 chars da erro)
          x = sc.next();
         for (int i = 0; i<x.length(); i++)  
            if (Character.isDigit(x.charAt(i))==false || x.length()!=9) {
              x = x.substring(0,(x.length()-x.length())); 
              System.out.print("Numero Fiscal invalido ou já existente no registo.\nPor favor verifique o seu NIF: ");
              }
              
        }while((x.length()!=9)||(hi.containsKey(x)==true)||(he.containsKey(x)==true) );
        
        String pass;
        do{ 
            System.out.print("Password: ");
            pass = sc.next();
            if(pass.length()<5) System.out.print("Password demasiado curta.Por favor insira novamente: ");
         }while(pass.length()<5);
        
        String pass2;

        do{
            System.out.print("Confirme a password: ");
            pass2=sc.next();
            if(!pass2.equals(pass)) System.out.print("As passwords sao diferentes, por favor insira novamente: ");
         }while(!pass2.equals(pass));
 
          switch(op){
            case 1: ci = registoIndividual(x,pass);
					hi.put(x,ci);
					break;
            
            case 2: ce = registoEmpresarial(x,pass); 
					he.put(x,ce);
					break;
         }
         
 

     }
    
      public static ContribuinteIndividual registoIndividual(String x,String pass){
        
        ContribuinteIndividual ci = new ContribuinteIndividual();
    
        
        ci.setNumFiscal(x);
        ci.setPassword(pass);
        System.out.println("\n__________ Informacoes __________\n\n");
        System.out.println("Insira as suas info's");
        
        System.out.print("E-mail: "); //<--------------Sugestao  inserir filtro para que se o @ nao aparecer, da erro
        String mail = sc.next();
            int contador = 0;
          for( int i=0; i<mail.length(); i++ ) {
               if( mail.charAt(i) == '@' ) {
                 contador++;
              }
          }
          if(contador != 1)  {
              System.out.print("E-mail invalido.\nPor favor verifique se o escreveu corretamente.\n");
              System.out.print("E-mail: ");
              mail = sc.next();
            } //Bug. Nao e um ciclo infinito. corrigir mais tarde     
        ci.setMail(mail);
        
        System.out.print("Nome: ");
        String nome = sc.next();
        nome = nome+sc.nextLine();
        ci.setNome(nome);
        
        System.out.print("Morada: ");
        String morada = sc.next();
        morada = morada + sc.nextLine();
        ci.setMorada(morada);
        
        System.out.print("Numero de dependentes do agregado familiar: ");
        int n = sc.nextInt();
        ci.setNdep(n);
        
        String[] nf = new String[n];
        System.out.print("\nNumeros fiscais do agregado familiar: \n");
          for(int i=0;i<n;i++){
           System.out.printf("Numero fiscal (9 digitos) da entidade %d:  ",i+1);
             String s;
             do { 
                  nf[i]=sc.next();
                  s = nf[i];
                 for(int j =0; j<s.length(); j++)
                    if (Character.isDigit(s.charAt(j))==false || s.length() != 9) {
                       s = s.substring(0,0);
                      System.out.print("Numero Fiscal Invalido\nPor favor verifique o NIF: ");
                   }
                      
                } while((s.length() != 9)||(s.equals(x)));
                
            System.out.print("\n");
         }
        
        System.out.print("\n\n");
        ci.setNfiscais(nf);
        
        
        return ci;
     }
     public static boolean invalido(String s,int i, String[]array){
		 int c=0;
		 int j;
		 for(j=0;j<=i;j++)
			 
			 if(s==array[j]) c++;
			 
		if(c>1) return true;
			 
		 return false;
		 }
    
      public static ContribuinteEmpresarial registoEmpresarial(String x,String pass){

        ContribuinteEmpresarial ce = new ContribuinteEmpresarial();
        
        
        ce.setNumFiscal(x);
        ce.setPassword(pass);
        System.out.println("\n__________ Informacoes __________\n\n");
        System.out.println("Insira as suas info's");
        
        System.out.print("E-mail: "); //<--------------Sugestao  inserir filtro para que se o @ nao aparecer, da erro
        String mail = sc.next();
            int contador = 0;
          for( int i=0; i<mail.length(); i++ ) {
               if( mail.charAt(i) == '@' ) {
                 contador++;
              }
          }
          if(contador != 1)  {
              System.out.print("E-mail invalido.\nPor favor verifique se o escreveu corretamente.\n");
              System.out.print("E-mail: ");
              mail = sc.next();
            } //Bug. Nao e um ciclo infinito. corrigir mais tarde     
        ce.setMail(mail);
        
        System.out.print("Nome: ");
        String nome = sc.next();
        nome+=sc.nextLine();
        ce.setNome(nome);
        
        System.out.print("Morada: ");
        String morada = sc.next();
        morada+=sc.nextLine();
        ce.setMorada(morada);
        
        System.out.print("Em quantas atividades opera: ");
        int o = sc.nextInt();
       
		System.out.println("Tabela de codigos deduziveis: \n\n");  
		System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
		System.out.println("| 0000 -                           Saude                          | 0001 -        Premios de seguros de saude          |");
		System.out.println("| 0010 - Juros de emprestimos para habitacao propria e permanente | 0011 - Rendas de imoveis para habitacao permanente |");
		System.out.println("| 0100 -           Encargos com a reabilitacao de imoveis         | 0001 -        Premios de seguros de saude          |");
		System.out.println("| 0101 -                         Educacao                         | 0110 -              Despesas Gerais                |");
		System.out.println("| 0111 -                      IVA de faturas                      | 1000 -                   Lares                     |");
		System.out.println("| 1001 -                  Pensoes de alimentos                    | 1010 -         PPR e fundos de pensoes             |");
		System.out.println("| 1011 -            Regime publico de capitalizacao               | 1100 -                 Donativos                   |");
		System.out.println(" ----------------------------------------------------------------------------------------------------------------------\n\n\n"); 
       
        String []d=new String[o+1];
        d[0]="pd";
        System.out.print("Os codigos da lista acima servem apenas para casos de deducao fiscal. \nSe o tipo de atividade praticado da estiver identificado acima deve inserir o codigo que tem.\n\n");
        
        for(int i = 1;i<o+1;i++){
		
		System.out.printf("Insira o codigo de atividade %d: ",i);
		d[i] = sc.next();
		
		ce.setDescricao(d); 
		}      
                
            
        
        System.out.print("\n\n");
        
       
        return ce;
        
     }
    
	  public static void menuIndividual(ContribuinteIndividual c,Map hi,Map he){
		  
		  int op;
		  do{
		  if(!c.getFatPorCla().isEmpty()) System.out.printf("Tem %d faturas por classificar\n\n",c.getFatPorCla().size());
          System.out.print("0-Voltar\n");
          System.out.print("1-Aceder a faturas\n2-Agregado familiar\n");
          if(!c.getFatPorCla().isEmpty()) System.out.printf("3-Classicar faturas\n");
          System.out.print("Opcao: ");
          op=sc.nextInt();
            
          switch(op){
            case 0: System.out.println("\n"); return;
            case 1: imprimeFaturas(c); break;
            case 2: vDeduzidoAgregado(hi,c); break;
            case 3: classFaturas(c,he); break;
          }
                
           }while(op!=-1);
		  
		  }
	 public static void vDeduzidoAgregado(Map hi,ContribuinteIndividual c){
		 String[] a = c.getNfiscais();
		 ContribuinteIndividual x = new ContribuinteIndividual();
		 int i;
		 double sum = 0;
		 for(i=0;i<a.length;i++){
			 if(hi.containsKey(a[i])){
			 x = (ContribuinteIndividual)hi.get(a[i]);
			 sum+=x.vDeduzido();}
			 }
		System.out.printf("O valor total deduzido pelo seu agregado familiar foi de %.2f\n",sum);
		 
		 }
	 public static void classFaturas(ContribuinteIndividual ci,Map he){
		 ArrayList<Despesa> x = ci.getFatPorCla();
		 
		 ArrayList<Despesa> cla = ci.getFaturas();
		 ContribuinteEmpresarial y = new ContribuinteEmpresarial();
		 int i;
		 Despesa d = new Despesa();
		 for(i=0;i<x.size();i++){
			 d = (Despesa)x.get(i);
			 y=(ContribuinteEmpresarial)he.get(d.getNFEmit());
			 
			 System.out.print("Emitente:\n"+d.getNFEmit()+"\nDesignacao Emitente: "+d.getDEmit()+"\nData: "+d.getData()+"\nNumero fiscal: "+d.getNFiscal()+"\nDescricao: "+d.getDescri()+"\nValor: "+d.getVDesp());
			 String []nat;
			 nat = y.getDescricao();
			System.out.print("\nOpcoes: \n");
			int j;
			for(j=1;j<nat.length;j++){
				System.out.printf("     %s\n",nat[j]);
				
			 }
			 System.out.print("\n\n\n");
			 String z;
			System.out.print("Opcao: ");
			do{
			
				z=sc.next();
			
			}while(existe(nat,z)); 
			
			 d.setNatur(z);
			 cla.add(d);
			
			 x.remove(i);
			 
			 ci.setFatPorCla(x);
			 ci.setFaturas(cla);
			 
			 }
		 
		 }
	 public static void imprimeFaturas(ContribuinteIndividual c){
		int i;
		Despesa k = new Despesa();
		ArrayList d = c.getFaturas();
		for(i=0;i<d.size();i++){
			k=(Despesa)d.get(i);
			System.out.print(k.toString());
			} 
		 double x = c.vTotal();
		 double y = c.vDeduzido();
		 System.out.printf("Total gasto: %.2f  Total deduzido: %.2f\n",x,y);
			
		 }
		 
	 
	 public static void menuEmpresarial(Map hi,Map he,String nif){
		 ContribuinteEmpresarial ce =(ContribuinteEmpresarial)he.get(nif);
         int op;
        do{
          System.out.print("0-Voltar\n");
          System.out.print("1-Aceder a faturas\n");
          System.out.print("2-Associar faturas\n");
          System.out.print("3-Faturado\n");
          System.out.print("Opcao: ");
          op=sc.nextInt();
            
          switch(op){
			case 0:  return;
            case 1: acederFaturasEmpresa(ce); break;
            case 2: associarFaturas(hi,he,nif);
                    break;
            case 3: vFaturado(ce); break;
            
           
                
          }
               System.out.println("\n"); 
           }while(op!=-1);
		 
		 
		 }
	  public static void vFaturado(ContribuinteEmpresarial ce){
		  int op;
		  do{
		  System.out.print("0-Voltar\n1-Valor Total\n2-Intervalo de datas\nOpcao: ");
		  op = sc.nextInt();
		  
			
		  
		  switch(op){
			  
			  case 0: return;
			  case 1: ce.vTotal(); break;
			  case 2: ce.vTotalIDatas(); break;
			  
			  }
		  }while(op!=-1);
		  
		  }
	  public static void acederFaturasEmpresa(ContribuinteEmpresarial ce){
		  int op;
		  do{
		  System.out.print("0-Voltar\n1-Por valor\n2-Por data\n3-Por Contribuinte\nOpcao: ");
		  op = sc.nextInt();
		  switch(op){
			  case 0: System.out.println("\n"); return;
			  case 1: acederPorValor(ce); break;
			  case 2: acederPorData(ce); break;
			  case 3: ce.faturasCIndividual(); break;
			  }
		  
	      }while(op!=-1);
		  
		  }
	  public static void acederPorData(ContribuinteEmpresarial ce){
		  ArrayList x = ce.getFaturas();
		  Despesa d = new Despesa();
		  for(int i = 0;i<x.size();i++){
			  
			  d = (Despesa)x.get(i);
			  System.out.print(d.toString());
			  
			  }
		  
		  }
      public static void acederPorValor(ContribuinteEmpresarial ce){
		  int i;
		  ArrayList x = ce.getFaturas();
		  Despesa k = new Despesa();
		  Map<Integer,Double> m = new HashMap<Integer,Double>();
		  for(i = 0;i<x.size();i++){
			  
			  k=(Despesa)x.get(i);
			  m.put(i,k.vDesp);
			  
			  }
			  
		 Map<Integer, Double> result = m.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                        
		 Iterator h = result.entrySet().iterator();

		 while (h.hasNext()) {
				Map.Entry pair = (Map.Entry)h.next();
				
				k=(Despesa)x.get((Integer)pair.getKey());
				
				System.out.print(k.toString());
				
				//it.remove(); // avoids a ConcurrentModificationException
				
			}
		  
		  }  
      public static void associarFaturas(Map hi,Map he,String nif){
		System.out.println("\n\n");
		/*System.out.println("Tabela de codigos: \n\n");  
		System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
		System.out.println("| 0000 -                           Saude                          | 0001 -        Premios de seguros de saude          |");
		System.out.println("| 0010 - Juros de emprestimos para habitacao propria e permanente | 0011 - Rendas de imoveis para habitacao permanente |");
		System.out.println("| 0100 -           Encargos com a reabilitacao de imoveis         | 0001 -        Premios de seguros de saude          |");
		System.out.println("| 0101 -                         Educacao                         | 0110 -              Despesas Gerais                |");
		System.out.println("| 0111 -                      IVA de faturas                      | 1000 -                   Lares                     |");
		System.out.println("| 1001 -                  Pensoes de alimentos                    | 1010 -         PPR e fundos de pensoes             |");
		System.out.println("| 1011 -            Regime publico de capitalizacao               | 1100 -                 Donativos                   |");
		System.out.println(" ----------------------------------------------------------------------------------------------------------------------\n\n\n");*/
		
        Despesa des = new Despesa();
        LocalDateTime date=LocalDateTime.now();
        String a,b,c,d,e,f;
        double v;
        ContribuinteEmpresarial ce = (ContribuinteEmpresarial)he.get(nif);
        System.out.printf("Emitente: %s",nif);
        des.setNFEmit(nif);
        System.out.printf("\nDesignacao do emitente: %s",ce.getNome());
        des.setDEmit(ce.getNome());
		System.out.print("\nData: ");
		System.out.print(date);
        des.setData(date);
        System.out.print("\nNumero fiscal do cliente: ");
        do{
        d=sc.next();
		}while(hi.containsKey(d)==false);
        des.setNFiscal(d);
        System.out.print("Descricao da despesa: ");
        e=sc.next();
        e = e+sc.nextLine();
        des.setDescri(e);
        System.out.print("Natureza da despesa: \n Opcoes disponiveis (Caso nao queira definir insira 'pd'): \n");
        
        String [] natureza = ce.getDescricao();
        
        for(int i = 1;i<natureza.length;i++){
			
			System.out.printf("        %s",natureza[i]);
			
			}
		System.out.print("\n");
		do{	
			f=sc.next();
		}while(existe(natureza,f));
		
        des.setNatur(f);
        System.out.print("Valor: ");
        v=sc.nextDouble();
        des.setVDesp(v);
        
		
		ContribuinteIndividual ci = (ContribuinteIndividual)hi.get(d);
		
		ArrayList fate = ce.getFaturas();
		ArrayList fat = new ArrayList<>();
		
		if(f.equals("pd")){  
			fat = ci.getFatPorCla();
			fat.add(des);
			fate.add(des);
			ci.setFatPorCla(fat);    //Algo esta a bater mal!!!!!
		}else{ 
			fat = ci.getFaturas();
			fat.add(des);
			fate.add(des);
			ci.setFaturas(fat);
		}
		
		ce.setFaturas(fate);
		
		hi.put(d,ci);
		he.put(nif,ce);
	
     }
    public static boolean existe(String []n, String s){
		
		for(int i = 0;i<n.length;i++){
			
			
			if(n[i]==s) return true;
			
			}
		return false;
		}
      public static void menuAdmnistrador(Map hi,Map he){
		  int op;
		
		  System.out.printf("Tem %d contribuintes individuais e %d contribuintes empresariais em registo.\n\n",hi.size(),he.size());
		do{
			System.out.print("0-Voltar\n1-Relacao dos 10 contribuintes que mais gastam\n2-Relacao dos X contribuintes que mais gastam\nOpcao: ");
			op=sc.nextInt();
			switch(op){
				case 0: System.out.println("\n"); return;
				case 1: dezCont(hi); break;
				case 2: XEmpresas(he); break;
				
				
				}
		}while(op!=-1);
     }
     
     public static void dezCont(Map hi){
		 Map <String, Double> c = new HashMap<String, Double>();
		 ContribuinteIndividual x = new ContribuinteIndividual();
		 double v = 0;
		 String s;
		 Iterator it = hi.entrySet().iterator();
		 
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				x = (ContribuinteIndividual)pair.getValue();
				v=x.vTotal();
				s=(String)pair.getKey();
				c.put(s,v);
				it.remove(); // avoids a ConcurrentModificationException
			}
		 
		 Map<String, Double> result = c.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		 
		 
		 
		 Iterator k = result.entrySet().iterator();
		 int z = 0;
		 System.out.println("Top 10 Contribuintes que mais gastam\n");
		 while (k.hasNext()) {
				Map.Entry pair = (Map.Entry)k.next();
				
				v=(Double)pair.getValue();
				s=(String)pair.getKey();
				System.out.printf("%d - Numero Fiscal: %s  Valor gasto: %.2f\n", z,s,v);
				//it.remove(); // avoids a ConcurrentModificationException
				z++;
				if(z==10) break;
			}
		 }
	
	public static void XEmpresas(Map he){
		int op;
		System.out.print("Pretende a relacao entre quantas Empresas? ");
		do{
		op = sc.nextInt();
		}while(op<2);
		Iterator it = he.entrySet().iterator();
		ContribuinteEmpresarial x = new ContribuinteEmpresarial();
		Map<String,Integer> m = new HashMap<String,Integer>();
		int valor;
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			x=(ContribuinteEmpresarial)pair.getValue();
			valor= x.getFaturas().size();
			m.put((String)pair.getKey(),valor);
			}
		
		 Map<String, Integer> result = m.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        it = m.entrySet().iterator();
        String a;
        int b=0;
        double c=0;
        int k=0;
        while(it.hasNext()){
			Map.Entry p = (Map.Entry)it.next();
			a=((ContribuinteEmpresarial)he.get((String)p.getKey())).getNome();
			b=(Integer)p.getValue();
			c=((ContribuinteEmpresarial)he.get((String)p.getKey())).deducaoFiscal();
			System.out.printf("\nNome: %s Numero de faturas: %d Total deduzido %.2f\n\n",a,b,c);
			k++;
			if(k==op) break;
			}
        
		
		}
	
}

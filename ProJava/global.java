import java.util.Map;
import java.util.HashMap;
import java.io.*;
public class global implements Serializable{
	
	private Map <String,ContribuinteEmpresarial> he = new HashMap<String,ContribuinteEmpresarial>();
	private Map <String,ContribuinteIndividual> hi = new HashMap<String,ContribuinteIndividual>();

	public Map getEmp(){
		
		return this.he;
		}
	public Map getInd(){
		
		return this.hi;
		}	
	public void setEmp(Map hee){
		this.he = hee;
		}
	public void setInd(Map hii){
		this.hi = hii;
		
		}
	public void guardaEstado(String nome) throws FileNotFoundException,IOException{
		FileOutputStream fos = new FileOutputStream(nome);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.flush();
		oos.close();
		}
	
	public global leEstado(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		global b = (global)ois.readObject();
		ois.close();
		return b;
		}
}


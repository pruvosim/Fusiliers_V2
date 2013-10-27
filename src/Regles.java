import java.util.Arrays;
import java.util.Random;


public class Regles {
	
	public int n = 5 + 1;
	public int [] rules;
	public int fitness;
	
	Initialization init;
	Random generator;
	
	public Regles()
	{
		rules = new int[n * n * n];
		fitness = -1;
	}
	
	public void init()
	{
		generator = new Random();
		init = new Initialization();
		
		init.init(rules);
	}
	
	public void eval()
	{
		Automata auto = new Automata(25);
		fitness = auto.f(rules, 25);
	}

	
	public Regles clone(Regles regles_a_cloner)
	{
		Regles result = new Regles();
		rules = copieRules(regles_a_cloner.rules);
		return result;
	}
	
	public int[] copieRules(int[] rules_a_copier)
	{
		int[] result = new int[rules_a_copier.length];
		
		for (int i = 0; i < rules_a_copier.length; i++) {
			result[i] = rules_a_copier[i];
		}
		
		return result;
		
	}

	@Override
	public String toString() {		
		return "Regles [fitness="+ fitness + ". rules=" + Arrays.toString(rules) + " ]";
	}

}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;


public class MainILS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Automata automate = new Automata(20);

		int [] rules = initRules();

		int fit = automate.f(rules, 20);
		System.out.println("Fit : " +fit);

		Initialization init = new Initialization();

		int ancien_fit = 0;
		
		IteratedLocalSearch ILS = new IteratedLocalSearch();
		ILS.iterer(1000000, rules, 15);
	}

	public static int [] initRules() {
		// 5 �tats + l'�tat "bord"
		int n = 5 + 1;

		int [] rules = new int[n * n * n];
		Random generator = new Random();

		try {

			for(int i=0;i<216;i++)
			{		
				rules[i] = generator.nextInt(4);
			} 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		return rules;
	}
	
	public static void printToFile(int fitness, int [] rules, PrintWriter ecrivain) {
		ecrivain.print(fitness);
		for(int i = 0; i < 216; i++) {
			ecrivain.print(" ");
			ecrivain.print(rules[i]);
		}
		ecrivain.println();
	}

}

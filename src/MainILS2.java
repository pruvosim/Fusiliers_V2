import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class MainILS2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Automata automate = new Automata(20);

		//int [] rules = initRules();
		int[] rules = new int[216];
		
		Initialization init = new Initialization();
		
		init.init(rules);

		int fit = automate.f(rules, 20);
		System.out.println("Fit : " +fit);

		//int ancien_fit = 0;
		
		ILS2 ils = new ILS2();
		ils.iterer(1000, 50);
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


public class MainEA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(100, 50);
		EA.selectionParentale();
		System.out.println(EA.toString());
		
	}

}

import java.text.DecimalFormat;
import java.util.Scanner;

public class Uzdevums {
	static Scanner scan = new Scanner(System.in);
	public static String[] ievadit(String[] studenti){
		//Ievada studentu vārdus, uzvārdus
		for(int i=0; i<studenti.length; i++) {
			System.out.println("Ievadi "+(i+1)+". studentu");
			studenti[i] = scan.next();
		}
		return studenti;
	}
	public static String[] ievaditKriteriju(String[] kriteriji ){
		//Definē kritērijus
				for(int i=0; i<kriteriji.length; i++) {
					System.out.println("Ievadi "+(i+1)+". kritēriju");
					kriteriji[i] = scan.next();
	}
				return kriteriji;
	}
	public static int[] svars(int[] kriterijaSvars, int kritSk){
		//Norāda katra kritērija svaru
		int maxSvars = 100;
		for(int i=0; i<kriterijaSvars.length; i++) {
				do {
					System.out.println("Ievadi "+(i+1)+". kritērija svaru");
					kriterijaSvars[i] = scan.nextInt();
				}while(kriterijaSvars[i]>maxSvars || 
						kriterijaSvars[i]<1 || 
						(kriterijaSvars[0]==100 && kritSk > 1));
				maxSvars -= kriterijaSvars[i];
			}
		return kriterijaSvars;
	}
	public static int[][] ievadaVertejumu(int[][] kriterijaVertejums, String[] studenti, String[] kriteriji){
		//Norāda vērtējumu kādu ieguvis katrs students par katru kritēriju
				for(int i=0; i<kriterijaVertejums.length; i++) {
					for(int j=0; j<kriterijaVertejums[i].length; j++) {
						do {
							System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
							kriterijaVertejums[i][j] = scan.nextInt();
						}while(kriterijaVertejums[i][j]<0 || kriterijaVertejums[i][j]>10);
					}
				}
		return kriterijaVertejums;
	}
	public static double[] izrekinat(int[][] kriterijaVertejums, String[] studenti, int[] kriterijaSvars, double[] semestraVertejums,String[] kriteriji){
		DecimalFormat df = new DecimalFormat("0.#");  
		double rezultats;
		for(int i=0; i<studenti.length; i++) {
			rezultats=0;
			for(int j=0; j<kriteriji.length; j++) {
				rezultats += ((double) kriterijaSvars[j]/100)*kriterijaVertejums[i][j];
			}
			semestraVertejums[i] = rezultats;
		}
		
		for(int i=0; i<studenti.length; i++) {	
			for(int j=0; j<kriteriji.length; j++) {
				System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
			}
			System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+"\n");
		}
		return semestraVertejums;
	}
	public static void izvadit(int[][] kriterijaVertejums, String[] studenti, int[] kriterijaSvars, double[] semestraVertejums,String[] kriteriji){
		DecimalFormat df = new DecimalFormat("0.#");  
		for(int i=0; i<studenti.length; i++) {	
			for(int j=0; j<kriteriji.length; j++) {
				System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
			}
			System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+"\n");
		}
	}
	public static void main(String[] args) {
		int studSk, kritSk,izvele;
		String[] studenti = null;
		String[] kriteriji = null;
		int[] kriterijaSvars = null;
		int[][] kriterijaVertejums = null;
		double[] semestraVertejums = null;
		do{
			System.out.println("1-Cik studenti un cik kritēriji un aprēķināšana | 2-Izvadīt |  3 - Apturēt programmu");
			izvele = scan.nextInt();
			switch(izvele){
			case 1:
				do {
					System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
					studSk = scan.nextInt();
				}while(studSk<1);
				studenti = new String[studSk];	
				ievadit(studenti);
				do {
					System.out.println("Kāds būs kritēriju skaits?");
					kritSk = scan.nextInt();
				}while(kritSk<1);
				 kriteriji = new String[kritSk];
				ievaditKriteriju(kriteriji);
				kriterijaSvars = new int[kritSk];
				svars(kriterijaSvars,kritSk);
				kriterijaVertejums = new int[studSk][kritSk];
				ievadaVertejumu(kriterijaVertejums,studenti,kriteriji);
				 semestraVertejums = new double[studSk];
				izrekinat(kriterijaVertejums,studenti,kriterijaSvars,semestraVertejums,kriteriji);
				break;
			case 2:
				izvadit(kriterijaVertejums,studenti,kriterijaSvars,semestraVertejums, kriteriji);
				break;
			case 3:
				System.out.println("Programmas beigas!");
				break;
			default: System.out.println("Tāda darbība nepastāv!");	
			}
		}while(izvele!=3);
		scan.close();
	}
}


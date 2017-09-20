
public class Main {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter source tree path: ");
		String src = kb.nextLine();
		System.out.println("Enter target repo folder path: ");
		String target = kb.nextLine();
		Repository rep = new Repository(src, target);
		rep.execute();
	}
	
	
}

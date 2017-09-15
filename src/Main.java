
public class Main {

	public static void main(String[] args) {
		String src = "C:\\Users\\Rich Homie Quan\\SCM\\SCM\\mypt2";
		String target = "C:\\Users\\Rich Homie Quan\\SCM\\SCM\\mypt3";
		Repository rep = new Repository(src, target);
		rep.execute();
	}
	
	
}

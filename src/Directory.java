import java.util.ArrayList;
import java.util.List;

public class Directory {
	private Node root;
	
	public Directory(String name)
	{
		root = new Node();
		root.name = name;
		root.childen = new ArrayList<Node>();
	}
	
	public static class Node
	{
		private String name;
		private Node parent;
		private ArrayList<Node> childen;
	}
	
}

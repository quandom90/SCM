import java.io.File;

public class Repository {
	
	private String src;
	private String target;
	
	
	public Repository(String src, String target){
		this.src = src;
		this.target = target;
		
		File root = new File(src);
		File[] listFile = root.listFiles();
		
		for(File f : listFile){
			if (f.isDirectory()){
				
			}else if (f.isFile()){
				
			}
		}
	}
	
	private void Duplicate(String directory){
		File root = new File(directory);
		File[] listFile = root.listFiles();
		
		for(File f : listFile){
			if (f.isDirectory()){
				
			}else if (f.isFile()){
				
			}
		}
	}
	
	
}

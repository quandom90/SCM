import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class Repository {
	
	private String src;
	private String target;
	
	/*
	 * Contructor
	 * @param src - string path of project to be copied
	 * @param target - string path of target directory to store copied files from src 
	 */
	public Repository(String src, String target){
		this.src = src;
		this.target = target;
		
	}
	
	//Getter
	public String getSrc(){
		return src;
	}
	
	public String getTarget(){
		return target;
	}
	
	/*
	 * Initiates the ptree for the repository by copying the root folder from src and storing it in target
	 */
	public void execute(){
		if ((target != "")&&(src != "")){
			File rootSrc = new File(src);
			String rootRep = target + File.separator + rootSrc.getName();
			File rep = new File(rootRep);
			
			if (rep.mkdir()){
				try {
					//once root folder is created for repository calls method to create 
					//the rest of the ptree
					duplicate(src, rootRep);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Creates the folders and files for the rest of the ptree for the repository. Uses recursion to traverse the ptree
	 * @param directory - the string path of the current leaf of the ptree traversal from the source to be copied from
	 * @param rep - the string path of the current leaf of the ptree traversal from the target to be stored in 
	 */
	private void duplicate(String directory, String rep) throws IOException{
		File dir = new File(directory);
		File[] listFile = dir.listFiles();
		
		for(File f : listFile){
			if (f.isDirectory()){
				String newTarget = rep + File.separator + f.getName();
				File newDir = new File(newTarget);
				newDir.mkdir();
				duplicate(f.getAbsolutePath(), newTarget);
			}else if (f.isFile()){
				String newName = aid(f);
				String newTarget = rep + File.separator + f.getName();
				File newDir = new File(newTarget);
				newDir.mkdir();
				
				File newFile = new File(newTarget + File.separator + newName);
				InputStream in = new FileInputStream(f);
				OutputStream out = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				int length = in.read(buffer);
				
				while (length > 0){
					out.write(buffer, 0, length);
					length = in.read(buffer);
				}
				
				in.close();
				out.close();
			}
		}
	}
	
	/*
	 * Generates Artifact Code names for files with checksum
	 * @param file - the file which a code name will be generated from
	 * @return - the code string created by taking the checksum of the file
	 */
	public String aid(File file){
		try {
			FileInputStream fileRead = new FileInputStream(file);
			
			int c = fileRead.read();
			int[] weight = {1, 3, 7, 11, 17};
			int weightIndex = 0;
			int checkSum = 0;
			
			while(c != -1){
				 if ((char) c != '\n'){
					 checkSum += (weight[weightIndex] * c);
					 
					 if (weightIndex >= 4)
							weightIndex = 0;
						else
							weightIndex++;
				 }
				 
				 c = fileRead.read();
			}
			
			fileRead.close();
			String[] fileName = file.getName().split("\\.");
			String ext = fileName[fileName.length - 1];
			return (checkSum + "." + file.length() + "." + ext);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	
		
	}
	
	
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("fred.txt");
		System.out.println(aid(file));
	}
	
	public static String aid(File file){
		try {
			FileInputStream fileRead = new FileInputStream(file);
			
			int c = fileRead.read();
			int[] weight = {1, 3, 7, 11, 17};
			int weightIndex = 0;
			int checkSum = 0;
			
			while(c != -1){
				 if ((char) c != '\n'){
					 checkSum += (weight[weightIndex] * c);
					 c = fileRead.read();
					 if (weightIndex >= 4)
							weightIndex = 0;
						else
							weightIndex++;
				 }
				 
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

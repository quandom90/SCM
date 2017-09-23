import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
	
	//	Runs main program
	//	Accepts user input of a src and target path to execute repo cloning
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter source tree path: ");
		String src = kb.nextLine();
		System.out.println("Enter target repo folder path: ");
		String target = kb.nextLine();
		
		Repository rep = new Repository(src, target);
		rep.execute();

		System.out.println("repo created");
		
		//	Generate Manifest File
		String manifestDir = target + File.separator + "manifest.txt";
		File manifest = new File(manifestDir);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(manifest, true));
			bw.write("Codename: NMX\n");
			bw.write("Time repo created: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + "\n");
			bw.write("User command: " + src + " " + target +"\n");
			bw.write("Source path: " + src + "\n");
			bw.write("Target path: " + target + "\n");
			
			File srcFile = new File(src);
			File dir = new File(target + File.separator + srcFile.getName());
			findFiles(dir, manifestDir, bw);
			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	Recursively travel through the directory
	//	to write info for each file copied into the manifest file.
	public static void findFiles(File dir, String manifestDir, BufferedWriter bw) throws IOException
	{
		//	Get list of files in directory ignoring the .DS_Store file
		File[] fileList = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.equals(".DS_Store");
			}
		});
		
		for(File f: fileList)
		{
			if(f.isDirectory())
				findFiles(f, manifestDir, bw);
			else if(f.isFile())
			{
				String aid = f.getName();
				String parent = f.getParentFile().getName();
				String path = f.getPath();
				String s = "File Copied Info: " + aid + " " + parent + " " + path + "\n";
				bw.write(s);
			}
		}
	}
}
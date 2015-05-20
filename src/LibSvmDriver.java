import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.csvreader.CsvWriter;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class LibSvmDriver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filedirectory = new File("").getAbsolutePath();
		String filedirectory_temp;
		String csv_file = null;
		String arff_file = null;
		String edge = null;
		String one = "1";
		String csv = ".csv";
		String arff = ".arff";
		String string_yes = "Yes";
		String string_no = "No";
		String filename = null;
		String temp = null;
		File file = null;
		file = new File(filedirectory);
		String[] paths = file.list();
		String news = "new";
		BufferedReader reader = null;
	    FileInputStream fis = null;
		String extension;
		Boolean bool =  false;
		String set;
		for (String path: paths){
			
			extension = path.substring(path.lastIndexOf(".") + 1, path.length());
			
			if (extension.equals(news)){
				
				filename  = path.substring(0, path.lastIndexOf("."));
				csv_file = filename + csv;
				File csvfile = new File(filedirectory + "//" + csv_file);
				
				if (!csvfile.exists()){
					csvfile.createNewFile();
				}
				
				CsvWriter csvwriter = new CsvWriter(new FileWriter(csvfile,true),',');
				
				for (int i = 0; i <= 56; i++){
					edge = "a" + String.valueOf(i);
					csvwriter.write(edge);
				}
				
				csvwriter.write("class");
				csvwriter.endRecord();
				
				arff_file = filename + arff;
				arff_file = filedirectory + "//" + arff_file;
				
				filedirectory_temp = filedirectory + "//" + path;
				fis = new FileInputStream(filedirectory_temp);
				reader = new BufferedReader(new InputStreamReader(fis));
				// Read the first line of the file
	               String line;
	               StringTokenizer stringtokenizer = null;
	               while ((line = reader.readLine()) != null){
	            	   
	            	   stringtokenizer = new StringTokenizer(line, " ");   
	            	   bool = true;
	            	   
	            	   while(stringtokenizer.hasMoreTokens()){	   
	            		   
	            		   set = stringtokenizer.nextToken();
	            		   
	            		   if (bool == true){
	            			   
	            			   if (set.equals(one)){
	            				   
	            				   temp = string_yes;
	            				   bool = false;
	            				   
	            			   } else {
	            				   
	            				   temp = string_no;
	            				   bool = false;
	            				   
	            			   }
	            		   } else {
	            			   
	            			   csvwriter.write(set.substring(set.lastIndexOf(":") + 1, set.length()));
	            			   
	            		   }
	            	   }
	            	   
	            	  csvwriter.write(temp);
	            	  csvwriter.endRecord();
	            	  
	               }
	               
	               csvwriter.flush();
	       		   csvwriter.close();
	       		   
	       		   CSVLoader loader = new CSVLoader();
	       		   loader.setSource(csvfile);
	       		   Instances data = loader.getDataSet();
	       		   
	       		   ArffSaver saver = new ArffSaver();
	       		   saver.setInstances(data);
	       		   saver.setFile(new File(arff_file));
	       		   saver.writeBatch();
	       		   
	       		   csv_file = null;
	       		   arff_file = null;
			}
		}
	}

}

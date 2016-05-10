import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import logic.BPFactory;
import logic.WordDataFactory;

import com.csvreader.CsvReader;

public class DatabaseTrain {

	public static void main(String[] arg) throws IOException{
		try {      
			String BPFileName = "DPData.ser";
	 		String WordFileName = "WordData.ser";
			File file = new File(BPFileName);
			if (file.exists()) {
		try {
			BPFactory.initialization(file);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} else {
			BPFactory.initialization(28*28, 86,
				10);
	}

	file = new File(WordFileName);
	if (file.exists()) {
		try {
			WordDataFactory.initialization(file);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} else {
		WordDataFactory.initialization();
	}
            
            
            ArrayList<String[]> csvList = new ArrayList<String[]>(); //store the data  
            String csvFilePath = "/Users/Sanky/Downloads/2.csv";  
             CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("SJIS"));    //GBK or something     
               
             //reader.readHeaders(); // skip the head  
               
             while(reader.readRecord()){ //read the data      
                 csvList.add(reader.getValues());  
             }              
             reader.close();  
             System.out.println(1);
               
            // for(int row=0;row<csvList.size();row++){  
                   
              //   String  cell = csvList.get(row)[1]; //get the data at row 0 col 1 
                // System.out.println(cell);  
                   
             //} 
             for(int row=0;row<csvList.size();row++){
            	 double double_map[]=new double[28*28];
            	 int x;
            	 x=Integer.parseInt(csvList.get(row)[0]);
            	 double double_data[]=new double[10];
            	 double_data[x]=1;
             
            	 for(int i=0;i<28;i++){
            		 for(int j=0;j<28;j++){
            			 String  cell = csvList.get(row)[i*28+j+1];
            			 if(Double.parseDouble(cell)>127)
            				 double_map[i*28+j]=1.0;
            			 else
            				 double_map[i*28+j]=0.0;
            			//double_map[i*28+j]=(Double.parseDouble(cell))/255.0;

            		 } 
            	 	}
            	 System.out.println(1);
            	 BPFactory.train(double_map,double_data);
            	 System.out.println(1);
            	 System.out.println("the label is "+x+"the row is "+row);
             }
             	 //String BPFileName = "DPData.ser"; // save the file
         	 //String WordFileName = "WordData.ser";
         	 File file = new File(BPFileName);
		 if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			try {
				BPFactory.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			file = new File(WordFileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				WordDataFactory.save(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               
               
        }catch(Exception ex){  
            System.out.println(ex);  
        }  
    }  
	}

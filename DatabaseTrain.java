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
            
            ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据  
            String csvFilePath = "/Users/Sanky/Downloads/2.csv";  
             CsvReader reader = new CsvReader(csvFilePath,',',Charset.forName("SJIS"));    //一般用这编码读就可以了      
               
             //reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。  
               
             while(reader.readRecord()){ //逐行读入除表头的数据      
                 csvList.add(reader.getValues());  
             }              
             reader.close();  
             System.out.println(1);
               
            // for(int row=0;row<csvList.size();row++){  
                   
              //   String  cell = csvList.get(row)[1]; //取得第row行第0列的数据  
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
             String BPFileName = "DPData.ser";
         	 String WordFileName = "WordData.ser";
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

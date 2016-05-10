package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BPFactory {
	
	private static BP bp;
	
	/**
	 * initialize BPNN
	 * @param inputSize
	 * @param hiddenSize 
	 * @param outputSize
	 */
	public static void initialization(int inputSize,int hiddenSize,int outputSize) {
		bp=new BP(inputSize, hiddenSize, outputSize);
	}
	
	/**
	 * read BPNN info from file
	 * @param file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void initialization(File file) throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream(file);
		ObjectInputStream si = new ObjectInputStream(fi); 
		bp = (BP) si.readObject(); 
		si.close();
	}
	
	/**
	 * save the BPNN to specific file
	 * @param file
	 * @throws IOException
	 */
	public static void save(File file) throws IOException {
		FileOutputStream fo = new FileOutputStream(file);
		ObjectOutputStream so = new ObjectOutputStream(fo);
		so.writeObject(bp);
		so.close();
	}
	
	/**
	 * train BPNN
	 * @param trainData
	 * @param target
	 */
	public static double[] train(double[] trainData, double[] target) {
		double[] trainData1=new double[28*28];
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				trainData1[i*28+j]=trainData[j*28+i];
				System.out.print(trainData1[i*28+j]);}
			System.out.println();
		}
		return bp.train(trainData1, target);
	}
	
	/**
	 * return test result
	 * @param inData
	 * @return
	 */
	public static double[] test(double[] trainData) {
		double[] trainData1=new double[28*28];
		for(int i=0;i<28;i++){
			for(int j=0;j<28;j++){
				trainData1[i*28+j]=trainData[j*28+i];
				System.out.print(trainData1[i*28+j]);}
			System.out.println();
		}
		return bp.test(trainData1);
	}
}

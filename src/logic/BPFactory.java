package ghost.writer.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BPFactory {
	/**
	 * BP神经网络元
	 */
	private static BP bp;
	
	/**
	 * 初始化一个全新的bp神经网络
	 * @param inputSize
	 * @param hiddenSize 
	 * @param outputSize
	 */
	public static void initialization(int inputSize,int hiddenSize,int outputSize) {
		bp=new BP(inputSize, hiddenSize, outputSize);
	}
	
	/**
	 * 从文件数据中读取bp神经网络
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
	 * 将目前的神经网络储存在指定文件
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
	 * 训练BP神经网络
	 * @param trainData
	 * @param target
	 */
	public static void train(double[] trainData, double[] target) {
		bp.train(trainData, target);
	}
	
	/**
	 * 要求bp神经网络返回预测值
	 * @param inData
	 * @return
	 */
	public static double[] test(double[] inData) {
		return bp.test(inData);
	}
}

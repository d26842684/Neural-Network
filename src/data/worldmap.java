/*
 * @(#)HandWritingRecognition.java 1.0 04/04/03
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package myprojects.handwritingrecognition;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.io.*;

class HandWritingRecognition extends JFrame implements ActionListener,ItemListener{
   JButton recognize;
   JButton train;
   JButton clear;
   JButton newSample;
   JLabel result1;
   JTextArea  result2;
   JButton choice1;
   JButton choice2;
   JTextArea status;
   JComboBox num;
   myCanvas can;
   int model;
   FileDialog fdsave;   
   FileDialog fdload;
   BufferedWriter out;
   FileWriter file_writer;   
   BufferedReader in;
   FileReader file_reader;
   JMenuItem save;
   JMenuItem load;
   JMenuItem quit;
   JMenuItem ab;
   JMenuItem edition;
   JTextArea area;
   int first,second;
   int choice;
   int resultLen=10;
   int snum=10;
   
   int [][]sample;
   int [][]input;
   int []row;
   int count;
   int stroke;
   int []x;
   int []y;
   int []dismantle;
   int []col;
   int [][]sample2;
   int d1,d2;
   boolean isSaved;
	
	public HandWritingRecognition() {
	  sample=new int[10][12];
	  sample2=new int[10][8];
	  input=new int[12][8];
	  row=new int[12];
	  count=0;
	  stroke=0;
	  x=new int[500];
	  y=new int[500];
     model=0;
     dismantle=new int[10];
	  col=new int[8];
	  d1=d2=0;
	  isSaved=true;
	  
     fdsave=new FileDialog(this,"保存文件",FileDialog.SAVE);   
     fdload=new FileDialog(this,"打开文件",FileDialog.LOAD);
	  setSize(470,520);
	  setLocation(300,100);
	  setTitle("数字手写识别器");
	  
     JMenuBar Bar=new JMenuBar();
     Bar.setOpaque(true);
     JMenu file=new JMenu("文件");
     JMenu about=new JMenu("关于");
     Bar.add(file);
     Bar.add(about);
     setJMenuBar(Bar);
     save=new JMenuItem("保存样本");
     load=new JMenuItem("读取样本");
     quit=new JMenuItem("退出");
     file.add(save);
     file.add(load);
     file.addSeparator();
     file.add(quit);
     ab=new JMenuItem("说明");
     edition=new JMenuItem("版本");
     about.add(ab);
     about.addSeparator();
     about.add(edition);
     save.addActionListener(this);
     load.addActionListener(this);
     quit.addActionListener(this);
     ab.addActionListener(this);
     edition.addActionListener(this);
	  
	  can=new myCanvas();
	  
	  Container con=getContentPane();
	  con.setLayout(null);
	  
	  JPanel p1=new JPanel();
	  p1.setLayout(null);
	  p1.add(can);
	  can.setBounds(15,15,270,270);
	  p1.setBorder(BorderFactory.createTitledBorder("手写数字输入板"));
	  con.add(p1);
	  p1.setBounds(20,60,300,300);
	  
	  recognize=new JButton("识 别");
	  clear=new JButton("清 除");
	  newSample=new JButton("新建样本");
	  train=new JButton("训 练");
	  choice1=new JButton();
	  choice2=new JButton();
	  con.add(recognize);
	  recognize.setBounds(340,70,80,30);
	  con.add(clear);
	  clear.setBounds(340,120,80,30);
	  con.add(newSample);
	  newSample.setBounds(20,15,90,30);
	  con.add(train);
	  train.setBounds(340,170,80,30);
	  String []s={"0","1","2","3","4","5","6","7","8","9"};
	  num=new JComboBox(s);
     JPanel p4=new JPanel();
     con.add(p4);
     p4.setLayout(null);
     p4.setBounds(340,210,80,50);
     p4.setBorder(BorderFactory.createTitledBorder("训练项"));
     p4.add(num);
     num.setBounds(10,15,55,25);
     
	  JPanel p2=new JPanel();
	  con.add(p2);
	  p2.setLayout(null);
	  p2.setBounds(340,260,80,100);
     p2.setBorder(BorderFactory.createTitledBorder("结果备选"));
	  p2.add(choice1);
	  p2.add(choice2);
	  choice1.setBounds(10,20,60,30);
	  choice2.setBounds(10,60,60,30);
	  
	  result1=new JLabel("识别结果:");
	  result2=new JTextArea();
	  result2.setEditable(false);
	  con.add(result1);
	  con.add(result2);
	  result1.setBounds(150,15,70,30);
	  result2.setBounds(230,15,170,30);
	  
	  JPanel p3=new JPanel();
     con.add(p3);
     p3.setLayout(null);
     p3.setBounds(20,370,400,70);
     p3.setBorder(BorderFactory.createTitledBorder("STATE："));
     area=new JTextArea();
     p3.add(area);
     area.setBounds(10,15,380,45);
     area.setEditable(false);
     
     recognize.setEnabled(false);
     choice1.setEnabled(false);
     choice2.setEnabled(false);
     train.setEnabled(false);
     num.setEnabled(false);
     clear.setEnabled(false);
     area.setText("请新建或载入手写体样本。");

     recognize.addActionListener(this);
     clear.addActionListener(this);
     train.addActionListener(this);
     newSample.addActionListener(this);
     choice1.addActionListener(this);
     choice2.addActionListener(this);
     num.addActionListener(this);
     
     
	  
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			   
			   if(isSaved==false){
			      String s="是否要保存已修改的样本到样本文件?";
			      String t="提示";
               int check;
               JFrame f=new JFrame();
               check=JOptionPane.showConfirmDialog(f,s,t,0);
               if(check==0){
               fdsave.setVisible(true);
               try{
                  if(fdsave.getFile()!=null){
                     File file=new File(fdsave.getDirectory(),fdsave.getFile());
                     file_writer=new FileWriter(file);
                     out=new BufferedWriter(file_writer);
                     for(int i=0;i<10;i++){
                        for(int j=0;j<12;j++){
                           Integer aa=new Integer((int)sample[i][j]);
                           s=aa.toString();
                           out.write(s,0,s.length());
                           out.flush();
                           out.newLine();
                        }
                        for(int j=0;j<8;j++){
                           Integer aa=new Integer((int)sample2[i][j]);
                           s=aa.toString();
                           out.write(s,0,s.length());
                           out.flush();
                           out.newLine();
                        }
                     }
                     out.close();
                     file_writer.close();
                  }
               }catch(FileNotFoundException e1){}
               catch(IOException e2){}
               }
               isSaved=true;
            }
		      dispose();
				System.exit(0);
			}
		});
		
		setVisible(true);
		show();
	}
	
	public void standard(){
	  int minx,miny,maxx,maxy,xlen,ylen;
     minx=miny=1000;
     xlen=ylen=0;
     maxx=maxy=0;
	  for(int i=0;i<count;i++){
	     if(x[i]==-1){
	        continue;
	     }
	     if(x[i]<minx){
	        minx=x[i];
	     }
        if(y[i]<miny){
           miny=y[i];
        }
        if(x[i]>maxx){
           maxx=x[i];
        }
        if(y[i]>maxy){
           maxy=y[i];
        }
     }
     System.out.println(maxx+" "+maxy+" "+minx+" "+miny );
	  xlen=maxx-minx;
	  ylen=maxy-miny;
	  int tempi,tempj;
	  for(int i=0;i<count;i++){
	     if(x[i]==-1){
	        continue;
	     }
	     tempj=(x[i]-minx)*8/xlen;
	     tempi=(y[i]-miny)*12/ylen;
	     if(tempi>11)tempi=11;
	     if(tempj>7)tempj=7;
	     if(tempi<0)tempi=0;
	     if(tempj<0)tempj=0;
	     input[tempi][tempj]=1;
	  }
	  for(int i=0;i<12;i++){
	     int c=0;
	     for(int j=0;j<8;j++){
	        if(input[i][j]==1){
	           row[i]+=j;
	           c++;
	        }
	     }
	     row[i]*=c;
	  }
     for(int i=0;i<8;i++){
        int c=0;
        for(int j=0;j<12;j++){
           if(input[j][i]==1){
              col[i]+=j;
              c++;
           }
        }
        col[i]*=c;
     }
	  
	  for(int i=0;i<12;i++){
	     for(int j=0;j<8;j++){
	        System.out.print(input[i][j]+" ");
	     }
	     System.out.print("   "+row[i]+"\n");
	  }
	  for(int i=0;i<8;i++){
	     System.out.print(col[i]+" ");
	  }
	  System.out.println("");
   }
	         
	
	public void actionPerformed(ActionEvent e){
	  if(e.getSource()==quit){
        
            
            if(isSaved==false){
               String s="是否要保存以修改的样本到样本文件?";
               String t="提示";
               int check;
               JFrame f=new JFrame();
               check=JOptionPane.showConfirmDialog(f,s,t,0);
               if(check==0){
               fdsave.setVisible(true);
               try{
                  if(fdsave.getFile()!=null){
                     File file=new File(fdsave.getDirectory(),fdsave.getFile());
                     file_writer=new FileWriter(file);
                     out=new BufferedWriter(file_writer);
                     for(int i=0;i<10;i++){
                        for(int j=0;j<12;j++){
                           Integer aa=new Integer((int)sample[i][j]);
                           s=aa.toString();
                           out.write(s,0,s.length());
                           out.flush();
                           out.newLine();
                        }
                        for(int j=0;j<8;j++){
                           Integer aa=new Integer((int)sample2[i][j]);
                           s=aa.toString();
                           out.write(s,0,s.length());
                           out.flush();
                           out.newLine();
                        }
                     }
                     out.close();
                     file_writer.close();
                  }
               }catch(FileNotFoundException e1){}
               catch(IOException e2){}
               }
               isSaved=true;
            }
       
            System.exit(0);
         
     }
	  if(e.getSource()==recognize){
	     standard();
	     for(int i=0;i<10;i++){
	        for(int j=0;j<12;j++){
	           dismantle[i]+=Math.abs(sample[i][j]-row[j]);
	        }
           for(int j=0;j<8;j++){
              dismantle[i]+=Math.abs(sample2[i][j]-col[j]);
           }
	        System.out.print(dismantle[i]+" ");
	     }
	     
	     
	     first=second=0;
	     for(int i=0;i<10;i++){
	        if(dismantle[i]<dismantle[first]){
	           first=i;  
	        }
	     }
	     if(first==0)second=1;
	     for(int i=0;i<10;i++){
	        if(i==first)continue;
	        if(dismantle[i]<dismantle[second]){
	           second=i;
	        }
	     }
        for(int j=0;j<12;j++){
           d1+=sample[first][j]-row[j];
        }
        for(int j=0;j<8;j++){
           d1+=sample2[first][j]-col[j];
        }
        for(int j=0;j<12;j++){
           d2+=sample[second][j]-row[j];
        }
        for(int j=0;j<8;j++){
           d2+=sample2[second][j]-col[j];
        }
	     for(int i=0;i<12;i++){
	        row[i]=0;
	     }
        for(int i=0;i<8;i++){
            col[i]=0;
         }
	     choice1.setLabel("I: "+first);
	     choice2.setLabel("II: "+second);
        
        System.out.print(d1+" "+d2);
        d1=d2=0;
	  }
	  if(e.getSource()==choice1){
	     /*for(int i=0;i<12;i++){
	        sample[first][i]=(row[i]-sample[first][i])/5+sample[first][i];
	     }
        for(int i=0;i<8;i++){
           sample2[first][i]=(col[i]-sample2[first][i])/5+sample2[first][i];
        }*/
        resultLen--;
        if(resultLen==0){
           resultLen=10;
           result2.setText("");
        }
        isSaved=false;
	     Integer i=new Integer(first);
	     result2.append(i.toString());
	  }
	  if(e.getSource()==choice2){
        for(int i=0;i<12;i++){
           sample[second][i]=(row[i]-sample[second][i])/5+sample[second][i];
        }
        for(int i=0;i<8;i++){
           sample2[second][i]=(col[i]-sample2[second][i])/5+sample2[second][i];
        }
        isSaved=false;
        resultLen--;
        if(resultLen==0){
           resultLen=10;
           result2.setText("");
        }
        Integer i=new Integer(second);
        result2.append(i.toString());
     }
	  if(e.getSource()==clear){
         input=new int[12][8];
         row=new int[12];
         count=0;
         stroke=0;
         x=new int[500];
         y=new int[500];
         dismantle=new int[10];
         can.repaint();
      }
      if(e.getSource()==train){
         standard();
         
         for(int i=0;i<12;i++){
            sample[model][i]=row[i];
         }
         for(int i=0;i<8;i++){
            sample2[model][i]=col[i];
         }
         if(model==9){
            recognize.setEnabled(true);
            choice1.setEnabled(true);
            choice2.setEnabled(true);
         }
         for(int i=0;i<12;i++){
           row[i]=0;
         }
         for(int i=0;i<8;i++){
            col[i]=0;
         }
        
         //for(int i=0;i<12;i++){
         //   System.out.print(sample[model][i]+" ");
         //}
      }
      if(e.getSource()==newSample){
         sample=new int[10][12];
         area.setText("请依次输入0-9样本。");
         recognize.setEnabled(false);
         choice1.setEnabled(false);
         choice2.setEnabled(false);
         train.setEnabled(true);
         num.setEnabled(true);
         clear.setEnabled(true);
         isSaved=false;
      }
      if(e.getSource()==save){
         fdsave.setVisible(true);
         String s;
         try{
            if(fdsave.getFile()!=null){
               File file=new File(fdsave.getDirectory(),fdsave.getFile());
               file_writer=new FileWriter(file);
               out=new BufferedWriter(file_writer);
               for(int i=0;i<10;i++){
                  for(int j=0;j<12;j++){
                     Integer aa=new Integer((int)sample[i][j]);
                     s=aa.toString();
                     out.write(s,0,s.length());
                     out.flush();
                     out.newLine();
                  }
                  for(int j=0;j<8;j++){
                     Integer aa=new Integer((int)sample2[i][j]);
                     s=aa.toString();
                     out.write(s,0,s.length());
                     out.flush();
                     out.newLine();
                  }
               }
               out.close();
               file_writer.close();
             }
          }catch(FileNotFoundException e1){}
           catch(IOException e2){}
           isSaved=true;
      }
      if(e.getSource()==load){
         fdload.setVisible(true);
         String s=new String();
         try{
            if(fdload.getFile()!=null){
               File file=new File(fdload.getDirectory(),fdload.getFile());
               file_reader=new FileReader(file);
               in=new BufferedReader(file_reader);
               Integer aa=new Integer(0);
               for(int i=0;i<10;i++){
                  for(int j=0;j<12;j++){
                     s=in.readLine();
                     sample[i][j]=aa.parseInt(s);
                  }
                  for(int j=0;j<8;j++){
                     s=in.readLine();
                     sample2[i][j]=aa.parseInt(s);
                  }
               }
               in.close();
               file_reader.close();
               }
         }catch(FileNotFoundException e1){fdload.setVisible(false);fdload.dispose();}
          catch(IOException e2){}
         recognize.setEnabled(true);
         clear.setEnabled(true);
         choice1.setEnabled(true);
         choice2.setEnabled(true);
         train.setEnabled(true);
         num.setEnabled(true);
         area.setText("请开始手写输入识别");
         
      }
      if(e.getSource()==edition){
         About abt=new About();
      }            
      if(e.getSource()==num){
         String s=(String)num.getSelectedItem();
         if(s.equals("0")){
            model=0;
         }
         if(s.equals("1")){
            model=1;
         }
         if(s.equals("2")){
            model=2;
         }
         if(s.equals("3")){
            model=3;
         }
         if(s.equals("4")){
            model=4;
         }
         if(s.equals("5")){
            model=5;
         }
         if(s.equals("6")){
            model=6;
         }
         if(s.equals("7")){
            model=7;
         }
         if(s.equals("8")){
            model=8;
         }
         if(s.equals("9")){
            model=9;
         }
         
      }
   }
   
   public void itemStateChanged(ItemEvent e){
   }
   

	public static void main(String args[]) {
		HandWritingRecognition frame=new HandWritingRecognition();
	}
	

	     
	
	class myCanvas extends Canvas implements MouseListener,MouseMotionListener{
	  
	  myCanvas(){
	     addMouseListener(this);
	     addMouseMotionListener(this);
	     setBackground(Color.white);
	  }
	  
	  public void paint(Graphics g){
	     g.setColor(Color.red);
	     for(int i=0;i<count-1;i++){
	        if(x[i+1]==-1){
	           i++;
	           continue;
	        }else{
	           g.drawLine(x[i],y[i],x[i+1],y[i+1]);
	        }
	     }
	  }
	  
	  public void mouseClicked(MouseEvent me){}
	  public void mousePressed(MouseEvent me){
	     x[count]=me.getX();
	     y[count]=me.getY();
	     count++;
	  }
	  public void mouseReleased(MouseEvent me){
	     x[count]=-1;
	     y[count]=-1;
	     stroke++;
	     count++;
	  }
	  public void mouseEntered(MouseEvent me){}
	  public void mouseExited(MouseEvent me){}
	  
	  public void mouseDragged(MouseEvent me){
	     x[count]=me.getX();
	     y[count]=me.getY();
	     //System.out.println(x[count]+" "+y[count]);
	     count++;
	     repaint();
	  }
	  public void mouseMoved(MouseEvent me){
	     int tempx=me.getX();
	     int tempy=me.getY();
	     //area.setText(tempx+" "+tempy);
	  }
   }
}
 class About extends JFrame{
     About(){
        Container con=getContentPane();
        con.setLayout(null);
        JLabel message=new JLabel();
        JLabel message1=new JLabel();
        setSize(250,150);
        setLocation(350,200);
        setTitle("版本");
        con.add(message);
        message.setText("版本：1.0");
        message.setBounds(20,5,150,30);
        con.add(message1);
        message1.setText("作者：09001211 钱磊");
        message1.setBounds(20,40,150,50);
        
        addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            dispose();
            
         }
         
      });
         setVisible(true);
     }
   }
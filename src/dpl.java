
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import packet.ReadFileLineByLine;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kiblitskiy
 */
public class dpl {
    public static void main(String[]args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ReadFileLineByLine rf = new ReadFileLineByLine();
        ArrayList<String> ar = new ArrayList<>();
        ArrayList<String> dpArray = new ArrayList<>();
        System.out.print("Введите адрес и имя файла с указанием расширения: \n");
        String inFile = reader.readLine();
        ar = rf.read(inFile);
        //System.out.println(ar);
        int n = ar.size(); int m = 0;
        for(int i = 0; i < n;i++){
            dpArray.add(ar.get(i));
            if(ar.get(i).lastIndexOf("Верхний норматив") > 0){
                m++;
                String replace = ar.get(i).replace("Верхний норматив", "Выход за верхний норматив");
                replace = replace.replace("ACXXXX1", "ACXXXX0");               
                dpArray.add(i + m, replace);
                
               
            }
           
        }
        System.out.print("Введите адрес и имя файла итогового с указанием расширения: \n");
        String outFile = reader.readLine();
        FileWriter writer = new FileWriter(outFile, false);
        
        for(int i=0;i<dpArray.size();i++){
            
            if(dpArray.get(i).lastIndexOf("Верхний норматив") > 0){
             String[] middleAr;
             middleAr = dpArray.get(i).split("\t");
             
             Double newPr = Double.valueOf(middleAr[6]) - 0.2;
             String replace = dpArray.get(i).replace(middleAr[5] + "\t" + middleAr[6], middleAr[5] + "\t" + newPr.toString());
             
             replace = replace.replace(middleAr[21] + "\t" + middleAr[22], middleAr[21] + "\t" + newPr.toString());
             dpArray.set(i, replace);
           }else if(dpArray.get(i).lastIndexOf("Выход за верхний норматив") > 0){  
             String[] middleAr;
             middleAr = dpArray.get(i).split("\t");
             Double newPr = Double.valueOf(middleAr[6]) - 0.2;
             Double newPr1 = newPr + 0.2;
             String replace1 = dpArray.get(i).replace(middleAr[5] + "\t" + middleAr[6], newPr.toString() + "\t" + middleAr[6]);
             replace1 = replace1.replace(middleAr[21] + "\t" + middleAr[22], newPr.toString() + "\t" + middleAr[22]);
             replace1 = replace1.replace(middleAr[5] + "\t" + middleAr[6], middleAr[5] + "\t" + newPr1.toString());
             replace1 = replace1.replace(middleAr[21] + "\t" + middleAr[22], middleAr[21] + "\t" + newPr1.toString());
             dpArray.set(i, replace1);
           }
           //Writer append = writer.append(dpArray.get(i) + "\n");
        }
        Integer k = 0;
        for(int i = 0;i<dpArray.size();i++){
         if(dpArray.get(i).equals("")){
             Writer append = writer.append(dpArray.get(i) + "\n");
             continue;
         }
         String[] middleAr;
         if(dpArray.get(i).contains("\t")){         
             middleAr = dpArray.get(i).split("\t");
         
         if(middleAr.length == 0){
             Writer append = writer.append(dpArray.get(i) + "\n");
             continue;
         }
         if(middleAr[3].equals("")){
            k = 0; 
            //continue;
         }else if(middleAr[3].matches("[-+]?\\d+")){
             k++;
             String replace1 = dpArray.get(i).replace(middleAr[2] + "\t" + middleAr[3], middleAr[2] + "\t" + k.toString());
             dpArray.set(i, replace1);
         }else{
             Writer append = writer.append(dpArray.get(i) + "\n");
             continue;
         }
         }
         Writer append = writer.append(dpArray.get(i) + "\n");
        }
        
        writer.flush();
        System.out.print("DONE");
    }
    
}

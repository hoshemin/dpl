/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kiblitskiy
 */
public class ReadFileLineByLine {
    public ArrayList<String> read(String filePath) throws FileNotFoundException, IOException{
    ArrayList<String> ar = new ArrayList<>();

    
            File file = new File(filePath);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (null != line) {
                //System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                if(line == null) continue;                
                boolean add = ar.add(line);
            }       
        return ar;
        }
    
    }

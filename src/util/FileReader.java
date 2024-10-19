package util;

import java.io.BufferedReader;

public class FileReader {

    public static void readFile(String fileName){

        try{
            java.io.FileReader fileReader=new java.io.FileReader(fileName);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            System.out.println(bufferedReader.readLine());
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}

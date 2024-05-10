package mySample.stock1.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOService {
    public static void main(String[] args) {
        IOService ioService = new IOService();
        ioService.saveByText("test", "asdf");
        System.out.println(ioService.getByText("test"));
    }

    public void saveByText(String fileName, String savingPassword) {
        try(
            PrintWriter pw = new PrintWriter(
                new FileWriter(
                    String.format("../%s.txt", fileName), false
                ), true
            )
        ){
            pw.print(savingPassword);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getByText(String fileName) {
        String pwd = "";
        try(BufferedReader br = new BufferedReader(new FileReader(String.format("../%s.txt", fileName)))){
            pwd = br.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }
        return pwd;
    }
}

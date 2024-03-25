package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BillFileWriter {
    public static void main(String[] args) {
        Billboard b1 = new Billboard(1, "Despacito", 1,
        "https://www.billboard.com/images/pref_images/q61808osztw.jpg", "luis fonsi");
        Billboard b2 = new Billboard(2, "That's What I Like", 2,
        "https://www.billboard.com/images/pref_images/q59725qvpol.jpg", "bruno mars");
        Billboard b3 = new Billboard(3, "Despacito", 3,
        "https://www.billboard.com/images/pref_images/q64532pl64x.jpg", "dj khaled");
        ArrayList<Billboard> bblist = new ArrayList<>();
        bblist.add(b1);
        bblist.add(b2);
        bblist.add(b3);
        File f = new File("billboard");
        if(!f.exists()) {f.mkdirs();}
        try(PrintWriter pw = new PrintWriter(new FileWriter("billboard/bill.txt", false), true)){
            for (Billboard bb : bblist) {
                pw.println(bb);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

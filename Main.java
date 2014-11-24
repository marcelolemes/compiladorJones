package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main extends JFrame{

    public static void main(String[] args) throws IOException {
        FilenameFilter filtro = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File file = new File(new File("").getAbsolutePath());
        if (file.isDirectory()){
            File[] files = file.listFiles(filtro);
            for(File arquivo: files){
                System.out.println(arquivo.getAbsolutePath());
                ArrayList<String> linhas = new ArrayList();
                ArrayList<String> variaveis = new ArrayList();
                ArrayList tokens = new ArrayList();
                Scanner in = new Scanner(new FileReader(arquivo));
                while(in.hasNextLine()){
                    linhas.add(in.nextLine());
                }
                for (String s:linhas){
                    Collections.addAll(tokens, s.split(" "));
                }

                for (int x=0; x<tokens.size();x++){
                    int y=x+1;

                    if (tokens.get(x).toString().equals("int")||tokens.get(x).toString().equals("double")){
                        if (tokens.get(y).toString().endsWith("{")){

                        }
                        else {
                            variaveis.add(tokens.get(x).toString() + " " + tokens.get(y).toString());
                        }
                    }
                }

                for(Object o:variaveis){
                    System.out.println(o.toString());
                }

            }
        }

    }


}

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

        File file = new File(System.getProperty("user.dir"));
        if (file.isDirectory()){
            File[] files = file.listFiles(filtro);
            for(File arquivo: files){
                System.out.println(arquivo.getAbsolutePath());
                ArrayList<String> linhas = new ArrayList();
                ArrayList<String> variaveis = new ArrayList();
                ArrayList<String> variaveisLocais = new ArrayList();
                StringBuffer comentario= new StringBuffer();
                ArrayList tokens = new ArrayList();
                Scanner in = new Scanner(new FileReader(arquivo));
                while(in.hasNextLine()){
                    linhas.add(in.nextLine());
                }
                for (String s:linhas){
                    Collections.addAll(tokens, s.split(" "));
                }

                for (int z=0; z<tokens.size();z++){
                    if (tokens.get(z).toString().startsWith("/*")){

                        for (int xz=z;xz<tokens.size();xz++){

                            if (tokens.get(xz).toString().endsWith("*/")){
                                comentario.append(" " + tokens.get(xz).toString());
                                xz=tokens.size();

                            }
                            else {
                                comentario.append(" " + tokens.get(xz).toString());
                            }
                        }

                    }

                }

                for (int x=0; x<tokens.size();x++){
                    int y=x+1;

                    if (tokens.get(x).toString().equals("int")||tokens.get(x).toString().equals("double")){
                        if (tokens.get(y).toString().endsWith("{")){
                            for (int cont=y;cont<tokens.size();cont++ ){
                                int cont2=cont+1;
                                if (tokens.get(cont).toString().equals("int")||tokens.get(cont).toString().equals("double")){
                                    variaveisLocais.add(tokens.get(cont).toString() + " " + tokens.get(cont2).toString());
                                }
                                else if (tokens.get(cont).toString().endsWith("}")){
                                    cont= tokens.size();
                                }
                            }
                        }
                        else {
                            variaveis.add(tokens.get(x).toString() + " " + tokens.get(y).toString());
                        }
                    }

                }
                System.out.println("Variáveis globais");
                for(Object o:variaveis){
                    System.out.println(o.toString());
                }

                System.out.println("Variáveis locais");
                               for(Object p:variaveisLocais){
                                   System.out.println(p.toString());
                               }

                System.out.println("Comentários");
                System.out.println(comentario);


            }
        }

    }


}

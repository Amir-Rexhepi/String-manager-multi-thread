package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do{
                String scelta = in.readLine();
                String stringaRicevuta = in.readLine();

                if(stringaRicevuta.equals("!")){
                    System.out.println("il client vuole chiudere");
                    s.close();
                    break;
                }
                System.out.println("Stringa ricevuta sul thread" + Thread.currentThread().getName() + " : " + stringaRicevuta);
                switch (scelta) {
                    case "1":
                        String maisculo = stringaRicevuta.toUpperCase();
                        out.writeBytes(maisculo + "\n");
                        break;
                
                    case "2":
                         String minuscolo = stringaRicevuta.toLowerCase();
                        out.writeBytes(minuscolo + "\n");
                        break;
                    
                    case "3":
                    StringBuilder strBuilder = new StringBuilder(stringaRicevuta);
                    String inverso = strBuilder.reverse().toString();
                    out.writeBytes(inverso + "\n");
                    break;

                    case "4":
                     String conta = stringaRicevuta;
                     out.writeBytes(conta.length() + "\n");
                     break;
                }
                

            }while(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

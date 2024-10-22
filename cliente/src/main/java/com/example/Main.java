package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    /**
     * @param args
     * @throws UnknownHostException
     * @throws IOException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Cliente avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso!");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        do{
            System.out.println("inserisci la tua parola: ");
            Scanner scan = new Scanner(System.in);
            Scanner scelta = new Scanner(System.in);
            System.out.println("Digita: 1 per Trasformare stringa in maiuscolo" + "\n" + 
            "Digita: 2 per  Trasformare stringa in minuscolo" + "\n" + 
            "Digita: 3 per Ribaltare i caratteri della stringa" + "\n" + 
            "Digita  4 per Contare il numero di caratteri");
            String digita = scelta.nextLine();
            System.out.println("digita la parola ");
            String stringaDigitata = scan.nextLine();
            if(stringaDigitata.equals("exit")){
                System.out.println("il client sta termindando");
                out.writeBytes("!" + "\n");
                break;
            }
            out.writeBytes(digita + "\n");
            out.writeBytes(stringaDigitata + "\n");
            String stringaRicevuta = in.readLine();
            System.out.println("il server ha risposto con " + stringaRicevuta);
        }while(true);
    }
}
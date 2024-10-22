package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Cliente avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso!");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        do{
            System.out.println("inserisci la tua parola: ");
            Scanner scan = new Scanner(System.in);
            String stringaDigitata = scan.nextLine();
            if(stringaDigitata.equals("exit")){
                System.out.println("il client sta termindando");
                out.writeBytes("!" + "\n");
                break;
            }
            out.writeBytes(stringaDigitata + "\n");
            String stringaRicevuta = in.readLine();
            System.out.println("il server ha risposto con " + stringaRicevuta);
        }while(true);
    }
}
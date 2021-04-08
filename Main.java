package com.lrn;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.codec.binary.Hex;

import org.apache.commons.codec.binary.Hex.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    public static String encode(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
        }
        catch (Exception ex){
            return "";
        }
    }

    public static void main(String[] args) {
        if(args.length%2==0){
            System.out.println("Wrong input data");
            return;
        }
        for(int i=0;i<args.length;i++){
           for(int j=i+1;j< args.length;j++){
               if(args[i].equals(args[j])){
                   System.out.println("Similar input data");
                   return;
               }
           }
        }
        byte[] key = new byte[16];
	    SecureRandom secureRandom=new SecureRandom();
        secureRandom.nextBytes(key);
        Random random = new Random();
        int compTurn= random.nextInt(args.length) ;
        String s_key=new String(Hex.encodeHex(key));
        String hmac=encode(s_key,args[compTurn]);
        System.out.println("HMAC: "+hmac+"\n");
        for(int i=0;i<args.length;i++){
            System.out.println(i+1+") "+args[i]);
        }
        System.out.println("0) Exit");
        Scanner in = new Scanner(System.in);
        boolean a=true;
        int userTurn=0;
        while(a){
            try{
                System.out.print("Enter your turn ");
                userTurn = in.nextInt()-1;
                if(userTurn>=args.length || userTurn<-1){
                    System.out.println("Wrong turn");
                    continue;
                }
                a=false;
            }
            catch (Exception ex){
                System.out.println("Wrong turn");
                in.nextLine();
            }
        }
        if(userTurn==-1){
            return;
        }

        System.out.print("Your turn is "+args[userTurn]+"\n");
        if(userTurn==compTurn){
            System.out.println("Draw");
        }
       else if(Math.abs(userTurn-compTurn)<= args.length/2){
           if(userTurn>compTurn){
               System.out.println("You win!");
           }
           else {
               System.out.println("You lose!");
           }
        }
       else {
            if (userTurn > compTurn) {
                System.out.println("You lose!");
            } else {
                System.out.println("You win!");
            }
        }
        System.out.println("Computer turn: "+args[compTurn]);
        System.out.println("Key: "+s_key);
        System.out.println("Method of HMAC calculating Sha256");
        return;
    }
}

package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



class ThreadFunc extends Thread{
    private Thread t;
    private String threadName;

    ThreadFunc(String name){
        threadName = name;
    }
    public void run(){

    }

    public void start(){

    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        //Number array
        ArrayList<Integer> number = new ArrayList<Integer>();

        //Read input File using scanner
        Scanner scanner = new Scanner(new BufferedReader((new FileReader("C:/dev/OS/Input.txt"))));
        while (scanner.hasNextLine()){
            number.add(scanner.nextInt());
        }
        scanner.close();

        System.out.print(number);

    }

    public static void sort(int[]arr, int lo, int hi){

    }
}


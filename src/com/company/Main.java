package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class QuickSort extends Thread{

    private ArrayList<Integer> arr;
    private int low;
    private int high;
    private char side;

    //constructor
    public QuickSort(ArrayList<Integer> arr, int low, int high, char side){
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.side = side;
    }

    public void run(){
        quickSort(arr, low, high);
//        System.out.println(arr);
    }

    public static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (high > low) {
            int pivot = partition(arr, low, high);       //partition sort between low and high
            QuickSort left = new QuickSort(arr,low, pivot-1, 'L');  //QuickSort thread for the left array
            left.run();
            QuickSort right = new QuickSort(arr, pivot + 1, high, 'R'); //QuickSort thread for the right array
            right.run();
        }
    }

    public static int partition(ArrayList<Integer> arr, int low, int high) {
        Random rand = new Random();
        int pivot = arr.get(rand.nextInt(arr.size()));      //choose pivot position randomly
        System.out.print("Pivot: " + pivot+ " ");
        int lo = low;                   //assign low to temp

        for (int i = lo; i < high; i++) {
            if (arr.get(i) <= pivot) {    //compare array with pivot and if smaller than pivot exchange
                swap(arr, lo, i);
                System.out.println(arr);
                lo++;                   //move the boundary between items compared with pivot
            }
        }
        swap(arr, lo, high);
        return lo;
    }
    //Swap value position in an array
    public static void swap(ArrayList<Integer> arr, int a, int b){
        int temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }

    public void print(){
        System.out.println(arr);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> number = parse();
        System.out.println(number);
        QuickSort sort = new QuickSort(number, 0, number.size()-1, 'N');
        sort.run();
        sort.print();
        printOutput(number);
    }

    //Parse.txt to array
    public static ArrayList<Integer> parse() throws IOException {
        ArrayList<Integer> number = new ArrayList<Integer>();
        Scanner scanner = new Scanner(new BufferedReader((new FileReader("input.txt"))));
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            number.add(scanner.nextInt());
        }
        scanner.close();
        return number;
    }

    public static void printOutput(ArrayList<Integer> number) throws IOException{
       PrintWriter writer = new PrintWriter("output.txt");
       for(int i = 0; i< number.size(); i++){
           writer.println(number.get(i));
        }
       writer.close();
    }

}


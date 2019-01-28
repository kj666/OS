package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class QuickSort extends Thread{

    private ArrayList<Integer> arr;
    private int low;
    private int high;

    //constructor
    public QuickSort(ArrayList<Integer> arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }


    public void run(){
       quickSort(arr, low, high);
        System.out.println(arr);

    }

    public static void quickSort(ArrayList<Integer> arr, int low, int high){
        if (high > low) {
            int pivot = partition(arr, low, high);       //partition sort between low and high
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
            QuickSort left = new QuickSort(arr,low, pivot-1);  //QuickSort thread for the left array
            QuickSort right = new QuickSort(arr, pivot + 1, high); //QuickSort thread for the right array
            left.start();
            right.start();

         try {
             left.join();
             right.join();
         }
         catch (Exception e) {
             System.out.println("An EXCEPTION OCCURED !!!");
         }
        }

    }

    public static int partition(ArrayList<Integer> arr, int low, int high) {

        int pivot=arr.get(high);
        System.out.print("Pivot: " + pivot+ " ");
        int lo = low;                   //assign low to temp

        for ( int i = lo; i < high; i++) {
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
        System.out.println("The "+Thread.currentThread().getName()+" thread just finished filling an integer array");
        QuickSort sort = new QuickSort(number, 0, number.size()-1);
        sort.quickSort(number, 0, number.size()-1);
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


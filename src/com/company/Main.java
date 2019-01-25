package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        System.out.println("QuickSort Thread Running");
        quickSort(arr, low, high);
    }

    public static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (high > low) {
            int pivot = partition(arr, low, high);       //partition sort between low and high
            QuickSort left = new QuickSort(arr,low, pivot-1);  //QuickSort thread for the left array
            QuickSort right = new QuickSort(arr, pivot +1, high); //QuickSort thread for the right array
            left.run();
            right.run();
        }
    }

    public static int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);      //make the last value of array as pivot
        int lo = low;                   //assign low to temp

        for (int i = lo; i < high; i++) {
            if (arr.get(i) <= pivot) {    //compare array with pivot and if smaller than pivot exchange
                swap(arr, lo, i);
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
        QuickSort sort = new QuickSort(number, 0, number.size()-1);
        sort.run();
        sort.print();
    }

    //Parse.txt to array
    public static ArrayList<Integer> parse() throws IOException {
        ArrayList<Integer> number = new ArrayList<Integer>();
        Scanner scanner = new Scanner(new BufferedReader((new FileReader("C:/dev/OS/Input.txt"))));
        while (scanner.hasNextLine()) {
            number.add(scanner.nextInt());
        }
        scanner.close();
        return number;
    }

}


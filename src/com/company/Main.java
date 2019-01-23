package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



//class QuickSort extends Thread{
//
//    private ArrayList<Integer> arr;
//    private int start, end;
//    public static int numThreads = Runtime.getRuntime().availableProcessors();
//    public static int count = 0;
//
//
//    private Thread t;
//    private String threadName;
//
//    public QuickSort(ArrayList<Integer> arr, int start, int end){
//        this.arr = arr;
//        this.start = start;
//        this.end = end;
//    }
//
//    public void run(){
//    }
//
//    public static void quicksort(ArrayList<Integer> arr, int start, int end){
//        if(end > start){
//            int i = partition(arr, start, end);
//
//        }
//    }
//
//    public static int partition(ArrayList<Integer> arr, int left, int right){
//        return 1;
//    }
//    //Swap value position in an array
//    public static void swap(ArrayList<Integer> arr, int a, int b){
//        int temp = arr.get(a);
//        arr.set(a, arr.get(b));
//        arr.set(b, temp);
//    }
//
//    public static int choosePivot(ArrayList<Integer> arr, int left, int right){
//        int middle = (left + right)/2;
//        if(arr.get(middle) < arr.get(left))
//            swap(arr,left,middle);
//        if(arr.get(right) < arr.get(left))
//            swap(arr, left, right);
//        if(arr.get(right) < arr.get(middle))
//            swap(arr, middle, right);
//
//        swap(arr, middle, right -1);
//        return arr.get(right-1);
//    }
//
//
//
//
//    public void start(){
//    }
//}


public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> number = parse();
        System.out.println(number);
        quickSort(number, 0, number.size()-1);
        System.out.println(number);
    }

    //Parse.txt to array
    public static ArrayList<Integer>  parse() throws IOException{
        ArrayList<Integer> number = new ArrayList<Integer>();
        Scanner scanner = new Scanner(new BufferedReader((new FileReader("C:/dev/OS/Input.txt"))));
        while (scanner.hasNextLine()){
            number.add(scanner.nextInt());
        }
        scanner.close();
        return number;
    }


    public static void quickSort(ArrayList<Integer> arr,int low, int high){
        if(high > low){
           int pivot = partition(arr, low, high);       //partition sort between low and high
           quickSort(arr, low, pivot -1);         //use quickSort recursively from low under pivot
           quickSort(arr, pivot +1, high);        //use quickSort from pivot to high
        }
    }

    public static int partition(ArrayList<Integer> arr, int low, int high){
        int pivot = arr.get(high);      //make the last value of array as pivot
        int lo = low;                   //assign low to temp

        for(int i = lo; i < high; i++){
            if(arr.get(i) <= pivot){    //compare array with pivot and if smaller than pivot exchange
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
}


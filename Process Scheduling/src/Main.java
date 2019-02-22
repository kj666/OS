import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//https://www.youtube.com/watch?v=3N2t9_6Co3U

//Circular Queue implementation
class CircularQ<T>{
    private ArrayList<T> Q = new ArrayList<>();
    int size, front, rear;

    public CircularQ(int nn) {
        front = rear =0;
    }

    public void add(T obj){

    }
}

//Process Object
class Process implements Runnable{
    private String processID;
    private String userID;
    private int readyTime;
    private int serviceTime;
    private int remainingTime;
    private int finishedTime;
<<<<<<< HEAD
    private int allowedBurstTime = 0;

=======
    private int timeToRun;
//kfkfkf
>>>>>>> 84fc90f195f98c58dfcbb86277ac742a731ef3b4
    public Process() {
    }

    public Process(String processID, String userID, int readyTime, int serviceTime) {
        this.processID = processID;
        this.userID = userID;
        this.readyTime = readyTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;
        this.allowedBurstTime = serviceTime;
    }

    public void run() {
        for (int i = 0; i < allowedBurstTime; i++){

        }

    }

    public int getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(int finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes = new ArrayList<>();
    private int q;
    private int t = 0;

    public void setProcessList(ArrayList<Process> processes, int q){
        this.processes = processes;
        this.q = q;
    }

    public void run() {
        System.out.println("Scheduler Started");
//        for(int i = 1; i <= 10; i++){
//            System.out.println("Time "+i);
//            try{
//                Thread.sleep(1000);
//                continue;
//            }catch (InterruptedException ex){
//                System.out.println("Resumed");
//            }
//        }

        if(Thread.interrupted()){
            System.out.println("Scheduler Stopped");
        }
//        try {
//            System.out.println("start- sleep");
//            Thread.sleep(6000);
//            System.out.println("Scheduler paused");
//        }catch (InterruptedException ex){
//            System.out.println("Scheduler Resumed");
//        }
    }


    public void suspend(){
        System.out.println("Scheduler Suspended");
    }



    public void addToReadyQueue(Process process){
        readyQueue.add(process);
    }

    public void getTotalReadyUser(String userID){

    }

    public void getTotalReadyProcessPerUser(String UserID){

    }

    public void getProcessAllowedBurst(){

    }

    public void isAllProcessTerminated(){}

}

public class Main {

    //Quantum Value
    private static int q = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        q = scanner.nextInt();
        System.out.println(q);

        ArrayList<Process> processes = inputParser(scanner);
        ArrayList<Thread> threads = new ArrayList<>();


        int t = 0;
        while(true){
            t++;
            System.out.println(t);
            if(t == 6)
                break;
        }

        for(int i = 0; i < processes.size(); i++){
            Thread temp = new Thread(processes.get(i));
            threads.add(temp);
        }
    }

    public static ArrayList<Process> inputParser(Scanner scanner){
        ArrayList<Process> processes = new ArrayList<>();
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String info = scanner.nextLine();
            String arr[] = info.split(" ", 2);

            String userID = arr[0];
            int numberProcess = Integer.valueOf(arr[1]);
            for(int i = 0; i< numberProcess; i++){
                String l[] = scanner.nextLine().split(" ",2);
                Process process = new Process(i+"", userID, Integer.valueOf(l[0]), Integer.valueOf(l[1]));
                processes.add(process);
            }
        }
        //Print process
        for (Process p: processes){
            System.out.println(p.getUserID()+"   "+p.getProcessID()+ "    "+p.getReadyTime()+" "+p.getServiceTime());
        }

        System.out.println("Parsed input.txt successfully!");
        return processes;
    }

    public static void outputParser(){

    }

    public static void processHandler(){

    }
}

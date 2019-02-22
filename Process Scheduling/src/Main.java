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
class Process extends Thread{
    private String processID;
    private String userID;
    private int readyTime;
    private int serviceTime;
    private int remainingTime;
    private int finishedTime;

    public Process() {
    }

    public Process(String processID, String userID, int readyTime, int serviceTime) {
        this.processID = processID;
        this.userID = userID;
        this.readyTime = readyTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;
    }

    @Override
    public void run() {
        super.run();
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

class Scheduler extends Thread{

    private Queue<Process> readyQueue = new LinkedList<>();
    private ArrayList<Process> processes = new ArrayList<>();
    private int q;
    private int t = 0;

}

public class Main {

    //Quantum Value
    private static int q = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        q = scanner.nextInt();
        System.out.println(q);

        ArrayList<Process> processes = inputParser(scanner);

        for (int i = 0; i < processes.size(); i++){
            System.out.println(processes.get(i).getUserID()+"   "+processes.get(i).getProcessID()+ "    "+processes.get(i).getReadyTime()+" "+processes.get(i).getServiceTime());
        }

        int t = 0;
        while(true){
            t++;
            System.out.println(t);
            if(t == 6)
                break;
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
        return processes;
    }


    public static void outputParser(){

    }

    public static void processHandler(){

    }
}

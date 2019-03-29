import sun.plugin.perf.PluginRollup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Scheduler {

    private int  threadCount;
    private int clk;
    private int time;
    private int processToRun;

    private Deque<Process> runningQ;
    private Deque<Process> waitingQ;

    Semaphore semaphore;

    ArrayList<Process> processes = new ArrayList<>();

    public Scheduler() {
        clk = 1;
        time = 1;
        threadCount = 0;
    }

    public void startScheduler() throws FileNotFoundException{
        parseProcessFile("processes.txt");
        initFlag();
        Thread threads[] = new Thread[threadCount];
        System.out.println("clk:" +clk);

        for(int i = 0; i < threadCount; i++){
            Thread thread = new Thread(processes.get(i));
        }
    }

    //intialize mutex
    void initFlag(){
        semaphore= new Semaphore(1);
        processToRun = 0;
    }

    //give cpu a specific thread
    void setThreadFlag(){

    }

    //simulate process
    void runProcess(){

    }

    //thread scheduler
    void start_rr(){

    }
    /**
     * Print Queue
     * @param queue
     */
    public void printQ(Deque<Process> queue){
        for(Process p: queue){
            System.out.printf("PID: "+ p.getPID()+" arrival: "+p.getArrivalTime()+" remaining: "+ p.getRemainingTime());
        }
    }

    /**
     * Check for process arrival time
     */
    public void checkArrivalTime(){
        for(int i = 0; i < waitingQ.size(); i++){
            if(waitingQ.getFirst().getArrivalTime() == time){
                runningQ.push(waitingQ.getFirst());
                System.out.println(waitingQ.getFirst().getPID()+" starting");
                waitingQ.removeFirst();
            }
            else
                break;
        }
    }

    public void setThreadFlag(int flag){
        processToRun = flag;

    }


    /**
     * Parse process input file into Process
     * @param fileName
     * @throws FileNotFoundException
     */
    void parseProcessFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

        threadCount = scanner.nextInt();
        System.out.println(threadCount);
        int PID = 0;
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String p[] = scanner.nextLine().split(" ",2);
            int at = Integer.parseInt(p[0]);
            int bt = Integer.parseInt(p[1]);
            processes.add(new Process(at, bt , PID));
//            waitingQ.addLast(new Process(at, bt , PID));
            PID ++;
        }
        for(Process p: processes){
            p.print();
        }
        scanner.close();

    }

    void parseCommandFile(String fileName)throws FileNotFoundException{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

    }

}

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
    private int t;
    int numberProcess;
    private int processToRun[];

    private Deque<Process> runningQ;
    private Deque<Process> waitingQ;

    Semaphore semaphore;

    ArrayList<Process> processes = new ArrayList<>();

    public Scheduler() {
        clk = 1;
        t = 1;
        threadCount = 0;
    }

    public void startScheduler() throws FileNotFoundException{
        parseProcessFile("processes.txt");
    }

    public void printQ(Deque<Process> queue){
        for(Process p: queue){
            System.out.printf("PID: "+ p.getPID()+" arrival: "+p.getArrivalTime()+" remaining: "+ p.getRemainingTime());
        }
    }

    /**
     * Parse process input file into Process
     * @param fileName
     * @throws FileNotFoundException
     */
    void parseProcessFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

        numberProcess = scanner.nextInt();
        System.out.println(numberProcess);
        int PID = 0;
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String p[] = scanner.nextLine().split(" ",2);
            int at = Integer.parseInt(p[0]);
            int bt = Integer.parseInt(p[1]);
            processes.add(new Process(at, bt , PID));
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

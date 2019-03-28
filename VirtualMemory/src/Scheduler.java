import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Scheduler {
    private int numProcess;
    private int memSize;
    private int commandCounter;
    private Clock clk;
    ArrayList<Process> processes = new ArrayList<>();

    public Scheduler() {
        clk = new Clock();

    }

    public void startScheduler() throws FileNotFoundException{
        parseProcessFile();
        clk.startClk();
    }

    void parseProcessFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("processes.txt")));

        int numberProcess = scanner.nextInt();
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

    }

    void parseCommandFile(){

    }

    void parseMemConfigFile(){

    }


}

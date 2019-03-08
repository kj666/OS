import java.io.*;
import java.util.*;



public class Main {

    //Quantum Value for scheduler
    private static int q = 0;
    //Main thread
    public static void main(String[] args) throws IOException,FileNotFoundException {
        PrintWriter writer = new PrintWriter("output.txt");
        //Scanner to read input file
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        //read quantum from txt file
        q = scanner.nextInt();
        //Arraylist to store all the processes from the input file
        ArrayList<Process> processes = inputParser(scanner);
        //Print quantum
        writer.println("quantum size: "+ q);
        //Print processes
        for (Process p: processes){
            writer.println(p.getUserID()+"   "+p.getProcessID()+ "    "+p.getReadyTime()+"  "+p.getServiceTime());
        }

        //Create a new scheduler
        Scheduler scheduler = new Scheduler(processes, q, writer);
        Thread t1 = new Thread(scheduler);
        t1.start();

    }

    //Parse the input text file and return an array of processes
    public static ArrayList<Process> inputParser(Scanner scanner){
        //temporary array to store processes
        ArrayList<Process> processes = new ArrayList<>();
        scanner.nextLine();

        //scan if next line exists
        while(scanner.hasNextLine()){
            String info = scanner.nextLine();
            String arr[] = info.split(" ", 2);

            String userID = arr[0];
            int numberProcess = Integer.valueOf(arr[1]);
            for(int i = 0; i< numberProcess; i++){
                String l[] = scanner.nextLine().split(" ",2);
                Process process = new Process(i+"", userID, Integer.valueOf(l[0]), Integer.valueOf(l[1]),0);
                processes.add(process);
            }
        }
        return processes;
    }

    public static void processHandler(){

    }

    /**
     * Convert process arraylist into an array of threads
     */
    public Thread[] convertArrayToThread(ArrayList<Process> process){
        Thread tmpThread[] = new Thread[process.size()];

        for(int i = 0; i< process.size(); i++){
            tmpThread[i] = new Thread(process.get(i));
        }

        return tmpThread;
    }
}

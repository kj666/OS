import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//Used this as a reference algorithm
//https://www.youtube.com/watch?v=3N2t9_6Co3U

public class Main {

    //Quantum Value for scheduler
    private static int q = 0;

    //Main thread
    public static void main(String[] args) throws IOException {

        //Scanner to read input file
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        //read quantum from txt file
        q = scanner.nextInt();
        //Arraylist to store all the processes from the input file
        ArrayList<Process> processes = inputParser(scanner);

        //Print quantum
        System.out.println("quantum size: "+ q);
        //Print processes
        for (Process p: processes){
            System.out.println(p.getUserID()+"   "+p.getProcessID()+ "    "+p.getReadyTime()+"  "+p.getServiceTime());
        }

        //Create a new scheduler
        Scheduler scheduler = new Scheduler(processes, q);
        scheduler.run();
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

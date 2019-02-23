import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//https://www.youtube.com/watch?v=3N2t9_6Co3U


public class Main {

    //Quantum Value
    private static int q = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        q = scanner.nextInt();
        System.out.println(q);

        ArrayList<Process> processes = inputParser(scanner);
        ArrayList<Thread> threads = new ArrayList<>();

        Scheduler scheduler = new Scheduler(processes, q);
        scheduler.run();


        int t = 0;
//        while(true){
//            t++;
//            System.out.println(t);
//            if(t == 6)
//                break;
//        }

//        for(int i = 0; i < processes.size(); i++){
//            Thread temp = new Thread(processes.get(i));
//            threads.add(temp);
//        }
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

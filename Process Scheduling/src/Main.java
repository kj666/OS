import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//User object
class User extends Thread{
    //members
    private String name;
    private int numberProcess;
    private ArrayList<Process> processes;

    public User(String name, int numberProcess) {
        this.name = name;
        this.numberProcess = numberProcess;
    }

    @Override
    public void run() {
        super.run();
    }

    public String getUsername() {
        return name;
    }


    public void setUsername(String name) {
        this.name = name;
    }

    public int getNumberProcess() {
        return numberProcess;
    }

    public void setNumberProcess(int numberProcess) {
        this.numberProcess = numberProcess;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public void addProcess(Process process){
        this.processes.add(process);
    }
}

//Process Object
class Process extends Thread{
    private int readyTime;
    private int serviceTime;

    public Process() {
    }

    public Process(int readyTime, int serviceTime) {
        this.readyTime = readyTime;
        this.serviceTime = serviceTime;
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
}

public class Main {

    //Quantum Value
    private static int q = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        q = scanner.nextInt();
        System.out.println(q);
        ArrayList<User> users = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            users.add(getUserProcessInfo(scanner));
        }

        for(User u: users){
            System.out.println(u.getUsername());
        }

    }

    public static User getUserProcessInfo(Scanner scanner){

        String info = scanner.nextLine();
        String arr[] = info.split(" ", 2);
        int numberProcess = Integer.valueOf(arr[1]);
        System.out.println(arr[0]+" " +numberProcess);

        User user = new User(arr[0], numberProcess);
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < numberProcess; i++) {
            String l[] = scanner.nextLine().split(" ",2);
            System.out.println(l[0]+" "+ l[1]);

            Process process = new Process(Integer.valueOf(l[0]), Integer.valueOf(l[1]));
            processes.add(process);
        }
        user.setProcesses(processes);
        return user;

    }


    public static void outputParser(){

    }

    public static void processHandler(){

    }
}

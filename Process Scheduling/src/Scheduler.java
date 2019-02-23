import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;
    private int q;
    private int t = 0;

    public Scheduler(ArrayList<Process> processes, int q) {
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

//        if(Thread.interrupted()){
//            System.out.println("Scheduler Stopped");
//        }
//        try {
//            System.out.println("start- sleep");
//            Thread.sleep(6000);
//            System.out.println("Scheduler paused");
//        }catch (InterruptedException ex){
//            System.out.println("Scheduler Resumed");
//        }

        while(!isAllProcessTerminated(t)){
            for(Process p : processes){
                if(p.getReadyTime() == t){
                    readyQueue.add(p);
                    System.out.println("Time "+ t+" :"+ p.getUserID() +" "+p.getProcessID());
                }
            }

            t++;

            if(t == 20)
                break;
        }
        System.out.println("Terminated: "+isAllProcessTerminated(t));

    }


    public void suspend(){
        System.out.println("Scheduler Suspended");
    }



    public void addToReadyQueue(Process process){
        readyQueue.add(process);
    }

    public int getTotalReadyUser(){
        ArrayList<String> users = new ArrayList<>();
        for (Process p: readyQueue){
            if(!users.contains(p.getUserID()))
                users.add(p.getUserID());
        }
        return users.size();
    }

    public int getTotalReadyProcessPerUser(String userID){
        ArrayList<Process> findProcess = new ArrayList<>();
        for(Process p : readyQueue){
            if(p.getUserID().equals(userID)){
                findProcess.add(p);
            }
        }
        return findProcess.size();

    }

    public void getProcessAllowedBurst(){

    }

    public boolean isAllProcessTerminated(int time){
        if(time == 0 || time == 1) {
            System.out.println("time ran");
            return false;
        }
        else {
            System.out.println("RQ ran");
            return readyQueue.isEmpty();
        }
    }

}

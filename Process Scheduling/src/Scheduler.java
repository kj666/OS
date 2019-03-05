import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;
    private int q;
    private int t = 0;
    private int currentTime = 0;


    public Scheduler(ArrayList<Process> processes, int q) {
        this.processes = processes;
        this.q = q;
    }

    public void run() {
        System.out.println("Scheduler Started");

        while(!isAllProcessTerminated()){

            //add process from ArrayList to readyQueue when process gets to arrival time
            for(Process p : processes){
                if(p.getReadyTime() <= t){
                    if(!readyQueue.contains(p) && !finishedQueue.contains(p))
                        readyQueue.add(p);
                }
            }

            for(Process p: readyQueue){
                setProcessBurstTime(p);
//                System.out.println("beforeSize: "+readyQueue.size());
                System.out.println("Time "+ t+" - User "+ p.getUserID() +" P"+p.getProcessID()+" - AllowedBT: "+p.getAllowedBurstTime()+" - RT: "+p.getRemainingTime()+", Started");

                t = p.execute(t);
//                setProcessBurstTime(p.getUserID());
//                p.decreaseRemainingTime();
                System.out.println("Time "+ t+" - User "+ p.getUserID() +" P"+p.getProcessID()+" - AllowedBT: "+p.getAllowedBurstTime()+" - RT: "+p.getRemainingTime()+", Executed");
                p.setRemainingTime(p.getRemainingTime()-p.getAllowedBurstTime());

//                System.out.println("Process Removed");
                if(p.getRemainingTime()<=0) {
                    finishedQueue.add(p);
                    System.out.println("Time "+ t+" - User "+ p.getUserID() +" P"+p.getProcessID()+" - AllowedBT: "+p.getAllowedBurstTime()+" - RT: "+p.getRemainingTime()+", Finished");
                }
                else{
                    readyQueue.add(p);

                }

                //gotta remove properly from queue
                readyQueue.remove();
//                System.out.println("afterSize: "+readyQueue.size());
                break;

//                t+= p.getAllowedBurstTime() - 1;
//                readyQueue.remove();
            }

            t++;
        }
        System.out.println("Terminated: "+isAllProcessTerminated());

    }
    //Get arrived processes
    public void getArrivedProcess(){
        for(Process p: processes) {
            if (p.getReadyTime() == currentTime) {
                readyQueue.add(p);
            }
        }
    }


    //Get total number of user with ready processes
    public int getTotalReadyUser(){
        ArrayList<String> users = new ArrayList<>();
        for (Process p: readyQueue){
            if(!users.contains(p.getUserID()))
                users.add(p.getUserID());
        }
        return users.size();
    }

    //get total number of processes that are ready per user
    public int getTotalReadyProcessPerUser(String userID){
        ArrayList<Process> findProcess = new ArrayList<>();
        for(Process p : readyQueue){
            if(p.getUserID().equals(userID)){
                findProcess.add(p);
            }
        }
//        System.out.println("User Size: "+ findProcess.size());
        return findProcess.size();
    }

    //Set Burst time for Specified process
    public void setProcessBurstTime(Process p){
        p.setAllowedBurstTime(getProcessAllowedBurst(p.getUserID()));
    }

    //Get calculated allowed burst time per quantum
    //burst time per process for given user
    public int getProcessAllowedBurst(String userID){
        double splitUser = q/(getTotalReadyUser());
        Double splitProcessUser = splitUser/getTotalReadyProcessPerUser(userID);
//        System.out.println("BURST "+ splitProcessUser);
        return splitProcessUser.intValue();
    }

    //check if finished queue = process Array
    public boolean isAllProcessTerminated(){
        if(processes.size() == finishedQueue.size())
            return true;
        else
            return false;
    }
}

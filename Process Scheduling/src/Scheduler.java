import java.util.*;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();

    private ArrayList<Process> processes;

    private int q;
    private int t = 1;//------------------------
    private int cycle = 1;
    private String prevUser;
    private Process runningProcess;

    int clock;

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
                    if(!readyQueue.contains(p) && !finishedQueue.contains(p)) {
                        readyQueue.add(p);
                    }

                }
                //LOOOOOOK HEEREEE
//                if(t >= cycle*q){
////                    System.out.println("Cycle finished");
//                    alternateUser(prevUser);
//                }
            }

//            for(Process p: readyQueue){
//                System.out.println("readyQ"+ p.getUserID()+" "+ p.getProcessID());
//            }

            //Check if ready queue is empty and then proceed
            if(!readyQueue.isEmpty()){
                //get first item from queue
                runningProcess = readyQueue.element();

                //Set the burst time of the current process
                setProcessBurstTime(runningProcess);
                //remove that process from readyQueue
                readyQueue.remove();
                //Keep track of the previous User
                prevUser = runningProcess.getUserID();

                runningProcess.setProcessState(true);

                if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
                    System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");

                System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");
                runningProcess.setRemainingTime(runningProcess.getRemainingTime() - runningProcess.getAllowedBurstTime());

                t = runningProcess.execute(t);//------------------------GO CHECK PROCESS

                if (runningProcess.getRemainingTime() <= 0) {
                    runningProcess.setRemainingTime(0);
                    finishedQueue.offer(runningProcess);
                    runningProcess.setProcessState(false);
                    System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
                } else {
                    readyQueue.offer(runningProcess);
                    System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
                }
//                alternateUser(runningProcess.getUserID());

            }

//            t++; //------------------------IM NOT INCREMENTING
        }
        System.out.println("Terminated: "+isAllProcessTerminated());

    }


    //Alternate User in Queue
    public void alternateUser(String currUser){
        Queue<Process> tempQueue = new LinkedList<>();
        for(Process p: readyQueue){
            if(!p.getUserID().equals(currUser)){
                tempQueue.add(p);
            }
        }
        for(Process p: readyQueue){
            if(p.getUserID().equals(currUser)){
                tempQueue.add(p);
            }
        }
        readyQueue.clear();
        readyQueue.addAll(tempQueue);

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

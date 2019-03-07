import java.util.*;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;
    private ArrayList<String> users;

    private int q;
    private int t = 1;
    private int cycle = 1;
    private String prevUser = "";
    private Process runningProcess;
    private String prevProcess ="";
    private boolean running = false;


    public Scheduler(ArrayList<Process> processes, int q) {
        this.processes = processes;
        this.q = q;
        this.users = new ArrayList<>(processes.size());
    }

    public void run() {
        System.out.println("Scheduler Started");
        while(!isAllProcessTerminated()){


            //add process from ArrayList to readyQueue when process gets to arrival time
            for(Process p : processes) {
                if ( p.getReadyTime() <= t && !readyQueue.contains(p) && !finishedQueue.contains(p) && (!prevProcess.equals(p.getProcessID())) ) {
                    readyQueue.add(p);
                }
            }
//                if(t >= cycle*q){
////                    System.out.println("Cycle finished");
//                    alternateUser(prevUser);
//                }


            //Print Ready Queue for debugging
                for (Process p : readyQueue) {
                    System.out.println("readyQ" + p.getUserID() + " " + p.getProcessID());
                }

                //Check if ready queue is empty and then proceed
                if (!readyQueue.isEmpty()) {

                    //check if time is a quantum start
                    if(t%q == 1){
                        //Calculate all the burst time in ready queue
                        calculateAtQuatum();
                        System.out.println("QQQQQQQQQQ");
                    }

                        //get first item from queue
                        runningProcess = readyQueue.element();
                    moveToEnd(runningProcess);
                        //Set the burst time of the current process
//                    setProcessBurstTime(runningProcess);
                        //remove that process from readyQueue
                        readyQueue.remove();
                        prevProcess = runningProcess.getProcessID();
                        System.out.println(prevProcess);



                        //Process started
                        if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");

                        //Process Resuming
                        System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");


                        int processStart = t;
                        for (int i = processStart; i < processStart + runningProcess.getAllowedBurstTime(); i++) {
                            runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
                            t++;
                            if(t%q == 1)
                                break;
                        }
//                    t = runningProcess.execute(t);

                        if (runningProcess.getRemainingTime() <= 0) {
                            runningProcess.setRemainingTime(0);
                            finishedQueue.offer(runningProcess);
                            running = false;
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
                        }
                        else {
                            readyQueue.offer(runningProcess);
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
                        }
                    }
                else{
                    //Increment time
                    t++;
                }
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

    /**
     * Get calculated allowed burst time per quantum
     * burst time per process for given user
     * @param userID
     * @return
     */
    public int getProcessAllowedBurst(String userID){
        double splitUser = q/(getTotalReadyUser());
        Double splitProcessUser = splitUser/getTotalReadyProcessPerUser(userID);
//        System.out.println("BURST "+ splitProcessUser);
        return splitProcessUser.intValue();
    }


    /**
     * Check if finished queue contains all the initial processes
     * @return
     */
    public boolean isAllProcessTerminated(){
        if(processes.size() == finishedQueue.size())
            return true;
        else
            return false;
    }

    public void calculateAtQuatum(){
        for(Process p: readyQueue){
            setProcessBurstTime(p);
        }
    }

    public void moveToEnd(Process p){
        ArrayList<Process> tmp = new ArrayList<>();
        tmp.addAll(readyQueue);


        tmp.remove(p);
        tmp.add(p);

        for (Process pr : tmp) {
            System.out.println("tempQ" + pr.getUserID() + " " + pr.getProcessID());
        }

        readyQueue.clear();
        readyQueue.addAll(tmp);

    }

}

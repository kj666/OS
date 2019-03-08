import java.util.*;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;

    private int q;
    private int t = 1;
    private int cycle = 1;
    private Process runningProcess =null;
    private Process previousProcess = null;

    /**
     * Default constructor for scheduler
     * @param processes
     * @param q
     */
    public Scheduler(ArrayList<Process> processes, int q) {
        this.processes = processes;
        this.q = q;
    }

    public void run() {
        System.out.println("Scheduler Started");
        while(!isAllProcessTerminated()){

            checkReadyProcess();

            //Print Ready Queue for debugging

//            for (Process p : readyQueue) {
//                System.out.println("Before readyQ" + p.getUserID() + " " + p.getProcessID());
//            }

                //Check if ready queue is empty and then proceed
                if (!readyQueue.isEmpty()) {

                    //check if time is a quantum start
//                    if(t%q == 1){
//                        //Calculate all the burst time in ready queue
//                        calculateAtQuatum();
//                        System.out.println("QQQQQQQQQQ");
//                    }


//                    if(previousProcess != null){
//                        moveToEnd(previousProcess);
//                    }


                        //get first item from queue
                    for (Process p : readyQueue) {
                        System.out.println("AB readyQ" + p.getUserID() + " " + p.getProcessID());
                    }

                        checkRunning(runningProcess);
                        runningProcess = readyQueue.peek();
                        previousProcess = runningProcess;

                        //Set the burst time of the current process
                        setProcessBurstTime(runningProcess);
                        //remove that process from readyQueue
//                        readyQueue.remove();
//                        System.out.println("Removed From Q");


                        //Process started
                        if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");

                        //Process Resuming
                        System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");
                        readyQueue.remove();

                        if(!(runningProcess.getRemainingTime() <=0)) {
                            //running process
                            int processStart = t;
                            for (int i = processStart; i < processStart + runningProcess.getAllowedBurstTime(); i++) {
                                runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
                                t++;
//                            if(t%q == 1)
//                                break;
                            }
                        }

                    //Process Finished
                        if (runningProcess.getRemainingTime() <= 0) {
                            runningProcess.setRemainingTime(0);
                            finishedQueue.offer(runningProcess);
//                            finishedQueue.add(runningProcess);
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
                        }
                        //Process paused
                        else {

                            readyQueue.offer(runningProcess);
//                            readyQueue.remove();
                            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
                        }

                    for (Process p : readyQueue) {
                        System.out.println("After readyQ" + p.getUserID() + " " + p.getProcessID());
                    }
                    }
                else{
                    //Increment time
                    t++;
                }
                System.out.println("CYCLE: "+ cycle);
                cycle++;
            for (Process p : finishedQueue) {
                System.out.println("FinishedQ" + p.getUserID() + " " + p.getProcessID());
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

    /**
     * Get total number of ready user in ready queue
     * @return
     */
    public int getTotalReadyUser(){
        ArrayList<String> users = new ArrayList<>();
        for (Process p: readyQueue){
            if(!users.contains(p.getUserID()))
                users.add(p.getUserID());
        }
        return users.size();
    }

    /**
     * Get total numer of process that are ready in ready queue per user
     * @param userID
     * @return
     */
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

    /**
     * Set burst time to specific process according to user
     * @param p
     */
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

    /**
     * Set burst time for every process in quantum
     */
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

        readyQueue.clear();
        readyQueue.addAll(tmp);

//        for (Process pr : readyQueue) {
//            System.out.println("tempQ" + pr.getUserID() + " " + pr.getProcessID());
//        }
    }

    public void checkRunning(Process p){
        Process tmp = null;
        for (Process pr: readyQueue){
            if(readyQueue.element().equals(p)){
                tmp = readyQueue.element();
            }
        }
        if(tmp!=null)
            moveToEnd(tmp);

    }

    /**
     * Check if there is ready process that can be added to ready queue at current t
     */
    public void checkReadyProcess(){
        //add process from ArrayList to readyQueue when process gets to arrival time
        for (Process p : processes) {
            if (p.getReadyTime() <= t && !readyQueue.contains(p) && !finishedQueue.contains(p) ) {
//                if(prevProcess!=null)
//                    if(p.equals(prevProcess)) {
//                        readyQueue.add(p);
//                    }
//                else
                    readyQueue.add(p);

            }
        }
    }

}

import java.io.PrintWriter;
import java.util.*;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;
    private PrintWriter writer;

    private int q;
    private volatile int t = 1;
    private int cycle = 1;
    private Process runningProcess =null;
    private Thread runningThread = null;

    /**
     * Default constructor for scheduler
     * @param processes
     * @param q
     */
    public Scheduler(ArrayList<Process> processes, int q, PrintWriter writer) {
        this.processes = processes;
        this.q = q;
        this.writer = writer;
    }

    public void run() {
        writer.println("Scheduler Started");

        //Time thread
        Timer time = new Timer(t);
        //start timer
        time.start(true);

        while(!isAllProcessTerminated()){

            checkReadyProcess();

//            printReadyQueue("Before");

            //Check if ready queue is empty and then proceed
            if (!readyQueue.isEmpty()) {
                time.resume();

                //get first item from queue
                checkRunning(runningProcess);
                runningProcess = readyQueue.peek();

                //Set the burst time of the current process
                setProcessBurstTime(runningProcess);

                //Process started
                if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
                    writer.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");

                //Process Resuming
                writer.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");
                readyQueue.remove();

                //Execute Program
                if(!(runningProcess.getRemainingTime() <=0)) {

                    //running process
                    int processStart = t;

                    //Pause Timer Thread
                    time.pause();

                    //Start Running process
                    //process will get the time from timer
                    //and execute
                    runningProcess.start(true);

                    //check the process state
                    //to notify timer to resume
                    while(runningProcess.getState().equals("paused")){
                        time.resume();
                        runningProcess.pause();
                    }

                    //control the remaining time
                    for (int i = processStart; i < processStart + runningProcess.getAllowedBurstTime(); i++) {
                        runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
                        t++;
                    }

                }

                //Process Finished
                if (runningProcess.getRemainingTime() <= 0) {
                    runningProcess.setRemainingTime(0);
                    finishedQueue.offer(runningProcess);
                    writer.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
                }
                //Process paused
                else {
                    readyQueue.offer(runningProcess);
                    writer.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
                }
                runningProcess.pause();
            }

            else{
                //Increment time
                t++;


            }

//            System.out.println("CYCLE: "+ cycle);
            cycle++;
        }
        //end timer thread
        time.end();

        writer.println("Terminated: "+isAllProcessTerminated());
        writer.close();

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
     * Move specified process to end of the ready queue
     */
    public void moveToEnd(Process p){
        ArrayList<Process> tmp = new ArrayList<>();
        tmp.addAll(readyQueue);

        tmp.remove(p);
        tmp.add(p);

        readyQueue.clear();
        readyQueue.addAll(tmp);
    }

    /**
     * Check if specified process is on top of ready queue
     * If it is move to the end of the queue
     * @param p
     */
    public void checkRunning(Process p){
        Process tmp = null;
        if(readyQueue.element().equals(p)){
            tmp = readyQueue.element();
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
            if (p.getReadyTime() <= t && !readyQueue.contains(p) && !finishedQueue.contains(p) )
                readyQueue.add(p);
        }
    }

    /**
     * Print ready queue
     * @param tag
     */
    public void printReadyQueue(String tag){
        for(Process p : readyQueue){
            System.out.println(tag+" " + p.getUserID() + " " + p.getProcessID());
        }
    }

}

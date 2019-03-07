import java.util.*;

class Scheduler implements Runnable {

    private Queue<Process> readyQueue = new LinkedList<>();
    private Queue<Process> finishedQueue = new LinkedList<>();
    private ArrayList<Process> processes;
//
//    private int q;
//    private int t = 1;
//
//
//    private int cycle = 1;
//    private boolean skip = true;
//    private int cpuTime = -1;
//    private int totalTime;
//    private Process runningProcess;
//    private Process nextProcess;
//
//
//
//    private String prevUser;
//
//
//    private boolean processRunning = false;
//
//
    public Scheduler(ArrayList<Process> processes, int q) {

    }

    @Override
    public void run() {

    }

    //
//    public void run() {
//        System.out.println("Scheduler Started");
//
//        while(skip){
//            cpuTime++;
//            processReady();
//        }
//
//        while(t < totalTime ){
//
//            if(nextProcess!= null){
//                readyQueue.add(nextProcess);
//            }
//
//            executeQuantum();
//        }
//
//        while(!isAllProcessTerminated()){
//
//            //add process from ArrayList to readyQueue when process gets to arrival time
//            processReady();
//
//            //Print Ready Queue
//            for(Process p: readyQueue){
//                System.out.println("readyQ"+ p.getUserID()+" "+ p.getProcessID());
//            }
//
//            //Check if ready queue is empty and then proceed
//            if(!readyQueue.isEmpty()){
//                executeQuantum();
//            }
//            else{
//                t++;
//            }
//            System.out.println("CPU Time: "+cpuTime);
//            cpuTime++;
//
//        }
//        System.out.println("Terminated: "+isAllProcessTerminated());
//
//    }
//    public void executeQuantum(){
//        while(readyQueue.size()<1){
//            processReady();
//            cpuTime++;
//        }
//        executeProcess();
////        int startTime = t;
////        for(int i = startTime; i <= startTime+runningProcess.getAllowedBurstTime();i++){
////            t++;
////        }
//    }
//
//    public void executeProcess(){
//        //get first item from queue
//        runningProcess = readyQueue.poll();
//
//        int remainingTime = runningProcess.getRemainingTime();
//        printing();
//        if (remainingTime<= q){
//            System.out.println("Inside QUANTUM");
//            printing();
//            for(int i = 1; i<= remainingTime;i++){
//                t++;
//                cpuTime++;
//                runningProcess.reduceRemainingTime(1);
//                processReady();
//            }
//            nextProcess = null;
//            finishedQueue.add(runningProcess);
//            if (runningProcess.getRemainingTime() <= 0)
//                System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
//
//        }
//        else{
////            printing();
//            for(int i = 1; i <= q; i++){
//                t++;
//                cpuTime++;
//                runningProcess.reduceRemainingTime(1);
//                processReady();
//            }
//            nextProcess = runningProcess;
//        }
//        //Set the burst time of the current process
////        setProcessBurstTime(runningProcess);
////
////        //remove that process from readyQueue
////        readyQueue.remove();
////
////        //Keep track of the previous User
////        //Used to prioritize by User
////        prevUser = runningProcess.getUserID();
////
////        //Set Running process state
////        runningProcess.setProcessState(true);
////        processRunning = true;
////
////        if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
////            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");
////
////
////        System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");
////        runningProcess.setRemainingTime(runningProcess.getRemainingTime() - runningProcess.getAllowedBurstTime());
////
//////        int startTime = t;
//////        for(int i = startTime; i < startTime+runningProcess.getAllowedBurstTime();i++){
//////            t++;
//////        }
//////        t = runningProcess.execute(t);
////
////        if (runningProcess.getRemainingTime() <= 0) {
////            runningProcess.setRemainingTime(0);
////            finishedQueue.offer(runningProcess);
////            runningProcess.setProcessState(false);
////            processRunning = false;
////            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
////        }
////        else {
////            readyQueue.offer(runningProcess);
////            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
////        }
////        t++;
//
//    }
//
//    public void printing(){
//        if (runningProcess.getServiceTime() == runningProcess.getRemainingTime())
//            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Started");
//
//        System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Resumed");
//
//        if (runningProcess.getRemainingTime() <= 0)
//            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Finished");
//        else
//            System.out.println("Time " + t + " - User " + runningProcess.getUserID() + " P" + runningProcess.getProcessID() + " - AllowedBT: " + runningProcess.getAllowedBurstTime() + " - RT: " + runningProcess.getRemainingTime() + ", Paused");
//
//    }
//
//
//    //Add process to readyQ
//    public void processReady(){
//        for(Process p : processes){
//            if(p.getReadyTime() == cpuTime){
//                skip = false;
//                readyQueue.add(p);
////                if(!readyQueue.contains(p) && !finishedQueue.contains(p)) {
////                    readyQueue.add(p);
////                }
//            }
//        }
//    }
//
//    //Alternate User in Queue
//    public void alternateUser(String currUser){
//        Queue<Process> tempQueue = new LinkedList<>();
//        for(Process p: readyQueue){
//            if(!p.getUserID().equals(currUser)){
//                tempQueue.add(p);
//            }
//        }
//        for(Process p: readyQueue){
//            if(p.getUserID().equals(currUser)){
//                tempQueue.add(p);
//            }
//        }
//        readyQueue.clear();
//        readyQueue.addAll(tempQueue);
//
//    }
//
//    //Get total number of user with ready processes
//    public int getTotalReadyUser(){
//        ArrayList<String> users = new ArrayList<>();
//        for (Process p: readyQueue){
//            if(!users.contains(p.getUserID()))
//                users.add(p.getUserID());
//        }
//        return users.size();
//    }
//
//    //get total number of processes that are ready per user
//    public int getTotalReadyProcessPerUser(String userID){
//        ArrayList<Process> findProcess = new ArrayList<>();
//        for(Process p : readyQueue){
//            if(p.getUserID().equals(userID)){
//                findProcess.add(p);
//            }
//        }
////        System.out.println("User Size: "+ findProcess.size());
//        return findProcess.size();
//    }
//
//    //Set Burst time for Specified process
//    public void setProcessBurstTime(Process p){
//        p.setAllowedBurstTime(getProcessAllowedBurst(p.getUserID()));
//    }
//
//    //Get calculated allowed burst time per quantum
//    //burst time per process for given user
//    public int getProcessAllowedBurst(String userID){
//        double splitUser = q/(getTotalReadyUser());
//        Double splitProcessUser = splitUser/getTotalReadyProcessPerUser(userID);
////        System.out.println("BURST "+ splitProcessUser);
//        return splitProcessUser.intValue();
//    }
//
//
//    //check if finished queue = process Array
//    public boolean isAllProcessTerminated(){
//        if(processes.size() == finishedQueue.size())
//            return true;
//        else
//            return false;
//    }

}

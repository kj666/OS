//Process Object
class Process implements Runnable{

    //Process members
    private String processID;
    private String userID;
    private int readyTime;
    private int serviceTime;
    private int remainingTime;
    private int finishedTime;
    private int allowedBurstTime = 0;
    private boolean processState = false;
    private int startTime;

    public Process() {
    }

    //constructor
    public Process(String processID, String userID, int readyTime, int serviceTime, int startTime) {
        this.processID = processID;
        this.userID = userID;
        this.readyTime = readyTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;
        this.allowedBurstTime = serviceTime;
        this.processState = false;
        this.startTime = startTime;
    }

    public void run() {
        if (serviceTime == remainingTime) {
            System.out.println("Time " + startTime + " - User " + userID + " P" + processID + " - AllowedBT: " + allowedBurstTime + " - RT: " + remainingTime + ", Started");
        }
        for (int i = 0; i < allowedBurstTime; i++){

        }
    }

    public int execute(int t){
        int time = t + allowedBurstTime;
        return time;
    }

    //Getters and Setters

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

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(int finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAllowedBurstTime() {
        return allowedBurstTime;
    }

    public void setAllowedBurstTime(int allowedBurstTime) {
        this.allowedBurstTime = allowedBurstTime;
    }

    public boolean isProcessState() {
        return processState;
    }

    public void setProcessState(boolean processState) {
        this.processState = processState;
    }
}

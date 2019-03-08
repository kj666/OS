
//Process Object
class Process implements Runnable{

    //Process members
    private String processID;
    private String userID;
    private int readyTime;
    private int serviceTime;

    private int remainingTime;
    private int allowedBurstTime = 0;
    private int startTime;
    private int t;

    //constructor
    public Process(String processID, String userID, int readyTime, int serviceTime, int startTime) {
        this.processID = processID;
        this.userID = userID;
        this.readyTime = readyTime;
        this.serviceTime = serviceTime;
        this.remainingTime = serviceTime;
        this.allowedBurstTime = serviceTime;
        this.startTime = startTime;
    }

    public void run() {

        for (int i = startTime; i < startTime + allowedBurstTime; i++) {
            remainingTime -=1;
            t++;
        }
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
        this.t = startTime;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}

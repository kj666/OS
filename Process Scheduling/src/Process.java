
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

    private volatile boolean pause = false;
    private volatile String state = "new";
    private Thread workerThread;

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
        boolean workDone = false;
        while(!workDone){
            while(pause){
                setState("Paused");
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){

                }
            }
            setState("Running");
            if(t == startTime+ allowedBurstTime){
                pause();
                break;
            }
            else{
                t++;
            }
            System.out.println("hi");
        }
        setState("finished");
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
        this.startTime = t;
    }

    public void pause(){
        pause = true;
    }

    public void resume(){
        pause = false;

        if(workerThread!=null){
            workerThread.interrupt();
        }
    }

    public void start(boolean start){
        pause = !start;
        workerThread = new Thread(this);
        workerThread.start();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

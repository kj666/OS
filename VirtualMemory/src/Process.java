class Process implements Runnable {
    private int arrivalTime;
    private int burstTime;
    private int PID;
    private boolean isReady;
    private boolean isFinished;
    private double remainingTime;
    private ArrayList <Commands>commands;
    public Process(int arrivalTime, int burstTime, int PID) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.PID = PID;
    }

    public void print(){
        System.out.println("PID: "+PID+" - AT: "+ arrivalTime+" - BT: "+burstTime);
    }

    @Override
    public void run() {
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    //call an instruction from the Virtual memory api
    public void commandCall( ArrayList<Commands>commmands,int firstParameter,int secondParameter)
    {

    }

}

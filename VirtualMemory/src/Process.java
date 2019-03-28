class Process implements Runnable {
    private int arrivalTime;
    private int burstTime;
    private int PID;

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
}

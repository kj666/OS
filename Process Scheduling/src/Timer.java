public class Timer implements Runnable {

    private int t;
    private volatile boolean pause = false;
    private volatile String state = "new";
    private Thread workerThread;

//used to track the time with a thread
    public Timer(int t) {
        this.t = t;
    }

    @Override
    public void run() {
        boolean workDone = false;
        while(!workDone){
            //pause thread
            while(pause){
                setState("Paused");
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){

                }
            }
            setState("Running");
            t++;
        }
        setState("finished");
    }

    //pause thread
    public void pause(){
        pause = true;
    }

    //reume thread
    public void resume(){
        pause = false;

        if(workerThread!=null){
            workerThread.interrupt();
        }
    }

    //start thread
    public void start(boolean start){
        pause = !start;
        //create the thread
        workerThread = new Thread(this);
        workerThread.start();
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //interrupt thread
    public void end(){
        Thread.currentThread().interrupt();
    }
}

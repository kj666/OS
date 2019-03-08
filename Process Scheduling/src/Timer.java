public class Timer implements Runnable {

    private int t;
    private boolean running;

    public Timer(int t) {
        this.t = t;
        this.running = false;
    }

    @Override
    public void run() {
        while(running){
            t++;
        }
    }

    public void stopRunning(){
        this.running = false;
    }

    public void startRunning(){
        this.running = true;
    }

}

import java.io.FileNotFoundException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");

        VMmanager vm = new VMmanager();

        Scheduler scheduler = new Scheduler();
        scheduler.startScheduler();
    }


}

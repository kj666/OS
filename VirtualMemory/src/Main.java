import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    final int q = 3000;
    final int numberCore = 2;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");

        VMmanager vm = new VMmanager();

        Scheduler scheduler = new Scheduler();
        scheduler.startScheduler();
    }
    //parse commands text file and return an array of commands
    public static ArrayList<Command> commmandParser(Scanner scanner){
        //temporary array to store commands
        ArrayList<Command> command = new ArrayList<>();
        scanner.nextLine();

        //scan if next line exists
        while(scanner.hasNextLine()){

            String info = scanner.nextLine();
            String arr[] = info.split(" ", 3);
            // store the command id
            String commandID = arr[0];
            int firstParameter = Integer.valueOf(arr[1]);
            int secondParameter;
            if(arr.length<2) {
                secondParameter = null;
            }
            else {
                int secondParameter = Integer.valueOf(arr[2]);
            }
            Command command= new Command(commandID,firstParameter,secondParameter);
            command.add(command);

        }
        return command;
    }


}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VMmanager implements Runnable {

    private int maxSize;
    private int value;
    private String variableID;

    @Override
    public void run() {

    }

    public VMmanager() throws FileNotFoundException {
        parseMemConfigFile("memconfig.txt");
        System.out.println("memory size: "+maxSize);
    }

    void parseMemConfigFile(String fileName)throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        maxSize = scanner.nextInt();

    }
}

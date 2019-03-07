package flowShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    Scanner scanner;
    int machineCount, jobCount;

    Reader(String fileName) throws FileNotFoundException {
        scanner = new Scanner(new File(fileName));
        scanner.nextLine();
        jobCount  = scanner.nextInt();
        machineCount = scanner.nextInt();
    }

    int[] readJob(){
        int[] job = new int[machineCount];
        for (int index = 0; index< machineCount; index ++) {
            scanner.nextInt();
            job[index] = scanner.nextInt();
        }
        return job;
    }

    Instance readInstance(){
        Instance instance = new Instance(jobCount, machineCount);
        for (int index = 0; index < jobCount; index++)
            instance.add(index,readJob());
        return instance;
    }

}

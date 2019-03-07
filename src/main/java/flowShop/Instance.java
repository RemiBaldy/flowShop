package flowShop;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    int jobCount;
    int machineCount;
    int[][] processingTime;

    public Instance(int jobCount, int machineCount) {
        System.out.println(machineCount);
        this.machineCount = machineCount;
        this.jobCount = jobCount;
        processingTime = new int[jobCount][machineCount];
    }

    public void add(int job, int[] times) {
        processingTime[job] = times;
    }
}

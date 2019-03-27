package flowShop;

import com.google.ortools.sat.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Solver {
    static {
        System.loadLibrary("jniortools");
    }

    public static void main(String[] args) throws Exception {

        Instance instance = new Reader("./src/main/resources/car8").readInstance();
        CpModel model = new CpModel();

        int jobCount = instance.jobCount;
        int machineCount = instance.machineCount;

        int maxProcessTime = 0;

        for (int i = 0; i < jobCount; i++)
            for (int j = 0; j < machineCount; j++)
                maxProcessTime += instance.processingTime[i][j];

        //System.out.println(maxProcessTime);

        IntervalVar[][] intervalVarsByJob = new IntervalVar[jobCount][machineCount];
        IntervalVar[][] intervalVarsByMachine = new IntervalVar[machineCount][jobCount];
        IntVar[][] ends = new IntVar[jobCount][machineCount];
        IntVar[][] starts = new IntVar[jobCount][machineCount];

        for (int i = 0; i < jobCount; i++) {
            for (int j = 0; j < machineCount; j++) {

                //IntVar end =  model.newIntVar(0,maxProcessTime,"end "+i+j);
                ends[i][j] =  model.newIntVar(0,maxProcessTime,"end "+i+j);
                starts[i][j] = model.newIntVar(0,maxProcessTime,"start "+i+j);

                intervalVarsByJob[i][j] = model.newIntervalVar(starts[i][j], instance.processingTime[i][j],
                                                       ends[i][j], "interval "+i+j);
                intervalVarsByMachine[j][i] = intervalVarsByJob[i][j];
            }
        }


        for (int i = 0; i < jobCount; i++) {
            model.addNoOverlap(intervalVarsByJob[i]);
        }
        for (int i = 0; i < machineCount; i++) {
            model.addNoOverlap(intervalVarsByMachine[i]);
        }


        for (int i = 0; i < jobCount; i++) {
            for (int j = 0; j < machineCount-1; j++) {
                model.addLessOrEqual(ends[i][j],starts[i][j+1]);
            }
        }


        //IntVar end = model.newIntVar(0,100,"end");

        IntVar lastTasks[] = new IntVar[jobCount];

        for (int i = 0; i < jobCount; i++) {
            lastTasks[i] = ends[i][machineCount-1];
        }

        IntVar end = model.newIntVar(0,maxProcessTime,"end");
        model.addMaxEquality(end, lastTasks);
        model.minimize(end);


        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        System.out.println("MaxprocessTIme : "+maxProcessTime);
        System.out.println(solver.objectiveValue());

        Frame frame = new Frame(new JFrame());

        int height = 1030 / machineCount;

        float max = 0;

        for (int i = 0; i < jobCount; i++) {
            for (int j = 0; j < machineCount; j++) {
                if(max < solver.value(ends[i][j]))
                    max = solver.value(ends[i][j]);
            }
        }

        ArrayList<Color> colorList = new ArrayList<>();

        Random rand = new Random();

        for(int i = 0; i < jobCount; i++){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colorList.add(new Color(r,g,b));
        }

        ArrayList<Rectangle> rectangles = new ArrayList<>();


        for (int i = 0; i < jobCount; i++) {
            for (int j = 0; j < machineCount; j++) {

                float start = solver.value(starts[i][j]);
                float endd = solver.value(ends[i][j]);
                float widht = (endd - start) / max * 1920;
                float x = start/max*1920;
                /*System.out.println(solver.value(starts[i][j]));
                System.out.println(max);
                System.out.println(x);*/

                //frame.drawRectangle(x,j*height,widht,height,colorList.get(i));
                rectangles.add(new Rectangle((int)x,j*height,(int)widht,height,colorList.get(i)));
                //System.out.println(solver.value(starts[i][j]));
                //System.out.println(solver.value(ends[i][j]));
            }
        }

        frame.drawRectangles(rectangles);
       // frame.drawRectangle(50,50,40,40,colorList.get(1));*/


    }

}

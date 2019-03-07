package flowShop;

import com.google.ortools.sat.*;

public class Example{
        static {
            System.loadLibrary("jniortools");
        }

        public static void main(String[] args) throws Exception {
            Instance instance = new Reader("./src/main/resources/car1.txt").readInstance();
            CpModel model = new CpModel();
            int numVals = 3;
            IntVar start1 = model.newIntVar(0,100,"start1");
            IntVar start2 = model.newIntVar(0,100,"start2");
            IntVar end1 = model.newIntVar(0,100,"end1");
            IntVar end2 = model.newIntVar(0,100,"end2");
            IntervalVar interval1= model.newIntervalVar(start1, 3, end1, "interval1");
            IntervalVar interval2= model.newIntervalVar(start2, 2, end2, "interval2");
            IntervalVar[] intervalVars = {interval1,interval2};
            model.addNoOverlap(intervalVars);
            model.addLessOrEqual(end1,start2);

            IntVar end = model.newIntVar(0,100,"end");
            IntVar[] ends = {end1,end2};
            model.addMaxEquality(end,ends);
            model.minimize(end);

            CpSolver solver = new CpSolver();
            CpSolverStatus status = solver.solve(model);

            System.out.println(solver.objectiveValue());
            System.out.println(solver.value(end1));
        }
    }


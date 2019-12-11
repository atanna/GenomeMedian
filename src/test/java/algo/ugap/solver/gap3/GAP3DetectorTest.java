package algo.ugap.solver.gap3;


import algo.ugap.graph.BaseGenome;
import algo.ugap.graph.DuplicatedGenome;
import algo.ugap.graph.GAPGraph;
import algo.ugap.solver.BaseDetector;
import algo.ugap.solver.BaseSolver;
import algo.ugap.solver.SeqSolver;
import algo.ugap.solver.Solution;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GAP3DetectorTest {
    @Test
    public void search2Test() throws Exception {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();
        neighbours.add(new ArrayList<>(Arrays.asList(5, 3, 4)));
        neighbours.add(new ArrayList<>(Arrays.asList(1, 1, 2)));
        neighbours.add(new ArrayList<>(Arrays.asList(2, 2, 1)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 4, 5)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 3, 5)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 4, 3)));
        DuplicatedGenome baseGenome = new DuplicatedGenome(neighbours);
        GAPGraph graph = new GAPGraph(baseGenome);

        GAP3Detector detector = new GAP3Detector(graph, false);
        BaseDetector.Branch result = detector.search2();
        int cyclesCount = result.getCyclesCount();
        assertEquals(cyclesCount, 2);
    }

    @Test
    public void solverLoopTest() throws Exception {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();
        neighbours.add(new ArrayList<>(Arrays.asList(5, 3, 4)));
        neighbours.add(new ArrayList<>(Arrays.asList(1, 1, 2)));
        neighbours.add(new ArrayList<>(Arrays.asList(2, 2, 1)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 4, 5)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 3, 5)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 4, 3)));
        BaseGenome baseGenome = new BaseGenome(neighbours, 3);
        GAPGraph graph = new GAPGraph(baseGenome);

        GAP3State firstState = new GAP3State(graph);

        BaseSolver solver = new SeqSolver(firstState);
        solver.solve();
        Solution solution = solver.getCurrentSolution();
        assertEquals(solution.getCyclesCount(), 6);
    }

    @Test
    public void solverTest() throws Exception {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();
        neighbours.add(new ArrayList<>(Arrays.asList(1, 3, 3)));
        neighbours.add(new ArrayList<>(Arrays.asList(2, 2, 0)));
        neighbours.add(new ArrayList<>(Arrays.asList(1, 1, 3)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 0, 2)));
        BaseGenome baseGenome = new BaseGenome(neighbours, 3);
        GAPGraph graph = new GAPGraph(baseGenome);

        GAP3State firstState = new GAP3State(graph);

        BaseSolver solver = new SeqSolver(firstState);
        solver.solve();
        Solution solution = solver.getCurrentSolution();
        assertEquals(solution.getCyclesCount(), 5);
    }

    @Test
    public void solverSearch4Test() throws Exception {
        ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();
        neighbours.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 2, 3)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 1, 3)));
        neighbours.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        BaseGenome baseGenome = new BaseGenome(neighbours, 3);
        GAPGraph graph = new GAPGraph(baseGenome);

        GAP3State firstState = new GAP3State(graph);

        BaseSolver solver = new SeqSolver(firstState);
        solver.solve();
        Solution solution = solver.getCurrentSolution();
        assertEquals(solution.getCyclesCount(), 4);
    }
}

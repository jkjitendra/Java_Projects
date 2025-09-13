import java.util.*;
import java.math.BigInteger;

/**
 * Solution for MathMaze problems provided on Matiks website:
 * https://www.matiks.in/puzzle/daily-challenge?puzzleType=MathMaze
 *
 * This solver works for n x n grids (n ≤ 15), following the MathMaze rules:
 * - Start at the top-left number and end at the bottom-right number
 * - Move up/down/left/right without revisiting cells (snake rule)
 * - Alternate number → operator → number → ...
 * - Immediately evaluate as you go
 * - Checks exact integer or terminating decimal targets
 */
public class MazeSolver {

    // --------- Tuning knobs ----------
    static final int MAX_SOLUTIONS = 20;     // stop after this many solutions
    static final long TIME_LIMIT_MS = 5000;  // stop after this many milliseconds
    // ---------------------------------

    static long startTime;
    static int N;
    static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}}; // down, up, right, left

    public static void main(String[] args) {
        // any n x n (n <= 15).
        String[][] grid = {
                {"2", "/", "1", "/", "2", "+"},
                {"/", "1", "-", "2", "-", "6"},
                {"5", "+", "9", "-", "0", "+"},
                {"-", "1", "*", "3", "+", "5"},
                {"8", "*", "9", "*", "3", "*"},
                {"*", "8", "/", "3", "/", "3"}
        };
        
        // Target can be integer or terminating decimal
        String targetStr = "1";  // e.g., "8", "8.5", "8.012"
        Rational target = Rational.parse(targetStr);

        N = grid.length;
        if (N == 0 || grid[0].length != N) {
            System.out.println("Grid must be square n x n (n <= 15).");
            return;
        }
        if (N > 15) {
            System.out.println("n must be <= 15.");
            return;
        }
        if (!isNumber(grid[0][0])) {
            System.out.println("Top-left cell must be a number.");
            return;
        }
        if (!isNumber(grid[N-1][N-1])) {
            System.out.println("Bottom-right cell must be a number.");
            return;
        }

        List<List<int[]>> solutions = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        List<int[]> path = new ArrayList<>();

        // init
        visited[0][0] = true;
        path.add(new int[]{0,0});
        Rational startVal = Rational.ofInt(Integer.parseInt(grid[0][0]));

        startTime = System.currentTimeMillis();
        dfs(grid, 0, 0, startVal, null, true, visited, path, solutions, target);

        // Print solutions
        System.out.println("Solutions found: " + solutions.size());
        for (List<int[]> sol : solutions) {
            String expr = buildExpression(grid, sol);
            System.out.println("Expression: " + expr);
            System.out.println("Path: " + formatPath(sol));
            System.out.println("Steps: " + formatSteps(grid, sol));
            System.out.println("---");
        }

        if (System.currentTimeMillis() - startTime >= TIME_LIMIT_MS) {
            System.out.println("(Stopped due to time limit)");
        } else if (solutions.size() >= MAX_SOLUTIONS) {
            System.out.println("(Stopped after reaching max solutions)");
        }

        if (!solutions.isEmpty()) {
            List<int[]> shortest = solutions.get(0);
            for (List<int[]> sol : solutions) {
                if (sol.size() < shortest.size()) shortest = sol;
            }
            System.out.println("Shortest path among the found solutions:");
            System.out.println("Expression: " + buildExpression(grid, shortest));
            System.out.println("Path: " + formatPath(shortest));
            System.out.println("Steps: " + formatSteps(grid, shortest));
        } else {
            System.out.println("No shortest path (no solutions found).");
        }
    }

    /** DFS search with snake rule and immediate evaluation */
    static void dfs(String[][] grid, int r, int c,
                    Rational currentValue, String pendingOp,
                    boolean expectOperator,
                    boolean[][] visited, List<int[]> path,
                    List<List<int[]>> solutions, Rational target) {

        // Time & solution guards
        if (solutions.size() >= MAX_SOLUTIONS) return;
        if (System.currentTimeMillis() - startTime >= TIME_LIMIT_MS) return;

        // Base case: at end (N-1,N-1), which must be a number.
        // After consuming a number we "expect operator" next, so expectOperator should be true here.
        if (r == N - 1 && c == N - 1 && expectOperator && currentValue.equals(target)) {
            solutions.add(new ArrayList<>(path));
            return;
        }

        // Explore 4 directions
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (!inBounds(nr, nc) || visited[nr][nc]) continue;

            String cell = grid[nr][nc];

            if (expectOperator) {
                // must be operator
                if (isOperator(cell)) {
                    visited[nr][nc] = true;
                    path.add(new int[]{nr,nc});
                    dfs(grid, nr, nc, currentValue, cell,
                            false, visited, path, solutions, target);
                    path.remove(path.size() - 1);
                    visited[nr][nc] = false;
                }
            } else {
                // must be operand
                if (isNumber(cell)) {
                    int valInt = Integer.parseInt(cell);
                    Rational val = Rational.ofInt(valInt);

                    Rational newValue;
                    if (pendingOp != null) {
                        try {
                            newValue = apply(currentValue, pendingOp, val);
                        } catch (ArithmeticException ex) {
                            // e.g., division by zero
                            continue;
                        }
                    } else {
                        newValue = val;
                    }

                    visited[nr][nc] = true;
                    path.add(new int[]{nr,nc});
                    dfs(grid, nr, nc, newValue, null,
                            true, visited, path, solutions, target);
                    path.remove(path.size() - 1);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    // -------- Helpers --------

    static boolean inBounds(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static boolean isNumber(String s) {
        return s.length() > 0 && Character.isDigit(s.charAt(0));
    }

    static boolean isOperator(String s) {
        return s.length() == 1 && "+-*/".indexOf(s.charAt(0)) >= 0;
    }

    static Rational apply(Rational a, String op, Rational b) {
        switch (op) {
            case "+": return a.add(b);
            case "-": return a.sub(b);
            case "*": return a.mul(b);
            case "/": return a.div(b); // exact division; throws on /0
            default:  throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    static String buildExpression(String[][] grid, List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : path) sb.append(grid[p[0]][p[1]]);
        return sb.toString();
    }

    static String formatPath(List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : path) sb.append("[").append(p[0]).append(",").append(p[1]).append("] ");
        return sb.toString().trim();
    }

    /** Pretty prints intermediate evaluation, e.g.: 3-8=-5 -> -5+8=3 -> ... */
    static String formatSteps(String[][] grid, List<int[]> path) {
        StringBuilder sb = new StringBuilder();
        Rational cur = Rational.ofInt(Integer.parseInt(grid[0][0]));
        sb.append(cur);
        String op = null;
        for (int i = 1; i < path.size(); i++) {
            String cell = grid[path.get(i)[0]][path.get(i)[1]];
            if (isOperator(cell)) {
                op = cell;
            } else {
                Rational rhs = Rational.ofInt(Integer.parseInt(cell));
                Rational next = apply(cur, op, rhs);
                sb.append(op).append(rhs).append("=").append(next);
                if (i < path.size() - 1) sb.append(" -> ");
                cur = next;
                op = null;
            }
        }
        return sb.toString();
    }

    // -------- Exact Rational Type (BigInteger) --------
    static final class Rational {
        final BigInteger num; // reduced; sign on numerator
        final BigInteger den; // positive

        Rational(BigInteger num, BigInteger den) {
            if (den.signum() == 0) throw new ArithmeticException("Zero denominator");
            if (den.signum() < 0) { num = num.negate(); den = den.negate(); }
            BigInteger g = num.gcd(den);
            if (!g.equals(BigInteger.ONE)) {
                num = num.divide(g); den = den.divide(g);
            }
            this.num = num; this.den = den;
        }

        static Rational ofInt(int v) {
            return new Rational(BigInteger.valueOf(v), BigInteger.ONE);
        }

        /** Parses integer or terminating decimal strings exactly. */
        static Rational parse(String s) {
            s = s.trim();
            if ( s.contains(".") ) {
                boolean neg = s.startsWith("-");
                if (neg) s = s.substring(1);
                String[] parts = s.split("\\.", -1);
                String whole = parts[0].isEmpty() ? "0" : parts[0];
                String frac  = parts.length > 1 ? parts[1] : "";
                String digits = whole + frac;
                BigInteger n = new BigInteger(digits.isEmpty() ? "0" : digits);
                BigInteger d = BigInteger.ONE;
                if (!frac.isEmpty()) d = BigInteger.TEN.pow(frac.length());
                if (neg) n = n.negate();
                return new Rational(n, d);
            } else {
                return new Rational(new BigInteger(s), BigInteger.ONE);
            }
        }

        Rational add(Rational o) {
            return new Rational(this.num.multiply(o.den).add(o.num.multiply(this.den)),
                    this.den.multiply(o.den));
        }
        Rational sub(Rational o) {
            return new Rational(this.num.multiply(o.den).subtract(o.num.multiply(this.den)),
                    this.den.multiply(o.den));
        }
        Rational mul(Rational o) {
            return new Rational(this.num.multiply(o.num), this.den.multiply(o.den));
        }
        Rational div(Rational o) {
            if (o.num.signum() == 0) throw new ArithmeticException("Division by zero");
            return new Rational(this.num.multiply(o.den), this.den.multiply(o.num));
        }

        @Override public boolean equals(Object obj) {
            if (!(obj instanceof Rational)) return false;
            Rational o = (Rational) obj;
            return this.num.equals(o.num) && this.den.equals(o.den);
        }
        @Override public int hashCode() { return Objects.hash(num, den); }
        @Override public String toString() {
            return den.equals(BigInteger.ONE) ? num.toString() : (num + "/" + den);
        }
    }
}

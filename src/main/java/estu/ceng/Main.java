package estu.ceng;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;


public class Main {
    public static void main(String[] args) {
        CmdLine cmdLine = parseCmdLine(args);
        int numThread = cmdLine.getNumThread();
        int numBins = cmdLine.getNumBins();
        int[] balls = new int[numBins];

        Thread[] threads = createThreads(numThread, numBins, balls);
        joinThreads(threads);
        printResults(numThread, getSum(balls), balls);
    }

    public static CmdLine parseCmdLine(String[] args) {
        CmdLine cmdLine = new CmdLine();
        CmdLineParser parser = new CmdLineParser(cmdLine);

        try {
            parser.parseArgument(args);
            if (cmdLine.getNumThread() <= 1 || cmdLine.getNumBins() <= 1) {
                throw new IllegalArgumentException("Number of threads and bins must be greater than 1.");
            }
        } catch (CmdLineException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return cmdLine;
    }

    public static Thread[] createThreads(int numThread, int numBins, int[] balls) {
        Thread[] threads = new Thread[numThread];

        for (int i = 0; i < numThread; i++) {
            threads[i] = new Thread(new GaltonBoard(balls, numBins));
            threads[i].start();
        }

        return threads;
    }

    public static void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSum(int[] cells) {
        int sum = 0;
        for (int i = 0; i < cells.length; i++) {
            int balls = cells[i];
            sum += cells[i];
            System.out.printf("%d\t%d\n", i , balls);
        }
        return sum;
    }

    public static void printResults(int numThread, int sum, int[] cells) {
        System.out.println("Number of requested thread: " + numThread);
        System.out.println("Sum of bin values: " + sum);

        String message = numThread == sum ? "Nice work! Both of them are equal." : "They are not equal.";
        System.out.println(message);
    }


}
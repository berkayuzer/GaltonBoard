package estu.ceng;

import org.kohsuke.args4j.Option;

public class CmdLine {
    @Option(name="-numThread", usage = "Number of threads", required = true)
    private int numThread;

    @Option(name ="-numBins", usage = "Number of bins", required = true)
    private int numBins;

    public int getNumThread() {
        return numThread;
    }

    public int getNumBins() {
        return numBins;
    }
}


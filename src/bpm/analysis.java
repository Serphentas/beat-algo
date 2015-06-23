package bpm;

import java.util.HashMap;
import java.util.Map;

public class analysis {
    private Map<Integer, Integer> kicks = new HashMap<>();

    /**
     * Kick analysis algorithm
     * 
     * Seeks kicks in an audio track by comparing chunks of data in sequence.
     * 
     * If a given chunk's mean exceeds the previous one's by more than the
     * specified delta, then the current chunk is considered to be a kick.
     * 
     * The 'step' parameter defines how large a chunk is.
     * 
     * @param b
     * @param delta
     *            Delta between to means for
     * @param step
     *            Data chunk width (usually n*1024, n=1,...,4)
     * 
     */
    public analysis(Map<Integer, Integer> chunks, int delta) {
        // setting temp variables at 0
        int prevMean = 0;
        chunks.
        // 16b/2B per sample, two channels and we need $step samples
        // so we get $step*2*2=$step*4 bytes at a time
        for (int i = 0; i < b.length; i += 4 * step) {
            int currMean = 0;

            int j = i;
            while (j < (j + step - 1)) {
                currMean += b[j];
                j++;
            }

            // getting mean over 4096 samples
            currMean *= 1 / step;

            // seeking kick except for first chunk
            if (i != 0) {

                if (currMean > prevMean + delta) {
                    // case when current sample's mean is higher than previous
                    // one's
                    kicks.put(b[i], i);

                } else {
                    // case when previous sample's mean is higher than current
                    // one's
                    kicks.put(b[i - step], i - step);
                }
            }
            prevMean = currMean;
        }
    }

    public Map<Integer, Integer> getKicks() {
        return kicks;
    }
}

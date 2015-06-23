package bpm;

import java.util.HashMap;
import java.util.Map;

public class chunking {
    private Map<Integer, Integer> chunks = new HashMap<>();

    public chunking(int[] bytes, int step, int k) {
        step *= k;
        int currMean = 0, length = bytes.length, n = Math
                .floorDiv(length, step);

        for (int i = 0; i < n; i++) {
            int j = i * step;
            while (j < i + step) {
                currMean += bytes[j];
                j++;
            }
            currMean *= 1 / step;
            chunks.put(i, currMean);
            currMean = 0;
        }

        int lastPos = n * step, remainder = length - lastPos;

        for (int x = remainder; x < length; x++) {
            currMean += bytes[x];
        }

        currMean *= 1 / (remainder);
        chunks.put(lastPos, currMean);
    }

    /**
     * Chunk getter
     * 
     * Width is defined by 'step' parameter in chucking method
     * 
     * @return Data chunks
     */
    public Map<Integer, Integer> chunks() {
        return chunks;
    }
}

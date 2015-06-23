package bpm;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class bpm {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        System.out.println("*** Working with file " + "'" + args[0] + "' ***");
        long startTime = System.nanoTime();
        long time = System.nanoTime();

        // preparing audio file
        transcoding t = new transcoding(args[0]);
        System.out.println("■ Done transcoding: " + (System.nanoTime() - time)
                / 1000000 + " ms");

        File f = t.getAudioFile();
        
        // setting audio parameters
        final int channels = 2, sampleRate = 44100, bitsPerSample = 16;
        long fileBytes = f.length() - 44;
        double fileBits = fileBytes * 8;
        double fileSeconds = fileBits / channels / sampleRate / bitsPerSample;

        System.out.println("□ Track duration:   "
                + (int) Math.floor(fileSeconds / 3600) + "h "
                + (int) Math.floor(fileSeconds / 60) + "m " + fileSeconds % 60
                + "s");

        time = System.nanoTime();
        // loading file bytes
        loading l = new loading(f, fileBytes);
        int[] content = l.content();
        System.out.println("■ Done loading:     " + (System.nanoTime() - time)
                / 1000000 + " ms");

        time = System.nanoTime();
        // chunking bytes
        chunking k = new chunking(content, 1024, 1);
        Map<Integer, Integer> chunks = k.chunks();
        System.out.println("■ Done chunking:    " + (System.nanoTime() - time)
                / 1000000 + " ms");

        time = System.nanoTime();
        // analyzing song for kicks
        analysis a = new analysis(chunks, 30);
        System.out.println("■ Done analyzing: " + (System.nanoTime() - time)
                / 1000000 + " ms");

        // long time = (System.nanoTime() - intermediateTime) / 1000000;
        // double speed = (fileBytes / time);
        // System.out.println(time + " ms");
        // System.out.println("MiB/s : " + speed*1000/Math.pow(1024, 2));
        
        System.out.println("■ Job done: " + (System.nanoTime() - startTime)
                / 1000000 + " ms");
    }
}

package bpm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class loading {
    private int[] content;

    /**
     * File loading
     * 
     * Loads a WAV file's bytes into an array (as int) in sequence
     * 
     * @param f
     *            Audio file
     * @param size
     *            Audio data size
     * @throws FileNotFoundException
     * @throws IOException
     */
    public loading(File f, long size) throws FileNotFoundException, IOException {
        content = new int[(int) size];
        try (FileInputStream stream = new FileInputStream(f)) {
            MappedByteBuffer buffer = stream.getChannel().map(
                    MapMode.READ_ONLY, 43, size);
            for (int i = 0; i < size; ++i) {
                content[i] = buffer.get(i);
            }
        }
    }

    /**
     * File byte getter
     * 
     * @return A WAV file's bytes in sequence and as int, in an array
     */
    public int[] content() {
        return content;
    }
}

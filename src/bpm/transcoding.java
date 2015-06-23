package bpm;

import java.io.File;
import java.io.IOException;

public class transcoding {
    private File transcodedAudio;

    /**
     * Transcoding method
     * 
     * From MP3/FLAC/OGG to WAV 44100 KHz, stereo
     * 
     * @param name
     *            Path to audio file
     * @throws IOException
     * @throws InterruptedException
     */
    public transcoding(String name) throws IOException, InterruptedException {
        if (name.endsWith(".wav")) {
            // case when the file is already in WAV format
            transcodedAudio = new File(name);
        } else if (name.endsWith(".mp3") || name.endsWith(".flac") || name.endsWith(".ogg")) {
            // gets file name without extension
            String trimmedName = name.substring(0, name.indexOf("."));
            // transcoding command
            String command = "ffmpeg -y -i " + name + " -ar 44100 -ac 2 "
                    + trimmedName + ".wav";

            // converts the audio file into WAV 44100 KHz, stereo
            // OVERWRITES ANY EXISTING FILE WITH THE SAME NAME
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            transcodedAudio = new File(trimmedName + ".wav");
        }
    }

    /**
     * WAV file getter
     * 
     * @return Transcoded audio file (WAV 44100 KHz, stereo)
     */
    public File getAudioFile() {
        return transcodedAudio;
    }
}
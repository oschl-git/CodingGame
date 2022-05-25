package gamelogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class handles loading levels from txt files.
 */
public class LevelLoader {
    final public static String LEVEL_PATH = "levels/X.txt";

    /**
     * This method loads a specific level.
     * @param level desired level number
     * @return 2D array of the level
     */
    public int[][] readLevelFile(int level) {
        String path = LEVEL_PATH.replace("X", String.valueOf(level));

        int[][] output = new int[15][15];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                for (int j = 0; j < output[i].length; j++) {
                    output[i][j] = Character.getNumericValue(line.charAt(j));
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}

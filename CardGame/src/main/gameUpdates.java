package CardGame.src.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class gameUpdates {
    public static boolean updating = true;
    public static boolean printing = true;

    /**
     * Writes the player's action to a file.
     * 
     * @param playerName The name of the player.
     * @param fileInput  The input to be written to the file.
     */
    public void writePlayerAction(String playerName, String fileInput) {
        if (updating) {
            StringBuilder fileName = new StringBuilder(playerName);
            fileName.append("_output.txt");
            fileInput += "\n";
            try (FileWriter writer = new FileWriter(new File(fileName.toString()), true)) {
                writer.append(fileInput);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }
    }

    /**
     * Deletes old output logs from the directory.
     * This method walks through the directory, identifies regular files that end with "_output.txt",
     * and deletes them.
     *
     * @throws IOException if an error occurs while preparing the directory
     */
    public void prepareDirectory() {
    try {
        // Delete old output logs
        Files.walk(Paths.get(""))
            .filter(Files::isRegularFile)
            .filter(path -> path.toString().endsWith("_output.txt"))
            .map(Path::toFile)
            .forEach(File::delete);
    } catch (IOException e) {
        System.out.println("An error occurred while preparing the directory: " + e.getMessage());
    }
}


}





import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileHandler {

    // Method to read all lines from a text file
    public static List<String> readFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }



    // Method to read a specific line from a text file
    public static String readSpecificLine(String filePath, int lineNumber) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            return lines.get(lineNumber - 1); // lineNumber is 1-based
        } else {
            throw new IndexOutOfBoundsException("Line number out of range");
        }
    }

    // Method to write a list of strings to a text file
    public static void writeToFile(List<String> lines, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, lines);
    }

    public static void writeToSpecificLine(String filePath, int lineNumber, String content) throws IOException {
        List<String> lines = readFromFile(filePath);
        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            lines.set(lineNumber - 1, content);
            writeToFile(lines, filePath);
            System.out.println("Successfully wrote to line " + lineNumber + " in " + filePath);
        } else {
            System.err.println("Line number out of bounds.");
        }
    }

    public static void main(String[] args) {
        // Example usage
        String inputFilePath = "C:\\Users\\ryand\\IdeaProjects\\Tetris Remastered\\HighScore.Txt";
        String outputFilePath = "C:\\Users\\ryand\\IdeaProjects\\Tetris Remastered\\HighScore.Txt";

        try {
            // Read from input file
            List<String> lines = readFromFile(inputFilePath);
            System.out.println("File contents:");
            for (String line : lines) {
                System.out.println(line);
            }


            // Write to output file
            writeToFile(lines, outputFilePath);
            System.out.println("Contents written to " + outputFilePath);

            System.out.println("CAAAA " + readSpecificLine(inputFilePath, 1));


        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }
}

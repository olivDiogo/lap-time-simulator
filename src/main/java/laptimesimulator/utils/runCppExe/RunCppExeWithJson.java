package laptimesimulator.utils.runCppExe;

import java.io.File;
import java.io.IOException;

public class RunCppExeWithJson {

    public static void main(String[] args) {
        String cppExePath = "D:\\LapTimeApp\\lapTimeSimulator\\cpp\\cmake-build-debug-visual-studio\\cpp.exe";
        String jsonDataPath = "D:\\LapTimeApp\\lapTimeSimulator\\cpp\\src\\simulationData.json";
        String workingDirectoryPath = "D:\\LapTimeApp\\lapTimeSimulator\\cpp\\cmake-build-debug-visual-studio";

        runCppExecutable(cppExePath, jsonDataPath, workingDirectoryPath);
    }

    public static void runCppExecutable(String exePath, String jsonDataPath, String workingDirectoryPath) {
        ProcessBuilder processBuilder = new ProcessBuilder(exePath, jsonDataPath);

        // Set the working directory
        File workingDirectory = new File(workingDirectoryPath);
        processBuilder.directory(workingDirectory);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Cpp executable completed successfully.");
            } else {
                System.err.println("Cpp executable returned error code: " + exitCode);

                // Read any errors from the error stream
                readErrors(process);

                // Read any output from the output stream
                readOutput(process);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readErrors(Process process) {
        try {
            java.io.InputStream errorStream = process.getErrorStream();
            java.io.InputStreamReader isr = new java.io.InputStreamReader(errorStream);
            java.io.BufferedReader br = new java.io.BufferedReader(isr);
            String line;
            System.out.println("<ERROR>");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("</ERROR>");
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }

    private static void readOutput(Process process) {
        try {
            java.io.InputStream errorStream = process.getInputStream();
            java.io.InputStreamReader isr = new java.io.InputStreamReader(errorStream);
            java.io.BufferedReader br = new java.io.BufferedReader(isr);
            String line;
            System.out.println("<OUTPUT>");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("</OUTPUT>");
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}

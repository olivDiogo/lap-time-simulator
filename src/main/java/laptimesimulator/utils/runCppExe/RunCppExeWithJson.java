package laptimesimulator.utils.runCppExe;

import java.io.File;
import java.io.IOException;

public class RunCppExeWithJson {

    public static void main(String[] args) {
        String cppExePath = "cpp\\cmake-build-debug-visual-studio\\cpp.exe";
        String jsonDataPath = "simulationData.json";
        String workingDirectoryPath = System.getProperty("user.dir");


        runCppExecutable(cppExePath, jsonDataPath, workingDirectoryPath, workingDirectoryPath);
    }

    public static void runCppExecutable(String cppExePath, String jsonDataPath, String workingDirectoryPath, String outputDirectoryPath) {
        File workingDirectory = new File(workingDirectoryPath);
        File outputDirectory = new File(outputDirectoryPath);
        File exeFile = new File(workingDirectory, cppExePath);
        File jsonDataFile = new File(workingDirectory, jsonDataPath);

        if(!jsonDataFile.exists()) {
            System.err.println("JSON data file does not exist.");
            return;
        }

        ProcessBuilder processBuilder = new ProcessBuilder(exeFile.getAbsolutePath(), jsonDataFile.getAbsolutePath() /*, outputDirectory.getAbsolutePath() */);
        processBuilder.directory(new File("cpp"));

        try {
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                readOutput(process);

            } else {
                System.err.println("Cpp executable returned error code: " + exitCode);
                readErrors(process);

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

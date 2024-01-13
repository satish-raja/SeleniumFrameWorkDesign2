package tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DockerSetUp {

	@BeforeTest
    void startDockergrid() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c","start", "start_dockergrid.bat");
        processBuilder.redirectErrorStream(true); // Redirect error stream to output stream

        try {
        	Process startProcess = processBuilder.start();
             Thread.sleep(150000);// Wait for the process to finish
             } catch (IOException e) {
            e.printStackTrace();
             }
    }
		
    @AfterTest
    void stopDockergrid() throws IOException, InterruptedException {
        // Start the stop_dockergrid.bat in a new process
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "stop_dockergrid.bat");
        processBuilder.redirectErrorStream(true); // Redirect error stream to output stream

        try {
            Process stopProcess = processBuilder.start();
            stopProcess.waitFor(); // Wait for the process to finish
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Sleep for a short duration if needed
//        Thread.sleep(30000);

        // Terminate any remaining cmd.exe processes
        try {
            Process killProcess = new ProcessBuilder("taskkill", "/f", "/im", "cmd.exe").start();
            killProcess.waitFor(); // Wait for the process to finish
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
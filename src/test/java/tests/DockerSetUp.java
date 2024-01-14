package tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DockerSetUp {

    @BeforeTest
    void startDockergrid() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "start_dockergrid.bat");
        processBuilder.redirectErrorStream(true);

        try  {
        	Process startProcess = processBuilder.start() ;
            Thread.sleep(120000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Docker SetUp Process Completed");
    }

    @AfterTest
    void stopDockergrid() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "stop_dockergrid.bat");
        processBuilder.redirectErrorStream(true);

        try  {
        	Process stopProcess = processBuilder.start();
            stopProcess.waitFor(); // Wait for the process to finish
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(15000); // Sleep for a short duration if needed

//        try {
//            Process killProcess = new ProcessBuilder("taskkill", "/f", "/im", "cmd.exe").start();
//            killProcess.waitFor(); // Wait for the process to finish
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

//        Thread.sleep(30000);
        System.out.println("Docker Process Closed");
    }
}

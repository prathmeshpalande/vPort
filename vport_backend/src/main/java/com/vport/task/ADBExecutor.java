package com.vport.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADBExecutor {

    public static void executeADBCommand(String command) throws IOException {
        // Executing the command
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        // Getting the results
        powerShellProcess.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
        }
        stdout.close();
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
        }
        stderr.close();

    }
}

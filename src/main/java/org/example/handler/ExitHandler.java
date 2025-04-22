package org.example.handler;

import java.util.Scanner;

public class ExitHandler extends Handler {
    @Override
    public void handle(Scanner scanner) {
        System.exit(0);
    }
}

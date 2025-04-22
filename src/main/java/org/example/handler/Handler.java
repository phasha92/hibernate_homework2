package org.example.handler;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class Handler implements HandleStrategy {

    protected final Logger logger = Logger.getLogger(Handler.class.getName());

    protected int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            logger.warning(ENTER_VALID_NUMBER);
            scanner.next();
        }
        return scanner.nextInt();
    }
}

package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.file.FileHandler;
import cleancode.studycafe.tobe.file.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.pass.Pass;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        FileHandler fileHandler = new StudyCafeFileHandler();
        Pass pass = new Pass(fileHandler);

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, pass);
        studyCafePassMachine.run();
    }

}

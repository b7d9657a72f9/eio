package b7d9657a72f9.beta2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Beta2 {
    public static void main(String[] args) {
        try (Scanner SourceReader = new Scanner(args[0])) {
            while (SourceReader.hasNextLine()) {
                System.out.println(SourceReader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occured. :(");
        }
    }
}
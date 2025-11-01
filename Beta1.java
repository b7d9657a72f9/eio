import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Beta1 {
    static void interpret(String SourceFile) {
        // Startup message
        System.out.println("EIO beta 1");
        System.out.println("Running file: " + SourceFile + ".");
        System.out.print("\n");
        
        // Syntax characters

        // If you are using (, [, or {, you must surrond it in \\Q and \\E

        final String StartBlock = "\\Q(\\E";
        final String EndBlock = ")";
        final String ParamSplit = ",";

        // Example function for getting function params:
        // Params = SplitLine[1].split(ParamSplit);

        // Then access it as Params[1] 


        File InputFile = new File(SourceFile);

        ArrayList<String> VariableNameList = new ArrayList<>();
        ArrayList<String> VariableValueList = new ArrayList<>();

        try (Scanner InputReader = new Scanner(InputFile)) {
            while (InputReader.hasNextLine()) {
                String[] SplitLine = InputReader.nextLine().replace(EndBlock, "").split(StartBlock);
                String[] Params;

                for (int i = 0; i < VariableNameList.size(); i++) {
                    if (SplitLine[0].contains(VariableNameList.get(i))) {
                        VariableValueList.set(i, SplitLine[1]);
                    }
                }

                if (SplitLine[0].contains("print")) {
                    for (int i = 0; i < VariableNameList.size(); i++) {
                        if (SplitLine[1].contains("@" + VariableNameList.get(i))) {
                            SplitLine[1] = SplitLine[1].replaceAll("@" + VariableNameList.get(i), VariableValueList.get(i));     
                        }
                    }

                    System.out.println(SplitLine[1]);
                }

                if (SplitLine[0].contains("declare")) {
                    VariableNameList.add(SplitLine[1]);
                    VariableValueList.add("null");
                }


                if (SplitLine[0].contains("read")) {
                    Scanner UserInput = new Scanner(System.in);

                    for (int i = 0; i < VariableNameList.size(); i++) {
                        if (VariableNameList.get(i).equals(SplitLine[1])) {
                            VariableValueList.set(i, UserInput.nextLine());
                        }
                    }
                }

                if (SplitLine[0].contains("error")) {
                    System.err.println(SplitLine[1]);
                    System.exit(0);
                }

                if (SplitLine[0].contains("func")) {
                    interpret(SplitLine[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist.");
        }
    }

    public static void main(String[] args) {
        interpret(args[0]);
    }
}
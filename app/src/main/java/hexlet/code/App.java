package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "getDiff",
        mixinStandardHelpOptions = true,
        version = "getDiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    private final Integer errorCode = 1;

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String firstFilePath;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String secondFilePath;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]",
            paramLabel = "format")
    private String format;

    @Override
    public final Integer call() {
        try {
            String result = Differ.generate(firstFilePath, secondFilePath, format);
            System.out.println(result);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return errorCode;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

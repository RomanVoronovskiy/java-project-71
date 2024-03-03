package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static String readFile(String pathToFile) throws Exception {
        String content;
        Path pathFile;
        File file = new File(pathToFile);
        String absolutePath = file.getAbsolutePath();
        pathFile = Path.of(absolutePath);
        content = Files.readString(pathFile);
        return content;
    }

    public static String getDataFormat(String pathToFile) {
        int index = pathToFile.lastIndexOf('.');
        return index > 0 ? pathToFile.substring(index + 1) : "";
    }
}

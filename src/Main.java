import java.io.File;
import java.util.Optional;

public class Main {

    public static long getSize(File dir) {
        long length = 0;
        try {
            if (dir==null) {
                throw new NullPointerException("Input directory is null");
            }
            Optional<File[]> files = Optional.ofNullable(dir.listFiles()); //we'll get empty object instead of NPE
            files.orElseThrow(()->
                    new NullPointerException(String.format(String.format("Can't get directories from %s", dir.getName()))));
            //long length = 0;
            for (File file : files.orElseThrow()) {
                if (file.isFile()) length += file.length();
                else length += getSize(file);
            }
        }
        catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return length;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        long byteFolderSize = getSize(new File(args[0]));
        long kbFolderSize = byteFolderSize/1024;
        long mbFolderSize = kbFolderSize/1024;
        double gbFolderSize = (double)mbFolderSize/1024.0;
        System.out.printf("%s --> %d bytes / %d Kb / %d Mb / %f Gb.\n", args[0], byteFolderSize, kbFolderSize, mbFolderSize, gbFolderSize);
    }


}

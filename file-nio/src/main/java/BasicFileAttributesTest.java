import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * @author Mikhail Zaitsev
 */
public class BasicFileAttributesTest {

  public static void main(String[] args) throws IOException {
    Path file = Paths.get("/home/zaitsev");

    changeLastModifiedTime(file);

    BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

    System.out.println("creationTime: " + attr.creationTime());
    System.out.println("lastAccessTime: " + attr.lastAccessTime());
    System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

    System.out.println("isDirectory: " + attr.isDirectory());
    System.out.println("isOther: " + attr.isOther());
    System.out.println("isRegularFile: " + attr.isRegularFile());
    System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
    System.out.println("size: " + attr.size());
    System.out.println("fileKey: " + attr.fileKey());
  }

  static void changeLastModifiedTime(Path path) throws IOException {
    long currentTime = System.currentTimeMillis();
    FileTime fileTime = FileTime.fromMillis(currentTime);
    Files.setLastModifiedTime(path, fileTime);
  }

}

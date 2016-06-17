import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * @author Mikhail Zaitsev
 */
public class PosixFileAttributesTest {

  public static void main(String[] args) throws IOException {
    Path file = Paths.get("/home/zaitsev/.profile");
    PosixFileAttributes attr =
            Files.readAttributes(file, PosixFileAttributes.class);
    System.out.format("%s %s %s%n",
            attr.owner().getName(),
            attr.group().getName(),
            PosixFilePermissions.toString(attr.permissions()));
  }

}

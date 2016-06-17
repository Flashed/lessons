import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mikhail Zaitsev
 */
public class PathExmpl {

  public static void main(String[] args) {
    Path path = Paths.get("one", "/./two", "three.log/");
    System.out.format("toString(): %s%n", path);
    System.out.format("getRoot(): %s%n", path.getRoot());
    System.out.format("getFileName: %s%n", path.getFileName());
    System.out.format("getNameCount: %d%n", path.getNameCount());
    printPathNames(path);
    System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
    System.out.format("getParent: %s%n", path.getParent());
    System.out.format("normalize(): %s%n", path.normalize());
    System.out.format("toUri(): %s%n", path.toUri());
    System.out.format("toAbsolutePath(): %s%n", path.toAbsolutePath());
    printRealPath(Paths.get(""));
    System.out.format("resolve(): %s%n", path.resolve("four"));
    printRelativize(Paths.get("one/two"), Paths.get("one"));
    System.out.format("equals(): %s%n", Paths.get("one/two").equals(Paths.get("one/two/")));
  }

  static void printRelativize(Path path1, Path path2) {
    System.out.format("relativize(): %s%n", path1.relativize(path2));
  }

  static void printRealPath(Path path) {
    try {
      System.out.format("toRealPath(): %s%n", path.toRealPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void printPathNames(Path path) {
    for (int i = 0; i < path.getNameCount(); i++ ) {
      System.out.format("getName(%d): %s%n", i, path.getName(i));
    }
  }

}

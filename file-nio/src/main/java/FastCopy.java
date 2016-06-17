import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author Mikhail Zaitsev
 */
public class FastCopy implements CopyTaskListener {



  public static void main(String[] args) throws IOException {
    new FastCopy();
  }

  public FastCopy() throws IOException {
    Path path = Paths.get("res/out.txt");
    Path path2 = Paths.get("res/out1.txt");


    FileChannel channel1 = null;
    FileChannel channel2 = null;
    try {
      channel1 = FileChannel.open(path, StandardOpenOption.READ);
      channel2 = FileChannel.open(path2, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
      new CopyTask(channel1, channel2, Files.size(path), this).run();
    } finally {
      if (channel1 != null) {
        channel1.close();
      }
      if (channel2 != null) {
        channel2.close();
      }
    }
  }

  @Override
  public void onSuccess(CopyTask task) {

  }

  @Override
  public void onError(Exception e, CopyTask task) {
    e.printStackTrace();
  }






}

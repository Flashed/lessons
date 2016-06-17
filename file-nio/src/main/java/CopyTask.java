import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

class CopyTask implements Runnable {

  private FileChannel inputChannel;
  private FileChannel outputChannel;
  private CopyTaskListener listener;
  private long bytesToRead;

  CopyTask(FileChannel inputChannel, FileChannel outputChannel, long bytesToRead, CopyTaskListener listener) {
    this.inputChannel = inputChannel;
    this.outputChannel = outputChannel;
    this.listener = listener;
    this.bytesToRead = bytesToRead;
  }

  @Override
  public void run() {
    ByteBuffer buffer = ByteBuffer.allocate(8912);
    int readOnce = 0;
    long readTotal = 0;
    try {
      while (true) {
        readOnce = inputChannel.read(buffer);
        if (readOnce == -1) {
          if (readTotal > bytesToRead) {
            listener.onError(new IOException("Abort reading channel"), this);
          }
          break;
        }
        readTotal += readOnce;
        if (readTotal > bytesToRead) {
          readOnce =  (int)( readOnce - (readTotal - bytesToRead));
          buffer.rewind();
          buffer.limit(readOnce);
          outputChannel.write(buffer);
          break;
        }
        buffer.rewind();
        buffer.limit(readOnce);
        outputChannel.write(buffer);
        buffer.flip();
      }
      listener.onSuccess(this);
    } catch (IOException e) {
      listener.onError(e, this);
    }
  }

  public FileChannel getInputChannel() {
    return inputChannel;
  }

  public FileChannel getOutputChannel() {
    return outputChannel;
  }


}
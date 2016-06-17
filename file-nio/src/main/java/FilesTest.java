import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Zaitsev
 */
public class FilesTest {

  public static void main(String[] args) throws IOException {
    List<Data> dataList = fillDataList();
    writeFile("res/out.csv", dataList);
  }

  static List<Data> fillDataList(){
    List<Data> dataList = new ArrayList<Data>();
    dataList.add(new Data(0, "Name1", "Description1"));
    dataList.add(new Data(1, "Name2", "Description2"));
    dataList.add(new Data(2, "Name3", "Description3"));
    dataList.add(new Data(3, "Name4", "Description4"));
    dataList.add(new Data(4, "Name5", "Description5"));
    dataList.add(new Data(5, "Name6", "Description6"));
    dataList.add(new Data(6, "Name7", "Description7"));
    return dataList;
  }

  static void writeFile(String filename, List<Data> dataList) throws IOException{
    PrintWriter fileWriter = null;
    try {
      fileWriter = new PrintWriter(filename);
      for (Data data: dataList) {
        fileWriter.format("%d\u0002%s\u0002%s%n", data.getId(), data.getName(), data.getDescription());
      }
      fileWriter.flush();
    } finally {
      if (fileWriter != null) {
        fileWriter.close();
      }
    }
  }

  static class Data{
    private int id;
    private String name;
    private String description;

    Data(int id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getDescription() {
      return description;
    }
  }

}

package FileController;
public interface IFileHandler{
    public boolean isFileExsit();
    public void createFile();
    public void writeFile(String line);
    public String[] readFileAll();
    public String readLastLine();
}
package FileController;
import java.io.*;
import java.util.ArrayList;

public class FileHandler implements IFileHandler {

    private String path;
    public FileHandler(String path){
        this.path = path;
    }


    public boolean isFileExsit(){
        File file = new File(path);
        return file.exists();
    }

    public void createFile(){
        File file = new File(path);
        
        try{
            file.createNewFile();
          }
          catch(IOException e){
            System.err.println(e);
        }
    }

    public void writeFile(String line){
        try{
            FileWriter file = new FileWriter(path, true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            pw.println(line);
            pw.flush();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public String[] readFileAll(){
        ArrayList<String> lines = new ArrayList<>();
        try{
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            while((str = bufferedReader.readLine()) != null){
                lines.add(str);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return lines.toArray(new String[lines.size()]);
    }

    public String readLastLine(){
        String[] lines = readFileAll();
        return lines[lines.length - 1];
    }
}

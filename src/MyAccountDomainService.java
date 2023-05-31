
import java.io.*;

public class MyAccountDomainService {
  private static final String myAccount_FilePath = "MyAccountData.csv";
 
  //アカウント存在する？
  public static boolean isFileExist(){
    File file = new File(myAccount_FilePath);
    return file.exists();
  }
  
  //アカウントを作成する
  public static void createMyAcount(String name){
    File file = new File(myAccount_FilePath);
    try{
      file.createNewFile();
      WriteMyAccountFile(name);
    }
    catch(IOException e){
      System.err.println(e);
    }
  }
  
  //アカウント情報を書き込む
  public static void WriteMyAccountFile(String name){
    try{
      FileWriter file = new FileWriter(myAccount_FilePath);
      PrintWriter pw = new PrintWriter(new BufferedWriter(file));
      String line = "name," + name;
      pw.println(line);
      pw.flush();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
  
  //アカウント情報を読み込む
  public static String getAccountInfo(){
    String str = "";
    try{
      File file = new File(myAccount_FilePath);
			FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      str = bufferedReader.readLine();
    }
    catch(IOException e){
      System.out.println(e);
    }
    return str;
  }
  
  //アカウント情報を削除する
  public static void Delete(){
    File file = new File(myAccount_FilePath);
    file.delete();
  }  
}
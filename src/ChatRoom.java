import java.io.*;
import java.util.Date;

public class ChatRoom{
    protected String room_Name;
    private FileHandler fileHandler;
    public static String RecordDirectry = "ChatRoomRecord";

    public ChatRoom(String room_name, String file_path){
        this.room_Name = room_name;
        this.fileHandler = new FileHandler(file_path);

        if(!isChatRoomExsit()){
            createRoom();
        }
        else
        {
            System.out.println("Backlog");
            outputBackLog();
        }
    }

    protected boolean isChatRoomExsit(){
        return fileHandler.isFileExsit();
    }

    protected void createRoom(){
        fileHandler.createFile();
        fileHandler.writeFile("RoomName: " + room_Name);
        fileHandler.writeFile((""));
    }

    protected void outputBackLog(){
        String[] lines = fileHandler.readFileAll();
        for(String line : lines){
            System.out.println(line);
        }
    }

    public void talk(String talk_parson_name, String comment){
        String comment_line = talk_parson_name + ": " + comment;
        String date_line = new Date().toString();
        System.out.println(comment_line);
        fileHandler.writeFile(talk_parson_name + ": " + comment);
        fileHandler.writeFile(date_line);
    }
}

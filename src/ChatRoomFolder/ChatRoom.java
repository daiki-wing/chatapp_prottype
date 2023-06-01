package ChatRoomFolder;
import java.io.*;
import java.util.Date;

import FileController.FileHandler;

public class ChatRoom{
    protected String room_Name;
    private FileHandler fileHandler;
    protected static String RecordDirectry = "ChatRoomRecord";
    protected static final String extension = ".txt";

    public ChatRoom(String room_name){
        this.room_Name = room_name;
        this.fileHandler = new FileHandler(RecordDirectry + "/" + room_name + extension);

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

    public static void outputExistChatroomName(){
        File[] room_files = getExistChatroomFiles();
        for(int i = 0; i < room_files.length; i++){
            if(i != 0) System.out.print(" ");
            String room_name = room_files[i].getName();
            System.out.print(room_name.substring(0,room_name.lastIndexOf('.')));
        }
        System.out.println("");
    }

    public static boolean isExistChatroomFile(String file_name){
        File[] existfiles = getExistChatroomFiles();
        for(File file : existfiles){
            if(!(file_name + extension).equals(file.getName())) continue;
            return true;
        }
        return false;
    }

    public static File getChatroomFile(String chatroom_name){
        return new File(RecordDirectry + "/" + chatroom_name + extension);
    }

    private static File[] getExistChatroomFiles(){
        return new File(ChatRoom.RecordDirectry).listFiles();
    }
}

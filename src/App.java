import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import ChatRoomFolder.ChatRoom;
import ChatRoomFolder.Chatroom_AutoRes;

public class App {
private static String my_name;

    public static void main(String[] args) throws Exception {
        if(MyAccountDomainService.isFileExist()){
            //MyAccountが存在するならそこから名前を読み取る
            my_name = MyAccountDomainService.getAccountInfo();
            println_Message("Hello!!" + my_name + " !!");
        }
        else{            
            //MyAccountが存在しないなら入力を基にアカウントを作る
            println_Message("初めまして、名前を入力してください.");
            String line = inputMessage();
            String my_name = line.split(",")[1];
            MyAccountDomainService.createMyAcount(my_name);
            println_Message("こんにちは！" + my_name + "さん！\nアカウントを作成しました.");
            Chatroom_AutoRes autoRes = new Chatroom_AutoRes();
        }

        Thread.sleep(1000);

        String line_roomname = "";
        while(true){
            println_Message("入室するルームを入力してください");
            print_Message("現在、存在するルーム: ");
            ChatRoom.outputExistChatroomName();
            line_roomname = inputMessage();
            if(ChatRoom.isExistChatroomFile(line_roomname)) break;

            println_Message("ルームネーム「" + line_roomname + "」は存在しません.");
        }

        ChatRoom chatRoom;
        if(line_roomname.equals("AutoResRoom"))
            chatRoom = new Chatroom_AutoRes();
        else
            chatRoom = new ChatRoom(line_roomname);

        while(true){
            String input_line = inputMessage();
            chatRoom.talk(my_name, input_line);
        }
    }

    private static String inputMessage(){
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void println_Message(String message){
        print_Message(message + "\n");
    }
    private static void print_Message(String message){
        System.out.print(message);
    }
}

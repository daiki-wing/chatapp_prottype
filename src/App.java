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
            my_name = inputMessage();
            MyAccountDomainService.createMyAcount(my_name);
            println_Message("こんにちは！" + my_name + "さん！\nアカウントを作成しました.");
            new Chatroom_AutoRes();
        }

        Thread.sleep(1000); //少し待つ

        //ルーム選択 or ルーム作成
        String line_roomname = "";
        while(true){
            println_Message("入室するルームか作成するルームを入力してください");
            print_Message("現在、存在するルーム: ");
            ChatRoom.outputExistChatroomName(); //存在するルームを出力
            
            line_roomname = inputMessage();
            //存在するルームを選択したらルームに入出
            if(ChatRoom.isExistChatroomFile(line_roomname)){
                EnterRoom(line_roomname);
                break;
            }

            //存在しないルームが入力されたらルームを作成するか確認し、ルームを作成する
            println_Message("ルームネーム「" + line_roomname + "」は存在しません.");
            String input_line = wait_ParticularKeyword("ルームを作成しますか？", new String[]{"yes", "no"});
            if(input_line.equals("yes")){
                new ChatRoom(line_roomname);
                EnterRoom(line_roomname);
                break;
            }
        }


    }

    private static void EnterRoom(String room_name){
        ChatRoom chatRoom;
        if(room_name.equals("AutoResRoom"))
            chatRoom = new Chatroom_AutoRes();
        else
            chatRoom = new ChatRoom(room_name);

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
    private static void print_Array(String[] array){
        for(int i = 0;i < array.length; i++){
            if(i != 0) print_Message(", ");
            print_Message(array[i]);
        }
    }

    private static String wait_ParticularKeyword(String output_line, String[] keywords){
        String input_line = "";
        while(true){
            println_Message(output_line);
            print_Message("入力可能ワード: ");
            print_Array(keywords);
            println_Message("");

            input_line = inputMessage();
            
            for(String keyword : keywords){
                if(keyword.equals(input_line)) return input_line;
            }
            print_Message("入力可能なワードは");
            print_Array(keywords);
            println_Message("です");
        }
    }
}

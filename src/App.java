import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
private static String my_name;

    public static void main(String[] args) throws Exception {
        if(MyAccountDomainService.isFileExist()){
            my_name = MyAccountDomainService.getAccountInfo();
            System.out.println("Hello!!" + my_name + " !!");
        }
        else{            
            System.out.println("初めまして、名前を入力してください.");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String my_name = null;
            try {
                my_name = br.readLine();
                //br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MyAccountDomainService.createMyAcount(my_name);
            System.out.println("こんにちは！" + my_name + "さん！\nアカウントを作成しました.");
            Chatroom_AutoRes autoRes = new Chatroom_AutoRes();
        }

        Thread.sleep(1000);

        System.out.println("入室するルームを入力してください");
        System.out.print("現在、存在するルーム");
        File[] room_files = new File(ChatRoom.RecordDirectry).listFiles();
        for(int i = 0; i < room_files.length; i++){
            if(i != 0) System.out.print(" ");
            String room_name = room_files[i].getName();
            System.out.println(room_name.substring(0,room_name.lastIndexOf('.')));
        }

        
        InputStreamReader _isr = new InputStreamReader(System.in);
        BufferedReader _br = new BufferedReader(_isr);
        String line = null;
        try {
            line = _br.readLine();
            //_br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!isContain(room_files, line)){
            System.out.println(line + "はないっす");
        }
        ChatRoom chatRoom;
        if(line.equals("AutoResRoom")){
            chatRoom = new Chatroom_AutoRes();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(true){
                try {
                    String input_line = br.readLine();
                    chatRoom.talk(my_name, input_line);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    private static boolean isContain(File[] array, String target_filename){
        for(File item : array){
            if(item.getName().equals(target_filename + ".txt")) return true;
        }
        return false;
    }
}

package ChatRoomFolder;
public class Chatroom_AutoRes extends ChatRoom{
    protected static final String defaultRes = "うい";
    private static final String name = "Ui_Chan";
    public Chatroom_AutoRes(){
        super("AutoResRoom");
    }

    @Override
    public void talk(String talk_parson_name, String comment){
        super.talk(talk_parson_name, comment);
        super.talk(name, defaultRes);
    }
}

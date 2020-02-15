import java.io.File;
import java.util.List;

public class Maın {
    public static void main(String[] args) {
        try {
            File f=new File("src/main/java/mmm.txt");
            f.createNewFile();
            Methods m=new Methods();
            String rnn = m.findRNN(f, String.valueOf(3456789));
            List<String> id = m.findId(f, rnn);
           m.createDoc(id,"src/main/java/","again20.docx","3456789");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

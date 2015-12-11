import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Position {

    private ArrayList<String> pos = new ArrayList<>();
    private LevelMaker makerSoal = new LevelMaker();

    public Position() {
    }

    public void setCard(String content) { //set 2 card
        for (int i = 1; i <= 2; i++){
            pos.add(content);
        }

    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public void randomize() {
        //TODO randomize

    }
}

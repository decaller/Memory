import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 * ini untuk pasang posisi
 */
public class Position {

    private ArrayList<String> pos = new ArrayList<>();


    public Position() {
    }

    public void setCard(String content) { //set 2 card yg sama karena memory game duh...
        for (int i = 1; i <= 2; i++){
            pos.add(content);
        }

    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public void randomize() {
        //randomize nanti...

    }
}

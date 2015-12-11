import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Card extends JButton implements ActionListener{

    private int id;
    private String content;
    private boolean open;
    private boolean guessed;



    public Card(int id, String content) {
        this.id = id;
        this.content = content;
        this.setText(content);
        this.addActionListener(this);
    }

    public int getId() {
        return id;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public boolean isGuessed() {
        return guessed;
    }


    public String getContent() {
        return content;
    }

    public boolean isOpen() {
        return open;
    }

    public void close(){
        open = false;
    }


    //TODO animation flip and open


    @Override
    public void actionPerformed(ActionEvent e) {
        open = true;
        Menu.cardChecker(id,content);
    }
}

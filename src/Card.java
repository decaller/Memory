import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HarridiIlman on 11/12/2015.
 *
 * ini button yg di modify agar bersifat seperti kartu
 */
public class Card extends Menu implements ActionListener{

    private int id;
    private String content;
    private boolean open;
    private boolean guessed;



    public Card(int id, String content) {
        this.id = id;
        this.content = content;
        //this.setText(content);
        this.addActionListener(this); //ketinggalan
        this.guessed = false;
        this.open = false;
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

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {

        return open;
    }

    public void close(){
        open = false;
        this.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open = true;
        this.setText(content);
        cardChecker(id,content);
    }


    //TODO animation flip and open


}

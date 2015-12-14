import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Created by HarridiIlman on 11/12/2015.
 *
 * ini button yg di modify agar bersifat seperti kartu
 */
public class Card extends Menu{

    private int id;
    private String content;
    private boolean open = false;
    private boolean guessed = false;


    private boolean flipped = false;
    private boolean flipme = false;

    private int width;
    private int posX;


    public Card(int id, String content) {
        this.id = id;
        this.content = content;

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

    public boolean isFlipme() {
        return flipme;
    }

    public void close(){
        open = false;
        flipme = true;
    }

    public void flip() {

            if (!flipped){
                setBounds(getBounds().x + 25 ,getBounds().y,getWidth() - 50,getHeight());

                if (getWidth() <= 50){

                    flipped = true;

                    if (open) {
                        setText(content);

                    } else {
                        setText("");
                    }

                }
            }
            if (flipped){
                setBounds(getBounds().x - 25 ,getBounds().y,getWidth() + 50,getHeight());

                if (getWidth() >= width-100){
                    setBounds(posX ,getBounds().y,width,getHeight());

                    flipped = false;
                    flipme = false;

                }



            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cardCloser();
        open = true;

        posX = getBounds().x;
        width = getWidth();
        flipme = true;

        cardChecker(id,content);

    }


    //TODO animation flip and open


}

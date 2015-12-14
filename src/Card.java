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
    private boolean open = false;
    private boolean guessed = false;

    private Timer timer;

    private boolean flipped = false;

    private int width;


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
        this.setText(content);

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

        timer = new Timer(30, flip);
        timer.setInitialDelay(300);
        timer.start();

    }

    ActionListener flip = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (!flipped){
                setBounds(getBounds().x + 10 ,getBounds().y,getWidth() - 20,getHeight());

                if (getWidth() <= 20){

                    flipped = true;

                    if (open) {
                        setText(content);
                    } else {
                        setText("");
                    }

                }
            }
            if (flipped){
                setBounds(getBounds().x - 10 ,getBounds().y,getWidth() + 20,getHeight());

                if (getWidth() >= width-20){

                    flipped = false;

                    timer.stop();
                }

            }

        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {

        open = true;

        width = getWidth();

        timer = new Timer(30, flip);
        timer.setInitialDelay(0);
        timer.start();

        cardChecker(id,content);



    }


    //TODO animation flip and open


}

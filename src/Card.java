import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HarridiIlman on 11/12/2015.
 *
 * ini button yg di modify agar bersifat seperti kartu
 */
public class Card extends JButton{ //TODO sebelumnya gw coba implement disini actionlistener nya yan.. tapi harus static kaya lw bilang
    //akhirnya semua di main jadi harus static dan swing gak mau kalo variable nya bersifat static <- entah mengapa

    private int id;
    private String content;
    private boolean open;
    private boolean guessed;



    public Card(int id, String content) {
        this.id = id;
        this.content = content;
        this.setText(content);
    }

    public int getId() {
        return id;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
        this.setText("guessed");
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
    }


    //TODO animation flip and open

/**
    @Override
    public void actionPerformed(ActionEvent e) {
        open = true;
        cardChecker(id,content);
    }

    **/
}

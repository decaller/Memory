import javax.swing.*;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Card extends JButton {


    public Card(String isiKartu) {

        this.setText(isiKartu);
        this.addActionListener(this);
    }

    private void addActionListener(Card card) {
        System.out.println("klik");
    }

}

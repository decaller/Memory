import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Menu {
    private JPanel panelMenu;
    private JButton startGameButton;
    private JLabel highScoreLabel;
    private JLabel waktuLabel;
    private JLabel levelLabel;
    private JProgressBar timeProgressBar;
    private JProgressBar levelProgressBar;
    private JLabel highLevelLabel;
    private static JPanel gamePanel;
    private JButton buttonStart;

    public static int level = 1;
    public static int sublevel = 1;

    private static LevelMaker makerSoal = new LevelMaker();

    public static ArrayList<Card> cards;
    public static String temp = null;
    public static int tempId = 0;


    public Menu() {
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLevel();
                //TODO timer
            }
        });
    }

    private static void startLevel(){
        //levelLabel.setText(Integer.toString(level));
        //panelMenu.repaint();
        startSubLevel();
    }
    private static void startSubLevel(){
        Position soal = makerSoal.makeLevel(level,sublevel);
        drawer(soal);
    }

    private static void drawer(Position soal){
        //remove all
        gamePanel.removeAll();
        gamePanel.repaint();

        ArrayList<String> isiKartu2 = soal.getPos();
        cards = new ArrayList<>();

        int y = 2;
        int x = 3;
        switch (isiKartu2.size()){
            case 12 : {
                y = 3;
                x = 4;
                break;
            }
            case 20 : {
                y = 4;
                x = 5;
                break;
            }
        }
        gamePanel.setLayout(new GridLayout(y,x,5,5)); // 5,5 margin

        int i = 1;
        for (String isiKartu : isiKartu2){ //buat kartu...
            Card kartu = new Card(i, isiKartu);
            gamePanel.add(kartu);
            cards.add(kartu);
            i++;
        }

        gamePanel.revalidate();
        gamePanel.repaint();

    }

    public static void cardChecker(int id, String content){
        if (temp == null){
            temp = content;
            tempId = id;
        } else {
            if (temp.equals(content)){
                //set Guessed
                for (Card kartu : cards){
                    if (kartu.getId() == id || kartu.getId() == tempId){
                        kartu.setGuessed(true);
                    }
                }
                closeCardChecker();
            } else {
                temp = null;
                tempId = 0;
            }
        }
        //close all not guessed
        for (Card kartu : cards){
            if (!kartu.isGuessed()){
                kartu.close();
            }
        }

    }

    private static void closeCardChecker(){
        for (Card kartu : cards){
            if (!kartu.isGuessed()){
                break;
            } else {
                replay();
            }
        }
    }

    private static void replay() {
        if (sublevel <= 5-level){
            //levelProgressBar.setValue(100*sublevel/(5-level));
            sublevel++;
            startSubLevel();
        }

        if (level <= 3){ // ada 3 level
            level++;
            startLevel();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panelMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

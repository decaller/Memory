import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Menu extends JButton {
    private JPanel panelMenu;
    private JButton startGameButton;
    private JLabel highScoreLabel;
    private JLabel waktuLabel;
    private JLabel levelLabel;
    private JProgressBar timeProgressBar;
    private JProgressBar levelProgressBar;
    private JLabel highLevelLabel;
    private JPanel gamePanel;
    private JButton buttonStart;

    public int level = 1;
    public int sublevel = 1;

    private LevelMaker makerSoal = new LevelMaker();

    private ArrayList<Card> cards = new ArrayList<>();
    private String temp = null;
    private int tempId = 0;

    private Card kartu;

    public Menu() {

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLevel();
                //nanti tambah timer
            }
        });



    }
    //untuk mulai
    private void startLevel(){
        levelLabel.setText(Integer.toString(level));
        panelMenu.repaint();
        startSubLevel();
    }
    private void startSubLevel(){
        Position soal = makerSoal.makeLevel(level,sublevel);
        drawer(soal);
    }

    //gambar di game panel
    private void drawer(Position soal){
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
            kartu = new Card(i, isiKartu);// TODO disini buat kartunya yan.. apa pake abstract ya??
            cards.add(kartu);
            gamePanel.add(kartu);
            i++;
        }

        gamePanel.revalidate();
        gamePanel.repaint();

    }

    //check apa kartunya sama?
    public void cardChecker(int id, String content){
        System.out.println("card check "+id);
        if (temp == null){
            temp = content;
            tempId = id;
            System.out.println(temp);
        } else {
            System.out.println("activated");
            if (temp.equals(content)){
                //set Guessed
                System.out.println("set guessed");
                for (Card kartu : cards){
                    if (kartu.getId() == id | kartu.getId() == tempId){
                        System.out.println("set guessed card");
                        kartu.setGuessed(true);
                    }
                }
                guessedCardChecker();
            }
            System.out.println("nulling");
            temp = null;
            tempId = 0;

            //close all not guessed
            for (Card kartu : cards){
                if (!kartu.isGuessed()){
                    kartu.close();
                }
            }
        }



    }

    //check apa udah ketebak semua?
    private void guessedCardChecker(){
        System.out.println("close card check");
        int unGuessed = 0;
        for (Card kartu : cards){
            if (!kartu.isGuessed()){
                unGuessed++;
            }
        }
        if (unGuessed == 0){
            //Menu reMenu = new Menu(); TODO ini gw coba juga yan.. bisa run tapi gak mau lanjut ke level selanjutnya
            //reMenu.replay();
            this.replay();
        }
    }

    //ulang sublevel atau level
    private void replay() {
        System.out.println("Replay");
        if (sublevel <= 5-level){
            System.out.println("New SubLevel");
            levelProgressBar.setValue(100*sublevel/(5-level));
            sublevel++;
            startSubLevel();
        } else if (level <= 3){ // ada 3 level
            System.out.println("New Level");
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

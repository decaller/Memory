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
    private JPanel gamePanel;
    private JButton buttonStart;

    public int level = 1;
    public int sublevel = 1;

    private LevelMaker makerSoal = new LevelMaker();

    public ArrayList<Card> cards;
    public String temp = null;
    public int tempId = 0;

    public Card kartu;

    public Menu() {

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLevel();
                //nanti tambah timer
            }
        });

        //TODO ini masalah nya yan.. ini mau diimplementasikan ke seluruh kartu... define nya dibawah \/
        kartu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kartu.setOpen(true);
                int id = kartu.getId();
                String content = kartu.getContent();
                cardChecker(id,content);
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
            gamePanel.add(kartu);
            cards.add(kartu);
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
        } else {
            if (temp.equals(content)){
                //set Guessed
                for (Card kartu : cards){
                    if (kartu.getId() == id || kartu.getId() == tempId){
                        kartu.setGuessed(true);
                    }
                }
                closeCardChecker();
            }
            temp = null;
            tempId = 0;

        }
        //close all not guessed
        for (Card kartu : cards){
            if (!kartu.isGuessed()){
                kartu.close();
            }
        }

    }

    //check apa udah ketebak semua?
    private void closeCardChecker(){
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

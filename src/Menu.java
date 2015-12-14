import com.sun.javafx.binding.StringFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Menu extends JButton implements ActionListener{
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

    private static int level = 1;
    private static int sublevel = 1;
    private static int fontSize = 40;

    private LevelMaker makerSoal = new LevelMaker();

    private static ArrayList<Card> cards = new ArrayList<>();
    private static String temp = null;
    private static int tempId = 0;

    private Card kartu;



    public Menu() {

        startGameButton.addActionListener(this);

    }
    //untuk mulai
    private void startLevel(){
        System.out.println("start level "+level);
        levelLabel.setText("Level " + Integer.toString(level));
        panelMenu.repaint();
        startSubLevel();
    }
    private void startSubLevel(){
        System.out.println("start sublevel "+sublevel);
        Position soal = makerSoal.makeLevel(level,sublevel);
        drawer(soal);
    }

    //gambar di game panel
    private void drawer(Position soal){
        System.out.println("drawing");
        //remove all

        gamePanel.removeAll();

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
            kartu = new Card(i, isiKartu);

            kartu.setFont(new Font("Arial", Font.PLAIN, fontSize));
            cards.add(kartu);
            gamePanel.add(kartu);
            System.out.println("add kartu "+ kartu.getContent());
            i++;
        }

        gamePanel.revalidate();
        repaint();

    }

    //check apa kartunya sama?
    public void cardChecker(int id, String content){
        System.out.println("card check "+id);
        levelProgressBar.setValue(20);

        if (temp == null){
            temp = content;
            tempId = id;
            System.out.println("temp " + temp);
        } else if (tempId != id){
            System.out.println("checking...");
            if (temp.equals(content)){
                System.out.println("guess right!");
                //set Guessed
                System.out.println("set guessed");
                for (Card kartu : cards){
                    if (kartu.getId() == id | kartu.getId() == tempId){
                        System.out.println("set guessed card");
                        kartu.setGuessed(true);


                    }


                }
            }

            System.out.println("nulling");
            temp = null;
            tempId = 0;


            //close all not guessed
            for (Card kartu : cards){
                if (kartu.isOpen()){
                    if (!kartu.isGuessed()){
                        kartu.close();
                    }
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
            replay();
        }
    }

    //ulang sublevel atau level
    private void replay() {
        System.out.println("Replay");

        if (sublevel < 5-level){
            System.out.println("New SubLevel");
            levelProgressBar.setValue(100*sublevel/(5-level));
            System.out.println("Set Progress "+ 100*sublevel/(5-level));
            sublevel++;
            startSubLevel();
        } else if (level < 3){ // ada 3 level
            System.out.println("New Level");
            levelProgressBar.setValue(0);
            level++;
            sublevel = 0;
            fontSize -= 10;
            startLevel();
        } else {
            sublevel++;
            level++;
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panelMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        startLevel();
        Task task = new Task();
        task.execute();
    }

    class Task extends SwingWorker<Void, Void> {

        @Override
        public Void doInBackground() {
        int time = 0;
            while (level < 4) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {}
                time++;
                guessedCardChecker();

                int minute = time/600;
                int second = (time%600)/10;
                waktuLabel.setText(String.format("%02d",minute)  + ":" + String.format("%02d",second));
                timeProgressBar.setValue(100* time/3000);

                if (time > 3000){
                    level = 4;
                    sublevel = 0;
                }
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            if (level > 3 && sublevel > 2){
                gamePanel.removeAll();
                gamePanel.revalidate();
                repaint();
                JLabel congrats = new JLabel("Selamat Anda Berhasil!");
                gamePanel.add(congrats);
                gamePanel.revalidate();
                repaint();
            } else {
                gamePanel.removeAll();
                gamePanel.revalidate();
                repaint();
                JLabel loss = new JLabel("Waktu Habis!");
                gamePanel.add(loss);
                gamePanel.revalidate();
                repaint();
            }

        }


    }


}

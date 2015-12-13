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
public class Menu extends JButton implements PropertyChangeListener, ActionListener{
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
        levelLabel.setText(Integer.toString(level));
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
        } else {
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
                if (!kartu.isGuessed()){
                    kartu.close();
                }
            }
        }
        guessedCardChecker();



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

        if (sublevel <= 5-level){
            System.out.println("New SubLevel");
            System.out.println((levelProgressBar.getAccessibleContext()==null));
            levelProgressBar.setValue(100*sublevel/(5-level));
            System.out.println("Set Progress "+ 100*sublevel/(5-level));
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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            levelProgressBar.setValue(progress);

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startLevel();
        levelProgressBar.setValue(50);

        Task task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {


            //Initialize progress property.
            setProgress(0);
            while (100*sublevel/(5-level) < 100) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {}
                //Make random progress.
                setProgress(100*sublevel/(5-level));
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {

        }


    }


}

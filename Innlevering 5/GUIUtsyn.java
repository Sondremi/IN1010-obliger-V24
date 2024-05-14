import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.Timer;

public class GUIUtsyn {
    private final int antKol;
    private final int antRader;
    private final Verden verden;
    private JLabel antall;
    private JButton[][] rutenett;
    private Timer timer;

    public GUIUtsyn(int antRader, int antKol) {
        this.antKol = antKol;
        this.antRader = antRader;
        verden = new Verden(antRader, antKol);
        rutenett = new JButton[antRader][antKol];
    }

    class Start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Starter en timer som kjører Start-action hvert 2. sekund
            timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    verden.oppdatering();
                    //verden.tegn(); // Tegner i terminalen

                    antall.setText(" Antall levende: " + verden.rutenett.antallLevende());

                    for (int rad = 0; rad < antRader; rad++) {
                        for (int kol = 0; kol < antKol; kol++) {
                            Celle celle = verden.rutenett.hentCelle(rad, kol);
                            String statusTegn = String.valueOf(celle.hentStatusTegn());

                            JButton rute = rutenett[rad][kol];
                            rute.setText(statusTegn);
                        }
                    }
                }
            });
            timer.start(); // Starter timeren
        }
    }

    class Avslutt implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class RuteKnapp implements ActionListener {
        private Celle celle;
        private JButton rute;

        public RuteKnapp(Celle celle, JButton rute) {
            this.celle = celle;
            this.rute = rute;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (celle.hentStatusTegn() == '.') {
                celle.settLevende();
            } else {
                celle.settDoed();
            }
            antall.setText("Antall levende: " + verden.rutenett.antallLevende());
            rute.setText(String.valueOf(celle.hentStatusTegn()));
        }
    }

    public void init() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(0);
        }

        // Oppretter gui vinduet
        JFrame vindu = new JFrame("Game Of Life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setLayout(new BorderLayout(0, 20));

        // Panel for header
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(antKol*50, 50));
        vindu.add(panel, BorderLayout.NORTH);

        // Panel for alle cellene
        JPanel panelKnapper = new JPanel();
        vindu.add(panelKnapper, BorderLayout.CENTER);

        // Setter layout og legger til padding 5, 5
        panel.setLayout(new GridLayout(1, 3, 5, 5));
        panelKnapper.setLayout(new GridLayout(antRader, antKol, 5, 5));
        
        // Antall viser antall levende celler i verden
        antall = new JLabel(" Antall levende: " + verden.rutenett.antallLevende());
        antall.setFont(new Font("Calibri", Font.BOLD, 16));
        
        // Knapp som starter simuleringen
        JButton start = new JButton("Start");
        start.addActionListener(new Start());
        start.setBackground(Color.green);
        
        // Knapp for å avslutte programmet
        JButton avslutt = new JButton("Avslutt");
        avslutt.addActionListener(new Avslutt());
        avslutt.setBackground(Color.red);

        panel.add(antall);
        panel.add(start);
        panel.add(avslutt);

        // Oppretter alle Celle-rutene
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKol; kol++) {
                // Henter celle og tilhørende statustegn fra rutenettet
                Celle celle = verden.rutenett.hentCelle(rad, kol);
                String statusTegn = String.valueOf(celle.hentStatusTegn());

                // Oppretter en ny rute
                JButton rute = new JButton(statusTegn);
                rute.addActionListener(new RuteKnapp(celle, rute));

                // Legger cellen i et eget rutenett for å kunne hente ut riktig rute under simuleringen
                rutenett[rad][kol] = rute;

                rute.setBackground(new Color(255, 255, 255));
                rute.setPreferredSize(new Dimension(50, 50));

                panelKnapper.add(rute);
            }
        }

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class healthCare extends JFrame implements KeyListener {
    JPanel p = new JPanel();
    JTextArea dialog = new JTextArea(20,50);
    JTextArea input = new JTextArea(1,50);

    String chatBot[][] = {

            {"hi"},
            {"Welcome to our 24X7 Online AlphaX HealthCare ChatBot System."},

            {"weakness", "body pain", "shivering", "chills", "irritated", "headache"},
            {"You might have fever." + "\n" + "For quick relief you can take medicine - Dolo 650, Paracetamol." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

            {"sore throat", "dry cough", "ruuny nose", "getting cold"},
            {"You might have cold." + "\n" + "For quick relief you can take medicine - Cetriz, Zolrex-D, Viscodyne-D." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

            {"dizziness", "faint", "sleepiness", "tired"},
            {"You might have weakness." + "\n" + "For quick relief you can take medicine - AtoZ Syrup, Vitamine tablet." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

            {"acidity", "vomating"},
            {"You might have stomach problem." + "\n" + "For quick relief you can take medicine - Eno, Pantop-D." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

            {"short temper"},
            {"You might have BP." + "\n" + "For quick relief you can take medicine - Pinon a20, Benazerpril." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

            {"blured vision"},
            {"You might have Eye Problem." + "\n" + "For quick relief you can take medicine - Ciplox Eye Drops." + "\n" + "Nearest Medicial Shop - Uni-Hospital, LPU"},

    };

    JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    public static void main(String[] args) {
        new healthCare();
    }

    public healthCare() {
        super("AlphaX HealthCare ChatBot");
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dialog.setEditable(false);
        input.addKeyListener(this);
        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(255,200,0));
        add(p);
        setVisible(true);
    }

    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(false);

            //-----grab symptoms----------
            String s = input.getText(); // s means symptoms
            input.setText("");
            addText("\nYou: \t " + s);
            s = s.trim();

            while(
                    s.charAt(s.length()-1) == '!' ||
                            s.charAt(s.length()-1) == '.' ||
                            s.charAt(s.length()-1) == '?'
            ) {
                s = s.substring(0, s.length()-1);
            }

            s = s.trim();

            byte response =0;
			/*
			0: searching
			1: did not find
			2: found
			*/

            //-----check for matches---
            int j = 0; //Group in ChatBot String array that we are checking
            while(response == 0) {
                if(inArray(s.toLowerCase(), chatBot[j*2])) {
                    response = 2;

                    int r = (int) Math.floor(Math.random() * chatBot[(j*2) + 1].length);
                    addText("\nAlphaX : \t " + chatBot[ (j*2) + 1 ][r] );
                }

                j++;

                if( j*2 == chatBot.length-1 && response != 2) {
                    response = 1;
                }

                //addText("\n");
            }

            //-----default-------------
            if( response==1 ) {
                int r = (int) Math.floor(Math.random() * chatBot[chatBot.length-1].length);
                addText("\nAlphaX : " + chatBot[ chatBot.length-1 ][r] );
            }
        }
    }

    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void addText(String str) {
        dialog.setText(dialog.getText()+str);
    }

    public boolean inArray(String in, String[] str) {
        boolean match = false;
        //length of array is contant so it is stored in variable length unlike for String object which is done using methof length()
        for(int i=0; i < str.length; i++) {
            if(str[i].equals(in)) {
                match = true;
            }
        }
        return match;
    }

}

package OOP.ec20315.A8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;


import javax.swing.*;

class GUIVisitor_ec20315 implements Visitor {
    JFrame db = new JFrame();
    JTextArea tellText = new JTextArea("Hello!\n");
    JTextArea itemText = new JTextArea();
    JButton acceptButton = new JButton("Accept Item");
    static enum Sempahore {WAITING, ACCEPT_ITEM, REJECT_ITEM};
    Sempahore acceptSempahore = Sempahore.WAITING;

    private PrintStream out;
    private Scanner in;
    private int purse;
    private Item[] items;
    private int next;

    public GUIVisitor_ec20315(PrintStream ps, InputStream is) {
        db.getContentPane().add(tellText, BorderLayout.NORTH);
        db.getContentPane().add(acceptButton, BorderLayout.SOUTH);
        db.getContentPane().add(itemText, BorderLayout.CENTER);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                acceptSempahore = Sempahore.ACCEPT_ITEM;
            }
        } );
        db.setTitle("GUIVisitor by ec20315");
        db.setSize(800, 600);
        db.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        db.setVisible(true);
        tellText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemText.setBorder(BorderFactory.createLineBorder(Color.BLACK));




        out = ps;
        in = new Scanner(is);
        purse = 0;
        items = new Item[1000];
        next = 0;
    }


    private static final char[] yOrN = { 'y', 'n'};

    public void tell(String m) {
        tellText.append(m);
    }

    public char getChoice(String d, char[] a) {
        JTextField textField = new JTextField();
        Object[] message = {
                d,
                textField
        };
        JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
        String t = textField.getText();
        if (t.length() > 0)
            return t.charAt(0);
        else {
            if (a.length > 0) {
                tell("Returning "+a[0]);
                return a[0];
            } else {
                tell("Returning '?'");
                return '?';
            }
        }
    }

    public boolean giveItem(Item x) {
        tell("You have: ");
        for (int i=0;i<next;i++) tell(items[i] + ", ");
        acceptButton.setText("Accept "+x.name+"?");
        if (next >= items.length) {
            tell("But you have no space and must decline.");
            return false;
        }
        //Wait for accept button press
        acceptSempahore = Sempahore.WAITING;
        acceptButton.setEnabled(true);
        while(acceptSempahore == Sempahore.WAITING) {
            db.repaint();
        }
        acceptButton.setEnabled(false);
        if (acceptSempahore==Sempahore.ACCEPT_ITEM) {
            items[next] = x;
            itemText.append(String.valueOf(items[next]));
            next++;


            return true;
        } else return false;
    }

    public boolean hasIdenticalItem(Item x) {
        for (int i=0; i<next;i++)
            if (x == items[i])
                return true;
        return false;
    }

    public boolean hasEqualItem(Item x) {
        for (int i=0; i<next;i++)
            if (x.equals(items[i]))
                return true;
        return false;
    }

    public void giveGold(int n) {
        out.println("You are given "+n+" gold pieces.");
        purse += n;
        out.println("You now have "+purse+" pieces of gold.");
    }

    public int takeGold(int n) {

        if (n<0) {
            out.println("A scammer tried to put you in debt to the tune off "+(-n)+"pieces of gold,");
            out.println("but you didn't fall for it and returned the 'loan'.");
            return 0;
        }

        int t = 0;
        if (n > purse) t = purse;
        else t = n;

        out.println(t+" pieces of gold are taken from you.");
        purse -= t;
        out.println("You now have "+purse+" pieces of gold.");

        return t;
    }
}
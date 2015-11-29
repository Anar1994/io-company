package maga;

import lab1.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main{
    private static MainFrame mFrame;

    /**
     * Точка запуску програми. Тут створюється головне вікно проограми та нова ЛСА.
* @param args
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFrame = new MainFrame();
        LSA lsa = new LSA();
        mFrame.setLsa(lsa);
        mFrame.createMainFrame();
    }
}

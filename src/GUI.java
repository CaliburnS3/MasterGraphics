/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import sun.print.ServiceDialog;
import sun.security.provider.ConfigFile;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;
import java.util.Random;

public class GUI extends JPanel implements ActionListener {
    protected JTextField SeedBox;
    protected JTextField outBox;

    static String[] args = {};

    public Random rand;
    public long SeedOutput;


    public GUI(){

        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Buttons
        JButton SpirographButton = new JButton("Spirograph");
        SpirographButton.setBounds(0, 0, 120, 30);
        SpirographButton.addActionListener(e -> SpirographButton(1));

        JButton S2Button = new JButton("Toggle Beams");
        S2Button.setBounds(0, 25, 120, 30);
        S2Button.addActionListener(e -> SpirographButton(0));

        JButton DCButton = new JButton("DCircle");
        DCButton.setBounds(0, 50, 120, 30);
        DCButton.addActionListener(e -> DCButton());

        JButton NMButton = new JButton("Node Map");
        NMButton.setBounds(0, 75, 120, 30);
        NMButton.addActionListener(e -> NMButton());

        JButton SGButton = new JButton("Shape Gen");
        SGButton.setBounds(0, 100, 120, 30);
        SGButton.addActionListener(e -> SGButton());

        JButton CSButton = new JButton("Copy Seed");
        CSButton.setBounds(0, 125, 120, 30);
        CSButton.addActionListener(e -> CSButton());

        JButton ClSButton = new JButton("Clear Seed");
        ClSButton.setBounds(0, 150, 120, 30);
        ClSButton.addActionListener(e -> ClSButton());

        SeedBox = new JTextField();
        SeedBox.setBounds(0, 175, 120, 30);
        SeedBox.addActionListener(this);

        outBox = new JTextField();
        outBox.setBounds(0, 200, 120, 30);


        //Add Components to this panel.
        //GridBagConstraints H = new GridBagConstraints();
        //H.fill = GridBagConstraints.HORIZONTAL;
        frame.add(SpirographButton);
        frame.add(S2Button);
        frame.add(DCButton);
        frame.add(NMButton);
        frame.add(SGButton);
        frame.add(CSButton);
        frame.add(ClSButton);
        frame.add(SeedBox);
        frame.add(outBox);



        //Display the window.
        //frame.pack();
        frame.setSize(120, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        CanvasHost.main(args);
    }

    private long processSeed(){
        String text = SeedBox.getText();
        Random randLong = new Random();
        long output;
        if(text.equalsIgnoreCase("")){

            output = randLong.nextLong();
            outBox.setText(String.valueOf(output));
            return output;
        }
        output = Long.valueOf(text);
        outBox.setText(text);
        return output;
    }

    private void SpirographButton(int input){
        SwingWorker myWorker= new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                //Execute your logic

                Spirograph.drawSeeded(processSeed(), input);
                Shape rect = new Rectangle(0, 0, CanvasHost.X - 1, CanvasHost.Y - 1);
                CanvasHost.myCanvas.draw(rect);
                return null;
            }
        };
        myWorker.execute();
    }

    private void DCButton(){
        SwingWorker myWorker= new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                //Execute your logic

                doubleCircle.drawSeeded(processSeed());
                Shape rect = new Rectangle(0, 0, CanvasHost.X - 1, CanvasHost.Y - 1);
                CanvasHost.myCanvas.draw(rect);
                return null;
            }
        };
        myWorker.execute();

    }

    private void NMButton(){
        NodeMap.drawSeeded(processSeed());
    }

    private void SGButton(){
        SwingWorker myWorker= new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                //Execute your logic

                ShapeGeneration.randomShape(processSeed());
                return null;
            }
        };
        myWorker.execute();
    }

    private void CSButton(){
        SeedBox.setText(outBox.getText());
    }

    private void ClSButton(){
        SeedBox.setText("");
    }

    public void actionPerformed(ActionEvent evt) {
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //createGUI();
                Object runnable = new GUI();
            }
        });
    }
}
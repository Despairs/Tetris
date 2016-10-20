/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.utils.AppConfig;
import com.despairs.games.tetris.model.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author EKovtunenko
 */
public class GameFrame extends JFrame {

    private final Timer timer;
    private GameBoard board;
    private GameStatistic statistic;

    public void initUI() {
        board = new GameBoard();
        statistic = new GameStatistic();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), AppConfig.BORDER_SIZE));
        javax.swing.GroupLayout boardLayout = new javax.swing.GroupLayout(board);
        board.setLayout(boardLayout);
        boardLayout.setHorizontalGroup(
                boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, AppConfig.BOARD_WIDTH, Short.MAX_VALUE)
        );
        boardLayout.setVerticalGroup(
                boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        statistic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), AppConfig.BORDER_SIZE));

        javax.swing.GroupLayout statisticLayout = new javax.swing.GroupLayout(statistic);
        statistic.setLayout(statisticLayout);
        statisticLayout.setHorizontalGroup(
                statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(statisticLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        //                        .addComponent(jLabel1)
                        .addContainerGap(33, Short.MAX_VALUE))
        );
        statisticLayout.setVerticalGroup(
                statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(statisticLayout.createSequentialGroup()
                        .addContainerGap()
                        //                        .addComponent(jLabel1)
                        .addContainerGap(AppConfig.BOARD_HEIGHT, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        pack();
    }

    public GameFrame() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                board.move(Direction.DOWN);
//                statistic.move(Direction.DOWN);
                repaint();
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        board.rotate();
                        break;
                    case KeyEvent.VK_DOWN:
                        board.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        board.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        board.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_SPACE:
                        break;
                }
                repaint();
            }
        });

    }

    public void start() {
        initUI();
        setVisible(true);
        timer.start();
    }
}

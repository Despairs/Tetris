/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.utils.AppConfig;
import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.presenter.GamePresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import com.despairs.games.tetris.view.GameView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author EKovtunenko
 */
public class GameFrame extends JFrame implements GameView, KeyListener, ActionListener {

    private Timer timer;
    private GameBoard board;
    private GameStatistic statistic;
    private JLabel scores;

    private GamePresenter presenter;

    public void start() {
        initEngine();
        initUI();
        setVisible(true);
        timer.start();
    }

    private void initEngine() {
        timer = new Timer(1000, this);
        addKeyListener(this);
        presenter = new GamePresenter(this);
    }

    @Override
    public void setScore(Long score) {
        scores.setText("Current scores: " + score);
    }

    public void initUI() {
        board = new GameBoard(presenter);
        statistic = new GameStatistic(presenter);
        scores = new JLabel();

        board.setPreferredSize(new Dimension(AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT));
        board.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, AppConfig.BORDER_SIZE));
               
        statistic.setPreferredSize(new Dimension(AppConfig.STATISTIC_WIDTH, AppConfig.STATISTIC_HEIGHT));
        statistic.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, AppConfig.BORDER_SIZE));
        statistic.add(scores);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        setPreferredSize(new Dimension(AppConfig.FRAME_WIDTH, AppConfig.FRAME_HEIGHT));
//        setResizable(false);
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(statistic, BorderLayout.AFTER_LINE_ENDS);
        
//        javax.swing.GroupLayout boardLayout = new javax.swing.GroupLayout(board);
//        BorderLayout boardLayout = new BorderLayout();
//        board.setLayout(boardLayout);
//        boardLayout.setHorizontalGroup(
//                boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, AppConfig.BOARD_WIDTH, Short.MAX_VALUE)
//        );
//        boardLayout.setVerticalGroup(
//                boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 0, Short.MAX_VALUE)
//        );
   
//        javax.swing.GroupLayout statisticLayout = new javax.swing.GroupLayout(statistic);
//        statistic.setLayout(statisticLayout);
//        statisticLayout.setHorizontalGroup(
//                statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(statisticLayout.createSequentialGroup()
//                        .addGap(50, 50, 50)
//                        .addComponent(scores)
//                        .addContainerGap(100, Short.MAX_VALUE))
//        );
//        statisticLayout.setVerticalGroup(
//                statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(statisticLayout.createSequentialGroup()
//                        .addContainerGap()
//                        .addComponent(scores)
//                        .addContainerGap(AppConfig.BOARD_HEIGHT, Short.MAX_VALUE))
//        );
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup()
//                        .addContainerGap()
//                        .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(statistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addContainerGap())
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup()
//                        .addContainerGap()
//                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(statistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                        .addContainerGap())
//        );

        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                presenter.onRotate();
                break;
            case KeyEvent.VK_DOWN:
                presenter.onMove(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                presenter.onMove(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                presenter.onMove(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        presenter.onMove(Direction.DOWN);
        repaint();
    }
}

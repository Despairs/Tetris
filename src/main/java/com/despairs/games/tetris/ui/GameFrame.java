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

        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(statistic, BorderLayout.AFTER_LINE_ENDS);
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

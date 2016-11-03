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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author EKovtunenko
 */
public class GameFrame extends JFrame implements GameView, KeyListener, ActionListener, MouseListener {

    private Timer timer;
    private GameBoard board;
    private GameStatistic statistic;
    private JLabel scores;
    private JLabel help;

    private boolean inited = false;
    private boolean isGameOver = false;
    private boolean isGamePaused = false;

    private GamePresenter presenter;

    public void start() {
        initEngine();
        initUI();
        setVisible(true);
        startTimer();
        inited = true;
    }

    private void initEngine() {
        if (!inited) {
            addKeyListener(this);
            addMouseListener(this);
        }
        timer = new Timer(500, this);
        presenter = new GamePresenter(this);
    }

    @Override
    public void setScore(Long score) {
        scores.setText("Current scores: " + score);
    }

    public void initUI() {
        board = new GameBoard(presenter);
        statistic = new GameStatistic(presenter);
        if (!inited) {
            scores = new JLabel();
            help = new JLabel(AppConfig.HELP);
        }

        help.setHorizontalAlignment(JLabel.LEFT);
        help.setHorizontalTextPosition(JLabel.LEFT);

        board.setPreferredSize(new Dimension(AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT));
        board.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, AppConfig.BORDER_SIZE));

        statistic.setPreferredSize(new Dimension(AppConfig.STATISTIC_WIDTH, AppConfig.STATISTIC_HEIGHT));
        statistic.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, AppConfig.BORDER_SIZE));
        statistic.setLayout(new javax.swing.BoxLayout(statistic, javax.swing.BoxLayout.Y_AXIS));

        statistic.add(scores);
        statistic.add(help);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(statistic, BorderLayout.AFTER_LINE_ENDS);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((!isGamePaused || e.getKeyCode() == KeyEvent.VK_P) && (!isGameOver || e.getKeyCode() == KeyEvent.VK_SPACE)) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    if (!isGamePaused) {
                        stopTimer();
                    } else {
                        startTimer();
                    }
                    isGamePaused = !isGamePaused;
                    break;
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
                    presenter.onStartNewGame();
                    startNewGame();
                    break;
            }
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

    private void stopTimer() {
        timer.stop();
    }

    private void startTimer() {
        timer.start();
    }

    private void startNewGame() {
        isGameOver = false;
        stopTimer();
        timer = null;
        setScore(0L);
        start();
    }

    @Override
    public void stopGame() {
        isGameOver = true;
        timer.stop();
        int dialogOption = JOptionPane.showConfirmDialog(
                this,
                "Игра завершена\nЖелаете начать снова?",
                null,
                JOptionPane.YES_NO_OPTION);
        if (dialogOption == JOptionPane.OK_OPTION) {
            presenter.onStartNewGame();
            startNewGame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       presenter.onRotate();
       repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { 
    }

    @Override
    public void mouseReleased(MouseEvent e) {   
    }

    @Override
    public void mouseEntered(MouseEvent e) {     
    }

    @Override
    public void mouseExited(MouseEvent e) {      
    }
}

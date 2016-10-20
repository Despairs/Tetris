/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.presenter.GameBoardPresenter;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameBoard extends JPanel {

    private final GameBoardPresenter presenter;

    public GameBoard() {
        this.presenter = new GameBoardPresenter();
    }

    @Override
    public void paintComponent(Graphics g) {
        presenter.onPaintComponent(g);
    }

    public void move(Direction direction) {
        presenter.onMove(direction);
    }

    public void rotate() {
        presenter.onRotate();
    }
}

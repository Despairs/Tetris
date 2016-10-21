/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.presenter.GamePresenter;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameStatistic extends JPanel {

    private final GamePresenter presenter;

    public GameStatistic(GamePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void paintComponent(Graphics g) {
//        presenter.onPaintStatisticComponent(g);
    }

}

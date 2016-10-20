/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.presenter;

import com.despairs.games.tetris.model.Figure;
import com.despairs.games.tetris.utils.GameStatisticView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 *
 * @author EKovtunenko
 */
public class GameStatisticPresenter {

    private Figure nextFigure;
    private final GameStatisticView view;
    
    public GameStatisticPresenter(GameStatisticView view) {
        this.view = view;
    }

    private Long score = 0L;

    public void onPaintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (nextFigure != null) {
            for (Shape s : nextFigure.getUnits()) {
                g2d.setColor(nextFigure.getColor());
                g2d.fill(s);
                g2d.setColor(Color.BLACK);
                g2d.draw(s);
            }
        }
    }

    public void onRowDelete() {
        score += 100;
        view.setScore(score);
    }

    public void onFigureDrop() {
        score += 10;
        view.setScore(score);
    }
    
    public void onGameOver() {
        score = -666L;
        view.setScore(score);
    }

    public void onNewFigureGenerate(Figure figure) {
        nextFigure = figure;
    }
}

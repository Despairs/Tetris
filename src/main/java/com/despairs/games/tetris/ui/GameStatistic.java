/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.model.BaseFigure;
import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.utils.FigureFactory;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameStatistic extends JPanel {
    
    private BaseFigure currentFigure;

    @Override
    public void paintComponent(Graphics g) {
//        if (currentFigure == null) {
//            currentFigure = FigureFactory.getRandomFigure();
//        }
//        currentFigure.paint(g);
    }

}

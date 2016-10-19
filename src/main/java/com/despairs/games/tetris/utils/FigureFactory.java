/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

import com.despairs.games.tetris.model.BaseFigure;
import com.despairs.games.tetris.model.IFigure;
import com.despairs.games.tetris.model.LFigure;
import com.despairs.games.tetris.model.OFigure;
import com.despairs.games.tetris.model.SFigure;
import com.despairs.games.tetris.model.TFigure;
import java.awt.Shape;

/**
 *
 * @author EKovtunenko
 */
public class FigureFactory {

    public static Shape getRandomFigure() {
        BaseFigure figure = null;
        int id = (int) (Math.random() * 4 + 1);
        switch (id) {
            case 1:
                figure = new IFigure();
                break;
            case 2:
                figure = new LFigure();
                break;
            case 3:
                figure = new OFigure();
                break;
            case 4:
                figure = new SFigure();
                break;
            case 5:
                figure = new TFigure();
                break;
        }
        return figure.getFigure();
    }
}

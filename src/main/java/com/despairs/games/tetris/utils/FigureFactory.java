/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

import com.despairs.games.tetris.model.Figure;
import com.despairs.games.tetris.model.IFigure;
import com.despairs.games.tetris.model.JFigure;
import com.despairs.games.tetris.model.LFigure;
import com.despairs.games.tetris.model.OFigure;
import com.despairs.games.tetris.model.SFigure;
import com.despairs.games.tetris.model.TFigure;
import com.despairs.games.tetris.model.OFigure;
import com.despairs.games.tetris.model.ZFigure;

/**
 *
 * @author EKovtunenko
 */
public class FigureFactory {

    public static Figure getRandomFigure() {
        Figure figure = null;
        int id = (int) (Math.random() * 6 + 1);
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
            case 6:
                figure = new ZFigure();
                break;
            case 7:
                figure = new JFigure();
                break;
        }
        return figure;
    }
}

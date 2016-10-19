/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.model;

import com.despairs.games.tetris.utils.AppConfig;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author EKovtunenko
 */
public class TFigure extends BaseFigure {

    public TFigure() {
        figures.add(new Rectangle(AppConfig.START_POSITION, 0, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE));
        figures.add(new Rectangle(AppConfig.START_POSITION - AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE));
        figures.add(new Rectangle(AppConfig.START_POSITION, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE));
        figures.add(new Rectangle(AppConfig.START_POSITION + AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE, AppConfig.BLOCK_SIZE));
    }

    @Override
    protected Shape getRotateFigure() {
        return figures.get(2);
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}

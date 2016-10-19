/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.model;

import java.awt.Polygon;

/**
 *
 * @author EKovtunenko
 */
public class IFigure extends BaseFigure {

    public IFigure() {
        ((Polygon) figure).addPoint(AppConfig.START_POSITION, AppConfig.START_POSITION);
        ((Polygon) figure).addPoint(AppConfig.START_POSITION + AppConfig.BLOCK_SIZE, AppConfig.START_POSITION);
        ((Polygon) figure).addPoint(AppConfig.START_POSITION + AppConfig.BLOCK_SIZE, AppConfig.START_POSITION + 4 * AppConfig.BLOCK_SIZE);
        ((Polygon) figure).addPoint(AppConfig.START_POSITION, AppConfig.START_POSITION + 4 * AppConfig.BLOCK_SIZE);
        ((Polygon) figure).addPoint(AppConfig.START_POSITION, AppConfig.START_POSITION);
    }

}

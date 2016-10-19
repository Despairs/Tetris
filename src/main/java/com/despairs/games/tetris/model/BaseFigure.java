/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.model;

import java.awt.Polygon;
import java.awt.Shape;

/**
 *
 * @author EKovtunenko
 */
public class BaseFigure {

    protected Shape figure = new Polygon();

    public Shape getFigure() {
        return figure;
    }
}

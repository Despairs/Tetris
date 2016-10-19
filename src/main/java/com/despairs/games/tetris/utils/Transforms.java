/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

import com.despairs.games.tetris.model.BaseFigure;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EKovtunenko
 */
public class Transforms {

    public static BaseFigure rotate(BaseFigure figure, double angle) throws CloneNotSupportedException {
        BaseFigure ret = figure;
        if (figure.isRotateAllowed()) {
            Point rotatePoint = figure.getRotatePoint();
            List<Shape> l = new ArrayList<>();
            for (Shape s : figure.getFigures()) {
                AffineTransform at = AffineTransform.getRotateInstance(angle, rotatePoint.getX(), rotatePoint.getY());
                at.translate(-s.getBounds().getHeight(), 0);
                l.add(at.createTransformedShape(s));
            }
            ret.getFigures().clear();
            ret.getFigures().addAll(l);
        }
        return ret;
    }

    public static BaseFigure translate(BaseFigure figure, double x, double y) throws CloneNotSupportedException {
        BaseFigure ret = figure;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        List<Shape> l = new ArrayList<>();
        for (Shape s : figure.getFigures()) {
            l.add(at.createTransformedShape(s));
        }
        ret.getFigures().clear();
        ret.getFigures().addAll(l);
        return ret;
    }
}

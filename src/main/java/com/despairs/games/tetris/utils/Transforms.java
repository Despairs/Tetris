/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

import com.despairs.games.tetris.model.Figure;
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

    public static Figure rotate(Figure figure, double angle) {
        Figure ret = figure;
        if (figure.isRotateAllowed()) {
            Point rotatePoint = figure.getRotatePoint();
            List<Shape> l = new ArrayList<>();
            for (Shape s : figure.getUnits()) {
                AffineTransform at = AffineTransform.getRotateInstance(angle, rotatePoint.getX(), rotatePoint.getY());
                at.translate(-s.getBounds().getHeight(), 0);
                l.add(at.createTransformedShape(s));
            }
            ret.getUnits().clear();
            ret.getUnits().addAll(l);
        }
        return ret;
    }

    public static Figure translate(Figure figure, double x, double y) {
        Figure ret = figure;
        if (figure != null) {
            List<Shape> l = new ArrayList<>();
            for (Shape s : figure.getUnits()) {
                l.add(translate(s, x, y));
            }
            ret.getUnits().clear();
            ret.getUnits().addAll(l);
        }
        return ret;
    }

    public static Shape translate(Shape figure, double x, double y) {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        return at.createTransformedShape(figure);
    }
}

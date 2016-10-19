/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author EKovtunenko
 */
public class BaseFigure implements Cloneable {

    protected List<Shape> figures = new CopyOnWriteArrayList<>();

    public Point getRotatePoint() {
        Rectangle bounds = getRotateFigure().getBounds();
        Point point = new Point();
        point.setLocation(bounds.getX(), bounds.getY() + bounds.getHeight());
        return point;
    }

    protected Shape getRotateFigure() {
        return figures.get(0);
    }

    public boolean isRotateAllowed() {
        return true;
    }

    public Color getColor() {
        return Color.BLACK;
    }

    public boolean intersects(BaseFigure target) {
        boolean ret = false;
        for (Shape s : figures) {
            for (Shape t : target.getFigures()) {
                if (s.intersects(t.getBounds2D())) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    public boolean intersectsLine(double x0, double y0, double x1, double y1) {
        boolean ret = false;
        for (Shape s : figures) {
            Rectangle2D bounds = s.getBounds2D();
            if (bounds.intersectsLine(x0, y0, x1, y1)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public List<Shape> getFigures() {
        return figures;
    }
}

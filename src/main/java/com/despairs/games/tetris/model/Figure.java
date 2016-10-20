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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author EKovtunenko
 */
public abstract class Figure implements Cloneable {

    protected List<Shape> units = new CopyOnWriteArrayList<>();

    protected abstract Shape getRotateFigure();

    public abstract boolean isRotateAllowed();

    public abstract Color getColor();

    public boolean intersects(Figure target) {
        boolean ret = false;
        for (Shape s : units) {
            for (Shape t : target.getUnits()) {
                Rectangle _s = s.getBounds();
                Rectangle _t = t.getBounds();
                if (_s.x <= (_t.x + _t.width) && (_s.x + _s.width) >= _t.x && _s.y <= (_t.y + _t.height) && (_s.y + _s.height) >= _t.y) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    public boolean intersectsLine(double x0, double y0, double x1, double y1) {
        boolean ret = false;
        for (Shape s : units) {
            Rectangle2D bounds = s.getBounds2D();
            if (bounds.intersectsLine(x0, y0, x1, y1)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public Point getRotatePoint() {
        Rectangle bounds = getRotateFigure().getBounds();
        Point point = new Point();
        point.setLocation(bounds.getX(), bounds.getY() + bounds.getHeight());
        return point;
    }

    public List<Shape> getUnits() {
        return units;
    }

}

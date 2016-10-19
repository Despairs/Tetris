/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 *
 * @author EKovtunenko
 */
public class Transforms {

    public static Shape rotate(Shape shape, double angle) {
        Rectangle bounds = shape.getBounds();
        AffineTransform at = AffineTransform.getRotateInstance(angle, bounds.getX(), bounds.getY() + bounds.getHeight());
        at.translate(-bounds.getHeight(), 0);
        return at.createTransformedShape(shape);
    }

    public static Shape translate(Shape shape, double x, double y) {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        return at.createTransformedShape(shape);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.model.AppConfig;
import com.despairs.games.tetris.model.BaseFigure;
import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.utils.FigureFactory;
import com.despairs.games.tetris.utils.Transforms;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameBoard extends JPanel {

    private Shape currentFigure;

    private final List<Shape> figures;

    public GameBoard() {
        if (currentFigure == null) {
            currentFigure = FigureFactory.getRandomFigure();
        }
        figures = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill(currentFigure);
        for (Shape s : figures) {
            g2d.fill(s);
        }
    }

    public void move(Direction direction) {
        double x = 0;
        double y = 0;
        switch (direction) {
            case DOWN:
                y = AppConfig.BLOCK_SIZE;
                break;
            case LEFT:
                x = -AppConfig.BLOCK_SIZE;
                break;
            case RIGHT:
                x = AppConfig.BLOCK_SIZE;
                break;
        }
        boolean blocked = false;
        for (Shape s : figures) {
            if (currentFigure.getBounds2D().intersects(s.getBounds2D())) {
                System.out.println("Figure");
                blocked = true;
                figures.add(currentFigure);
                currentFigure = FigureFactory.getRandomFigure();
            }
        }
        if (currentFigure.intersects(AppConfig.BOARD_WIDTH, 0, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.RIGHT)) {
            System.out.println("Right border");
            blocked = true;
        }
        if (currentFigure.intersects(-AppConfig.BOARD_WIDTH, 0, AppConfig.BOARD_WIDTH + AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.LEFT)) {
            System.out.println("Left border");
            blocked = true;
        }
        if (currentFigure.intersects(0, AppConfig.BOARD_HEIGHT, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.DOWN)) {
            System.out.println("Bottom border");
            blocked = true;
            figures.add(currentFigure);
            currentFigure = FigureFactory.getRandomFigure();
        }
        if (!blocked) {
            currentFigure = Transforms.translate(currentFigure, x, y);
        }
    }

    public void rotate() {
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
    }
}

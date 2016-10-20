/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.model.Figure;
import com.despairs.games.tetris.utils.AppConfig;
import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.utils.FigureFactory;
import com.despairs.games.tetris.utils.Transforms;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameBoard extends JPanel {

    private Figure currentFigure;

    private final List<Figure> figures;

    public GameBoard() {
        if (currentFigure == null) {
            currentFigure = FigureFactory.getRandomFigure();
        }
        figures = new CopyOnWriteArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Shape s : currentFigure.getUnits()) {
            g2d.setColor(currentFigure.getColor());
            g2d.fill(s);
            g2d.setColor(Color.BLACK);
            g2d.draw(s);

        }
        for (Figure figure : figures) {
            for (Shape s : figure.getUnits()) {
                g2d.setColor(figure.getColor());
                g2d.fill(s);
                g2d.setColor(Color.BLACK);
                g2d.draw(s);
            }
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
        boolean createNewFigure = false;
//        currentFigure = Transforms.translate(currentFigure, x, y);
        for (Figure f : figures) {
            if (currentFigure.intersects(f)) {
                System.out.println("Figure");
                blocked = true;
                if (direction.equals(Direction.DOWN)) {
                    createNewFigure = true;
                }
            }
        }
        if (currentFigure.intersectsLine(AppConfig.BOARD_WIDTH, 0, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.RIGHT)) {
            System.out.println("Right border");
            blocked = true;
        }
        if (currentFigure.intersectsLine(0, 0, 0, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.LEFT)) {
            System.out.println("Left border");
            blocked = true;
        }
        if (currentFigure.intersectsLine(0, AppConfig.BOARD_HEIGHT, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.DOWN)) {
            System.out.println("Bottom border");
            createNewFigure = true;
        }
        if (!blocked) {
            currentFigure = Transforms.translate(currentFigure, x, y);
        }
        if (createNewFigure) {
            figures.add(currentFigure);
            currentFigure = FigureFactory.getRandomFigure();
            deleteRow();
        }
    }

    public void rotate() {
        
        for (Figure f : figures) {
            if (currentFigure.intersects(f)) {
                System.out.println("Rotate: Figure");
            }
        }
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
        while (currentFigure.intersectsLine(AppConfig.BOARD_WIDTH + AppConfig.BLOCK_SIZE, 0, AppConfig.BOARD_WIDTH + AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT)) {
            System.out.println("Rotate: Right border");
            currentFigure = Transforms.translate(currentFigure, -AppConfig.BLOCK_SIZE, 0);
        }
        while (currentFigure.intersectsLine(-AppConfig.BLOCK_SIZE, 0, -AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT)) {
            System.out.println("Rotate: Left border");
            currentFigure = Transforms.translate(currentFigure, AppConfig.BLOCK_SIZE, 0);
        }

    }

    private void deleteRow() {
        Map<Double, Double> board = new HashMap<>();
        for (Figure figure : figures) {
            for (Shape s : figure.getUnits()) {
                Rectangle bounds = s.getBounds();
                Double width = board.get(bounds.getY());
                if (width == null) {
                    width = new Double(0);
                }
                board.put(bounds.getY(), width + bounds.getWidth());
            }
        }
        for (Map.Entry<Double, Double> entrySet : board.entrySet()) {
            if (entrySet.getValue().intValue() >= AppConfig.BOARD_WIDTH) {
                for (Figure figure : figures) {
                    for (Shape s : figure.getUnits()) {
                        if (s.getBounds().getY() == entrySet.getKey()) {
                            figure.getUnits().remove(s);
                        } else if (s.getBounds().getY() < entrySet.getKey()) {
                            figure.getUnits().add(Transforms.translate(s, 0, AppConfig.BLOCK_SIZE));
                            figure.getUnits().remove(s);
                        }
                    }
                }
            }
        }
    }
}

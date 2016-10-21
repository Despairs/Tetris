/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.presenter;

import com.despairs.games.tetris.event.EventManager;
import com.despairs.games.tetris.event.EventType;
import com.despairs.games.tetris.model.Direction;
import com.despairs.games.tetris.model.Figure;
import com.despairs.games.tetris.utils.AppConfig;
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
import com.despairs.games.tetris.view.GameView;

/**
 *
 * @author EKovtunenko
 */
public class GamePresenter {

    private Figure currentFigure;

    private Long score = 0L;

    private final GameView view;

    private final List<Figure> figures;

    public GamePresenter(GameView view) {
        this.view = view;
        if (currentFigure == null) {
            currentFigure = FigureFactory.getRandomFigure();
        }
        figures = new CopyOnWriteArrayList<>();
    }

    public void onPaintBoardComponent(Graphics g) {
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

    public void onPaintStatisticComponent(Graphics g) {
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

    public void onMove(Direction direction) {
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
        currentFigure = Transforms.translate(currentFigure, x, y);
        for (Figure f : figures) {
            if (currentFigure.intersects(f)) {
                System.out.println("Figure");
                blocked = true;
                if (direction.equals(Direction.DOWN)) {
                    createNewFigure = true;
                }
            }
        }
        if (currentFigure.intersectsLine(AppConfig.BOARD_WIDTH + AppConfig.BLOCK_SIZE, 0, AppConfig.BOARD_WIDTH + AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.RIGHT)) {
            System.out.println("Right border");
            blocked = true;
        }
        if (currentFigure.intersectsLine(-AppConfig.BLOCK_SIZE, 0, -AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT) && direction.equals(Direction.LEFT)) {
            System.out.println("Left border");
            blocked = true;
        }
        if (currentFigure.intersectsLine(0, AppConfig.BOARD_HEIGHT + AppConfig.BLOCK_SIZE, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT + AppConfig.BLOCK_SIZE) && direction.equals(Direction.DOWN)) {
            System.out.println("Bottom border");
            createNewFigure = true;
            blocked = true;
        }
        if (blocked) {
            currentFigure = Transforms.translate(currentFigure, -x, -y);
        }
        if (createNewFigure) {
            increaseScore(10L);
            figures.add(currentFigure);
            currentFigure = FigureFactory.getRandomFigure();
            view.setScore(score);
            deleteFilledRows();
        }
    }

    public void onRotate() {
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
        boolean blocked = false;
        for (Figure f : figures) {
            if (currentFigure.intersects(f)) {
                System.out.println("Rotate: Figure");
                blocked = true;
            }
        }
        if (currentFigure.intersectsLine(AppConfig.BOARD_WIDTH, 0, AppConfig.BOARD_WIDTH, AppConfig.BOARD_HEIGHT)) {
            System.out.println("Rotate: Right border");
            blocked = true;
        }
        if (currentFigure.intersectsLine(-AppConfig.BLOCK_SIZE, 0, -AppConfig.BLOCK_SIZE, AppConfig.BOARD_HEIGHT)) {
            System.out.println("Rotate: Left border");
            blocked = true;
        }
        if (blocked) {
            //Лайфхак
            tripleRotate();
        }
    }

    private void deleteFilledRows() {
        Map<Double, Double> rows = new HashMap<>();
        for (Figure figure : figures) {
            for (Shape s : figure.getUnits()) {
                Rectangle bounds = s.getBounds();
                Double width = rows.get(bounds.getY());
                if (width == null) {
                    width = new Double(0);
                }
                rows.put(bounds.getY(), width + bounds.getWidth());
            }
        }
        for (Map.Entry<Double, Double> row : rows.entrySet()) {
            if (row.getValue().intValue() >= AppConfig.BOARD_WIDTH) {
                for (Figure figure : figures) {
                    for (Shape s : figure.getUnits()) {
                        if (s.getBounds().getY() == row.getKey()) {
                            figure.getUnits().remove(s);
                        } else if (s.getBounds().getY() < row.getKey()) {
                            figure.getUnits().add(Transforms.translate(s, 0, AppConfig.BLOCK_SIZE));
                            figure.getUnits().remove(s);
                        }
                    }
                }
                increaseScore(100L);
            }
        }
    }

    private void tripleRotate() {
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
        currentFigure = Transforms.rotate(currentFigure, Math.toRadians(90));
    }

    private void increaseScore(Long value) {
        score += value;
        view.setScore(score);
    }
}

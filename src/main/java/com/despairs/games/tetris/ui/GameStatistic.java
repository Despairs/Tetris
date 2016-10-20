/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.ui;

import com.despairs.games.tetris.event.EventManager;
import com.despairs.games.tetris.event.EventManager.EventReceiver;
import com.despairs.games.tetris.event.EventType;
import com.despairs.games.tetris.model.Figure;
import com.despairs.games.tetris.presenter.GameStatisticPresenter;
import com.despairs.games.tetris.utils.GameStatisticView;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author EKovtunenko
 */
public class GameStatistic extends JPanel implements EventReceiver, GameStatisticView {

    private final GameStatisticPresenter presenter;
    
    private final JLabel scoresLabel; 

    public GameStatistic() {
        presenter = new GameStatisticPresenter(this);
        scoresLabel = new JLabel();
        EventManager.getInstance().registerReceiver(EventType.GAME_OVER, this);
        EventManager.getInstance().registerReceiver(EventType.FIGURE_DROPPED, this);
        EventManager.getInstance().registerReceiver(EventType.NEXT_FIGURE_GENERATED, this);
        EventManager.getInstance().registerReceiver(EventType.ROW_DELETED, this);
    }

    @Override
    public void paintComponent(Graphics g) {
        presenter.onPaintComponent(g);
    }

    public void rowDeleted() {
        presenter.onRowDelete();
    }

    public void figureDropped() {
        presenter.onFigureDrop();
    }

    @Override
    public void onEventReceive(int eventId, Object... data) {
        System.out.println("Receive event: " + eventId);
        switch (eventId) {
            case EventType.FIGURE_DROPPED:
                presenter.onFigureDrop();
                break;
            case EventType.ROW_DELETED:
                presenter.onRowDelete();
                break;
            case EventType.NEXT_FIGURE_GENERATED:
                presenter.onNewFigureGenerate((Figure) data[0]);
                break;
            case EventType.GAME_OVER:
                presenter.onGameOver();
                break;

        }
    }

    @Override
    public void setScore(Long score) {
        scoresLabel.setText("Очки: " + score.toString());
    }

    public JLabel getScoresLabel() {
        return scoresLabel;
    }

}

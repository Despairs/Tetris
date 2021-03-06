/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.utils;

/**
 *
 * @author EKovtunenko
 */
public class AppConfig {

    public static final int BLOCK_SIZE = 30;
    public static final int BORDER_SIZE = 1;

    public static final int BOARD_WIDTH = 15 * AppConfig.BLOCK_SIZE;
    public static final int BOARD_HEIGHT = 20 * AppConfig.BLOCK_SIZE;

    public static final int STATISTIC_HEIGHT = BOARD_HEIGHT;
    public static final int STATISTIC_WIDTH = 6 * AppConfig.BLOCK_SIZE;

    public static final int FRAME_WIDTH = BOARD_WIDTH + STATISTIC_WIDTH;
    public static final int FRAME_HEIGHT = BOARD_HEIGHT;

    public static final int START_POSITION = ((BOARD_WIDTH / BLOCK_SIZE) / 2) * AppConfig.BLOCK_SIZE;
    public static final int HALF_BLOCK_SIZE = BLOCK_SIZE / 2;

    public static final long FIGURE_COST = 10L;
    public static final long ROW_COST = 10L;

    public static final String HELP = "<html>"
            + "<hr/>Справка:<hr/>"
            + "Вверх - повернуть<br/>"
            + "P - пауза<br/>"
            + "Пробел - новая игра<br/>"
            + "</html>";
}

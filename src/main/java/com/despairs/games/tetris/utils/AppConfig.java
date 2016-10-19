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

    public static int BLOCK_SIZE = 30;

    public static final int BORDER_SIZE = 1;
    public static final int BOARD_WIDTH = 15 * AppConfig.BLOCK_SIZE - 2 * BORDER_SIZE;
    public static final int BOARD_HEIGHT = 30 * AppConfig.BLOCK_SIZE - 2 * BORDER_SIZE;
    public static int START_POSITION = ((BOARD_WIDTH / BLOCK_SIZE) / 2) * AppConfig.BLOCK_SIZE;

}

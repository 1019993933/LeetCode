package com.study.leetcode.solutions;

import java.util.ArrayList;

//螺旋矩阵
public class NC38 {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> data = new ArrayList<>();
        if (matrix != null || matrix.length > 0) {
            doSpiralOrder(data, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
            return data;
        }


        return data;
    }

    public void doSpiralOrder(ArrayList<Integer> data, int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        if (startRow > endRow || startCol > endCol) {
            return;
        }

        addLine(data, matrix, startRow, startCol, endCol);
        if (endRow - startRow >= 1) {
            addColumn(data, matrix, endCol, startRow + 1, endRow - 1);
            addRLine(data, matrix, endRow, startCol, endCol);
            if (endRow - startRow > 1 && endCol - startCol >= 1) {
                addRColumn(data, matrix, startCol, startRow + 1, endRow - 1);
            }
        }

        if (endRow - startRow <= 1 || endCol - startCol <= 1) {
            return;
        }

        //除去上边的几个条件后，去除四边
        doSpiralOrder(data, matrix, startRow + 1, endRow - 1, startCol + 1, endCol - 1);
    }

    public void addLine(ArrayList<Integer> data, int[][] matrix, int row, int startCol, int endCol) {
        for (int i = startCol; i <= endCol; ++i) {
            data.add(matrix[row][i]);
        }
    }

    public void addRLine(ArrayList<Integer> data, int[][] matrix, int row, int startCol, int endCol) {
        for (int i = endCol; i >= startCol; --i) {
            data.add(matrix[row][i]);
        }
    }

    public void addColumn(ArrayList<Integer> data, int[][] matrix, int row, int startCol, int endCol) {
        for (int i = startCol; i <= endCol; ++i) {
            data.add(matrix[i][row]);
        }
    }

    public void addRColumn(ArrayList<Integer> data, int[][] matrix, int row, int startCol, int endCol) {
        for (int i = endCol; i >= startCol; --i) {
            data.add(matrix[i][row]);
        }
    }
}

package com.nkrasnovoronka.util;

import com.nkrasnovoronka.model.Problem;

public class ShortestPath {
    public static int findShortestPaths(int numberOfLocations, Problem problem, int[][] sumMatrix) {
        int[] minDistance = new int[numberOfLocations];
        boolean[] visited = new boolean[numberOfLocations];
        final int INFINITY = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfLocations; i++) {
            minDistance[i] = INFINITY;
            visited[i] = false;
        }
        minDistance[problem.getFromId() - 1] = 0;

        int minIndex;
        int min;

        do {
            min = INFINITY;
            minIndex = INFINITY;

            for (int i = 0; i < numberOfLocations; i++) {
                if (!visited[i] && minDistance[i] < min) {
                    min = minDistance[i];
                    minIndex = i;
                }
            }

            if (minIndex != INFINITY) {
                for (int i = 0; i < numberOfLocations; i++) {
                    if (sumMatrix[minIndex][i] > 0) {
                        int temp = min + sumMatrix[minIndex][i];
                        if (temp < minDistance[i]) {
                            minDistance[i] = temp;
                        }
                    }
                }
                visited[minIndex] = true;
            }

        } while (minIndex < INFINITY);

        return minDistance[problem.getToId() - 1];

    }
}

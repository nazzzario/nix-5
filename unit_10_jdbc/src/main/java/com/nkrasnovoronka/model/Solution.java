package com.nkrasnovoronka.model;

import java.util.Objects;

public class Solution {
    private int id;
    private int cost;

    public Solution() {
    }

    public Solution(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return id == solution.id && cost == solution.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}

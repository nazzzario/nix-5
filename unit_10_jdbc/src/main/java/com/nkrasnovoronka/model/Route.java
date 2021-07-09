package com.nkrasnovoronka.model;

import java.util.Objects;

public class Route {
    private int id;
    private int fromId;
    private int toId;
    private int cost;

    public Route() {
    }

    public Route(int fromId, int toId, int cost) {
        this.fromId = fromId;
        this.toId = toId;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
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
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return id == route.id && fromId == route.fromId && toId == route.toId && cost == route.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromId, toId, cost);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", cost=" + cost +
                '}';
    }
}

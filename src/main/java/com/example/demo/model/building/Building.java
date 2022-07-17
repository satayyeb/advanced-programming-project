package com.example.demo.model.building;

import com.example.demo.model.Producible;
import com.example.demo.model.tiles.Tile;

public class Building implements Producible {
    private final BuildingType buildingType;
    private final Tile tile;
    int remainedCost;

    public Building(BuildingType buildingType,Tile tile) {
        this.buildingType = buildingType;
        remainedCost = buildingType.getCost();
        this.tile=tile;
    }

    @Override
    public String getName() {
        return buildingType.toString();
    }

    @Override
    public int getRemainedCost() {
        return remainedCost;
    }

    @Override
    public void setRemainedCost(int remainedCost) {
        this.remainedCost = remainedCost;
    }

    @Override
    public int getCost() {
        return buildingType.getCost();
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Tile getTile() {
        return tile;
    }
}

package model.tiles;

import model.City;
import model.Civilization;
import model.Units.*;
import model.features.Feature;
import model.features.FeatureType;
import model.improvements.Improvement;
import model.resources.ResourcesTypes;
import model.technologies.Technology;

import java.util.ArrayList;

public class Tile {
    private boolean[] tilesWithRiver = new boolean[6];
    private TileType tileType;
    private ResourcesTypes containedResource;
    private Feature containedFeature;
    private Improvement improvement;
    private final int x;
    private final int y;
    private Civilization civilization;
    private Unit civilian;
    private NonCivilian nonCivilian;
    private City city;
    private int hasRoad;
    private int raidLevel;

    private final Tile[] neighbours = new Tile[6];// LU, clockwise

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public City getCity() {
        return city;
    }

    public int getMovingPrice() {
        if(containedFeature!=null)
            return tileType.movementPoint + containedFeature.getFeatureType().movePoint;
        return tileType.movementPoint;
    }

    public Unit getCivilian() {
        return civilian;
    }

    public NonCivilian getNonCivilian() {
        return nonCivilian;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public Tile getNeighbours(int i) {
        if(i >= 0 && i < 6){
            return neighbours[i];
        }
        return null;
    }
    public void setTilesWithRiver(int i){
        if(i >= 0 && i < 6){
            tilesWithRiver[i] = true;
        }
    }

    public boolean isRiverWithNeighbour(int i ){
        if(i >= 0 && i < 6){
            return tilesWithRiver[i];
        }
        return false;
    }

    public void setNeighbours(int i, Tile tile) {
        neighbours[i] = tile;
    }

    public TileType getTileType() {
        return tileType;
    }
    public FeatureType getFeatureType() {
        return containedFeature.getFeatureType();
    }

    public ResourcesTypes getResources() {
        return containedResource;
    }

    public Tile(TileType tileType,int x, int y){
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }
    public void setFeature(Feature feature){
        this.containedFeature = feature;
    }



    public boolean isFeatureTypeValid(FeatureType featureType){
        FeatureType[] list = tileType.featureTypes;
        for (FeatureType validFeatureType : list) {
            if (validFeatureType == featureType) {
                if(featureType == FeatureType.DELTA) {
                    for (int i = 0; i < 6; i++) {
                        if(tilesWithRiver[i]) return true;
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    public boolean isResourceTypeValid(ResourcesTypes resourcesType){
        ResourcesTypes[] list1 = tileType.resourcesTypes;
        ResourcesTypes[] list2 = new ResourcesTypes[1];
        if(containedFeature != null)  list2 = containedFeature.getFeatureType().resourcesTypes;
        for (ResourcesTypes types : list1) {
            if(types == resourcesType) return true;
        }
        for (ResourcesTypes types : list2) {
            if(types == resourcesType) return true;
        }
        return false;
    }
    public void setResource(ResourcesTypes resource){
        this.containedResource = resource;
    }

    public void setCivilian(Unit unit) {
        if(unit==null)
            this.civilian=null;
        else if(unit.getUnitType().combatType== CombatType.CIVILIAN)
            this.civilian = unit;
    }

    public void setNonCivilian(NonCivilian nonCivilian) {
        this.nonCivilian = nonCivilian;
    }

    public Tile CloneTileForCivilization(ArrayList<Technology> technologies) {
        Tile newTile = new Tile(this.tileType, this.x, this.y);
        newTile.tilesWithRiver = this.tilesWithRiver;
        newTile.containedResource = null;
        if(containedResource != null && containedResource.isTechnologyUnlocked(technologies))
            newTile.containedResource = this.containedResource;
        newTile.improvement = this.improvement;
        newTile.civilization = this.civilization;
        newTile.civilian = this.civilian;
        newTile.nonCivilian = this.nonCivilian;
        newTile.city = this.city;
        newTile.hasRoad = this.hasRoad;
        newTile.raidLevel = this.raidLevel;
        return newTile;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public Feature getContainedFeature() {
        return containedFeature;
    }
}

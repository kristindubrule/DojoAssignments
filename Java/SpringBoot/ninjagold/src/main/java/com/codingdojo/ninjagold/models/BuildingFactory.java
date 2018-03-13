package com.codingdojo.ninjagold.models;

public class BuildingFactory {
    public Building getBuilding(String buildingType) {
        if(buildingType == null) {
            return null;
        }
        if(buildingType.equalsIgnoreCase("farm")) {
            return new Farm();
        } else if (buildingType.equalsIgnoreCase("cave")) {
            return new Cave();
        } else if (buildingType.equalsIgnoreCase("house")) {
            return new House();
        } else if (buildingType.equalsIgnoreCase("casino")) {
            return new Casino();
        }
        return null;
    }
}

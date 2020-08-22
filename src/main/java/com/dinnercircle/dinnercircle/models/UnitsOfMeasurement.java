package com.dinnercircle.dinnercircle.models;


public enum UnitsOfMeasurement {

    TEASPOON("Teaspoon(tsp. or t)"),
    TABLESPOON("Tablespoon(tbsp. or T)"),
    CUP("Cup(C)"),
    PINT("Pint(pt.)"),
    QUART("Quart(qt.)"),
    GALLON("Gallon(gal.)"),
    PINCH("Pinch(pn.)"),
    DASH("Dash(ds.)"),
    OUNCE("Ounce(oz.)"),
    POUND("Pound(lb.)");

    private final String displayName;

    UnitsOfMeasurement(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}

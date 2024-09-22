package com.ridango.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cocktail {

    @JsonProperty("strDrink")
    private String strDrink;

    @JsonProperty("strInstructions")
    private String strInstructions;

    @JsonProperty("strCategory")
    private String strCategory;

    @JsonProperty("strGlass")
    private String strGlass;

    @JsonProperty("strIngredient1")
    private String strIngredient1;

    @JsonProperty("strIngredient2")
    private String strIngredient2;

    @JsonProperty("strIngredient3")
    private String strIngredient3;

    @JsonProperty("strIngredient4")
    private String strIngredient4;

    // Getters and setters
    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }
}

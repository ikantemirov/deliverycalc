package io.kantemirov;

enum Size {

    SMALL(100f),
    BIG(200f);

    private float cost;

    Size(float c) {
        cost = c;
    }

    public float getCost() {
        return cost;
    }
}

package io.kantemirov;

import static io.kantemirov.DeliveryCalculator.MAX_DISTANCE_DELIVERY;

public enum DeliveryZone {

    FIRST(2, 50f),
    SECOND(10, 100f),
    THIRD(30, 200f),
    FOURTH(MAX_DISTANCE_DELIVERY, 300f);

    private final int maxDistance;
    private final float cost;

    DeliveryZone(int max, float c) {
        maxDistance = max;
        cost = c;
    }

    public float getCost() {
        return cost;
    }

    public int getMaxDistance() {
        return maxDistance;
    }
}

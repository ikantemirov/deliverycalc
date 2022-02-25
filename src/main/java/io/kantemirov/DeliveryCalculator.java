package io.kantemirov;

import java.util.Arrays;

public class DeliveryCalculator {

    public static final float MINIMAL_COST = 400f;
    public static final int MAX_DISTANCE_DELIVERY = 100;
    public static final int FRAGILE_MAX_ZONE_KM = 30;
    public static final float FRAGILE_COST = 300f;
    public static final float ZERO_COST = 0.0f;

    private float cost = 0.0f;
    private float fragileCost = 0.0f;

    public float calculate(int deliveryZoneKm,
                           Size size,
                           boolean fragile,
                           LoadMultiplier loadMultiplier) {

        if (deliveryZoneKm <= 0
                || deliveryZoneKm > MAX_DISTANCE_DELIVERY
                || fragile
                && (deliveryZoneKm > FRAGILE_MAX_ZONE_KM)) {
            return ZERO_COST;
        }

        float distanceCost = Arrays.stream(DeliveryZone.values())
                .filter(v -> deliveryZoneKm <= v.getMaxDistance())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UNEXPECTED DELIVERY ZONE"))
                .getCost();

        float sizeCost = Arrays.stream(Size.values())
                .filter(v -> size == v)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UNEXPECTED SIZE"))
                .getCost();

        if (fragile) {
            fragileCost += FRAGILE_COST;
        }

        float loadCost = Arrays.stream(LoadMultiplier.values())
                .filter(v -> loadMultiplier == v)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UNEXPECTED LOAD INFO"))
                .getMultiplier();

        cost = (distanceCost + sizeCost + fragileCost) * loadCost;
        cost = Math.max(MINIMAL_COST, cost);

        return cost;
    }
}

package io.kantemirov;

enum LoadMultiplier {
    VERY_HIGH(1.6f),
    HIGH(1.4f),
    RAISED(1.2f),
    NORMAL(1);

    private float multiplier;

    LoadMultiplier(float m) {
        multiplier = m;
    }

    public float getMultiplier() {
        return multiplier;
    }
}

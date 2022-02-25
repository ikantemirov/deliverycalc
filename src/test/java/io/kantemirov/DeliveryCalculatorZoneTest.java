package io.kantemirov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.kantemirov.DeliveryCalculator.ZERO_COST;
import static io.kantemirov.DeliveryZone.FIRST;
import static io.kantemirov.DeliveryZone.FOURTH;
import static io.kantemirov.DeliveryZone.SECOND;
import static io.kantemirov.DeliveryZone.THIRD;
import static io.kantemirov.LoadMultiplier.HIGH;
import static io.kantemirov.Size.BIG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCalculatorZoneTest {

    private static final Size SIZE = BIG;
    private static final boolean FRAGILE = false;
    private static final LoadMultiplier LOAD = HIGH;

    private final DeliveryCalculator calculator = new DeliveryCalculator();

    @ParameterizedTest(name = "{0}")
    @MethodSource("argumentsStream")
    public void shouldSeeCalcResult(String name, int deliveryZoneKm, Size size, boolean fragile,
                                    LoadMultiplier loadMultiplier, float expected) {

        float actual = calculator.calculate(deliveryZoneKm, size, fragile, loadMultiplier);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                arguments("Delivery zone bad case 1", -1, SIZE, FRAGILE, LOAD, ZERO_COST),
                arguments("Delivery zone bad case 2", 0, SIZE, FRAGILE, LOAD, ZERO_COST),
                arguments("Delivery zone bad case 3", FOURTH.getMaxDistance() + 1, SIZE, FRAGILE, LOAD, ZERO_COST),
                arguments("Delivery zone positive case 1", 1, SIZE, FRAGILE, LOAD, 400),
                arguments("Delivery zone positive case 2", FIRST.getMaxDistance() - 1, SIZE, FRAGILE, LOAD, 400),
                arguments("Delivery zone positive case 3", FIRST.getMaxDistance(), SIZE, FRAGILE, LOAD, 400),
                arguments("Delivery zone positive case 4", FIRST.getMaxDistance() + 1, SIZE, FRAGILE, LOAD, 420),
                arguments("Delivery zone positive case 5", SECOND.getMaxDistance() - 1, SIZE, FRAGILE, LOAD, 420),
                arguments("Delivery zone positive case 6", SECOND.getMaxDistance(), SIZE, FRAGILE, LOAD, 420),
                arguments("Delivery zone positive case 7", SECOND.getMaxDistance() + 1, SIZE, FRAGILE, LOAD, 560),
                arguments("Delivery zone positive case 8", THIRD.getMaxDistance() - 1, SIZE, FRAGILE, LOAD, 560),
                arguments("Delivery zone positive case 9", THIRD.getMaxDistance(), SIZE, FRAGILE, LOAD, 560),
                arguments("Delivery zone positive case 10", THIRD.getMaxDistance() + 1, SIZE, FRAGILE, LOAD, 700),
                arguments("Delivery zone positive case 11", FOURTH.getMaxDistance() - 1, SIZE, FRAGILE, LOAD, 700),
                arguments("Delivery zone positive case 12", FOURTH.getMaxDistance(), SIZE, FRAGILE, LOAD, 700)
        );
    }
}

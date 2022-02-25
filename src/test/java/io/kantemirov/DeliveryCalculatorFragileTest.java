package io.kantemirov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.kantemirov.DeliveryCalculator.FRAGILE_MAX_ZONE_KM;
import static io.kantemirov.DeliveryCalculator.ZERO_COST;
import static io.kantemirov.LoadMultiplier.HIGH;
import static io.kantemirov.Size.BIG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCalculatorFragileTest {

    private static final Size SIZE = BIG;
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
                arguments("Fragile true, delivery zone over 30", FRAGILE_MAX_ZONE_KM + 1, SIZE, true, LOAD, ZERO_COST),
                arguments("Fragile true, delivery zone 30", FRAGILE_MAX_ZONE_KM, SIZE, true, LOAD, 980),
                arguments("Fragile true, delivery zone 29", FRAGILE_MAX_ZONE_KM - 1, SIZE, true, LOAD, 980),
                arguments("Fragile false case", 25, SIZE, false, LOAD, 560)
        );
    }
}

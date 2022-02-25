package io.kantemirov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.kantemirov.LoadMultiplier.HIGH;
import static io.kantemirov.Size.BIG;
import static io.kantemirov.Size.SMALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCalculatorSizeTest {

    private static final int DISTANCE = 25;
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
                arguments("Size case 1", DISTANCE, SMALL, FRAGILE, LOAD, 420),
                arguments("Size case 2", DISTANCE, BIG, FRAGILE, LOAD, 560)
        );
    }
}

package io.kantemirov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.kantemirov.LoadMultiplier.HIGH;
import static io.kantemirov.LoadMultiplier.NORMAL;
import static io.kantemirov.LoadMultiplier.VERY_HIGH;
import static io.kantemirov.Size.BIG;
import static io.kantemirov.Size.SMALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCalculatorMultiplierTest {

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
                arguments("Multiplier case 1", 25, BIG, true, NORMAL, 700),
                arguments("Multiplier case 2", 25, SMALL, false, HIGH, 420),
                arguments("Multiplier case 3", 25, BIG, false, HIGH, 560),
                arguments("Multiplier case 4", 25, BIG, false, VERY_HIGH, 640)
        );
    }
}

package io.kantemirov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.kantemirov.LoadMultiplier.HIGH;
import static io.kantemirov.LoadMultiplier.NORMAL;
import static io.kantemirov.LoadMultiplier.RAISED;
import static io.kantemirov.LoadMultiplier.VERY_HIGH;
import static io.kantemirov.Size.BIG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DeliveryCalculatorMultiplierTest {

    private static final int DISTANCE = 5;
    private static final Size SIZE = BIG;
    private static final boolean FRAGILE = true;

    private final DeliveryCalculator calculator = new DeliveryCalculator();

    @ParameterizedTest(name = "{0}")
    @MethodSource("argumentsStream")
    public void shouldSeeCalcResult(String name,
                                    int deliveryZoneKm,
                                    Size size,
                                    boolean fragile,
                                    LoadMultiplier loadMultiplier,
                                    float expected) {

        float actual = calculator.calculate(deliveryZoneKm, size, fragile, loadMultiplier);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                arguments("Multiplier case 1", DISTANCE, SIZE, FRAGILE, NORMAL, 600),
                arguments("Multiplier case 2", DISTANCE, SIZE, FRAGILE, RAISED, 720),
                arguments("Multiplier case 3", DISTANCE, SIZE, FRAGILE, HIGH, 840),
                arguments("Multiplier case 4", DISTANCE, SIZE, FRAGILE, VERY_HIGH, 960)
        );
    }
}

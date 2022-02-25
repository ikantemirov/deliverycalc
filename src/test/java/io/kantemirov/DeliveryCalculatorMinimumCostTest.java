package io.kantemirov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.kantemirov.DeliveryCalculator.MINIMAL_COST;
import static io.kantemirov.LoadMultiplier.VERY_HIGH;
import static io.kantemirov.Size.SMALL;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryCalculatorMinimumCostTest {

    private final DeliveryCalculator calculator = new DeliveryCalculator();

    @Test
    @DisplayName("Delivery minimum cost case")
    public void shouldSeeCalcResult() {
        float actual = calculator.calculate(1, SMALL, false, VERY_HIGH);
        assertThat(actual).isEqualTo(MINIMAL_COST);
    }
}

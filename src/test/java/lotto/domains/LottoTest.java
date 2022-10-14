package lotto.domains;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    @ParameterizedTest
    @MethodSource("parametersProvider")
    void Given_LottoAndWinner_When_GetPrize_Then_EqualsTo_Expected(Lotto winner, Prize expected) {
        Lotto lotto = new Lotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.getPrize(winner)).isEqualTo(expected);
    }

    @Test
    void Given_Duplicated_Numbers_When_Create_Lotto_Then_Fail() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(1, 1, 2, 3, 4, 5));
    }

    static Stream<Arguments> parametersProvider() {
        return Stream.of(
                arguments(new Lotto(10, 11, 12, 13, 14, 15), Prize.NONE),
                arguments(new Lotto(1, 10, 11, 12, 13, 14), Prize.NONE),
                arguments(new Lotto(1, 2, 10, 11, 12, 13), Prize.NONE),
                arguments(new Lotto(1, 2, 3, 10, 11, 12), Prize.FOURTH),
                arguments(new Lotto(1, 2, 3, 4, 10, 11), Prize.THIRD),
                arguments(new Lotto(1, 2, 3, 4, 5, 10), Prize.SECOND),
                arguments(new Lotto(1, 2, 3, 4, 5, 6), Prize.FIRST)
        );
    }
}
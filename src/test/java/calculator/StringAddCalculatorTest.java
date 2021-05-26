package calculator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringAddCalculatorTest {
	@DisplayName("빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.")
	@Test
	public void splitAndSum_null_또는_빈문자() {
		int result = new StringAddCalculator(null).splitAndSum();
		assertThat(result).isEqualTo(0);

		result = new StringAddCalculator("").splitAndSum();
		assertThat(result).isEqualTo(0);
	}

	@DisplayName("숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.")
	@ParameterizedTest
	@CsvSource({ "1,1", "2,2", "3,3" })
	public void splitAndSum_숫자하나(String input, int expected) throws Exception {
		int result = new StringAddCalculator(input).splitAndSum();
		assertThat(result).isEqualTo(expected);
	}

	@DisplayName("숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.(예 : “1,2”)")
	@Test
	public void splitAndSum_쉼표구분자() throws Exception {
		int result = new StringAddCalculator("1,2").splitAndSum();
		assertThat(result).isEqualTo(3);
	}

	@DisplayName("구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다. (예 : “1,2:3” => 6)")
	@Test
	public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
		int result = new StringAddCalculator("1,2:3").splitAndSum();
		assertThat(result).isEqualTo(6);
	}

	@DisplayName("“//”와 “\\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. (예 : “//;\\n1;2;3” => 6)")
	@Test
	public void splitAndSum_custom_구분자() throws Exception {
		int result = new StringAddCalculator("//;\n1;2;3").splitAndSum();
		assertThat(result).isEqualTo(6);
	}

	@DisplayName("음수를 전달할 경우 RuntimeException 예외가 발생해야 한다. (예 : “-1,2,3”)")
	@Test
	public void splitAndSum_negative() throws Exception {
		assertThatThrownBy(() -> new StringAddCalculator("-1,2,3").splitAndSum())
			.isInstanceOf(RuntimeException.class);
	}
}
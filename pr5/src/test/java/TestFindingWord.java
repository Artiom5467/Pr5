import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.example.TestRecognizer;

import java.util.stream.Stream;

public class TestFindingWord {

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("abcTESTabc", TestRecognizer.State.F),
                Arguments.of("abcTES", TestRecognizer.State.STATE3),
                Arguments.of("TEST", TestRecognizer.State.F),
                Arguments.of("TE", TestRecognizer.State.STATE2),
                Arguments.of("T", TestRecognizer.State.STATE1),
                Arguments.of("abc", TestRecognizer.State.S),
                Arguments.of("abcdTESTefg", TestRecognizer.State.F),
                Arguments.of("TESabc", TestRecognizer.State.S),
                Arguments.of("TET", TestRecognizer.State.STATE1),
                Arguments.of("TETTEST", TestRecognizer.State.F),
                Arguments.of("TTEST", TestRecognizer.State.F),
                Arguments.of("TEST", TestRecognizer.State.F),
                Arguments.of("TESE", TestRecognizer.State.S)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testFindingWordParameterized(String input, TestRecognizer.State expectedState) {
        Assertions.assertEquals(expectedState, TestRecognizer.processString(input));
    }
}

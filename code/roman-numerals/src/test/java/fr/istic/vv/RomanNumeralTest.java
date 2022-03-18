package fr.istic.vv;
import net.jqwik.api.*;

import java.util.List;

public class RomanNumeralTest {

    @Property
    boolean romanNumeralParse_ValueRange(@ForAll("romanString") String roman) {
        int value = RomanNumeraUtils.parseRomanNumeral(roman);
        return value >= 0 && value < 4000;
     }

    @Property
    boolean integerConvert_ValidRange(@ForAll("validInt") int number) {
        String res = RomanNumeraUtils.toRomanNumeral(number);
        return RomanNumeraUtils.isValidRomanNumeral(res);
    }

    @Property
    boolean romanNumeralCheck_ValidChars(@ForAll("generateRomanString") String roman) {
        return roman.matches("(M|D|C|L|X|V|I)*");
    }

    @Property
    boolean romanNumeralCheck_RepetitionsValid(@ForAll("generateRomanString") String roman) {
        List<String> bads = List.of(
                "MMMM",
                "CCCC",
                "XXXX",
                "IIII",
                "DD",
                "LL",
                "VV"
        );

        for (String bad : bads) {
            if (roman.contains(bad)) return false;
        }
        return true;
    }

    @Property
    boolean romanNumeralCheck_ValidSubtractors(@ForAll("generateRomanString") String roman) {
        return false;
    };

    @Property
    boolean romanNumeralCheck_WithRegex(@ForAll("generateRomanString") String roman) {
        return roman.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    };








    @Provide
    Arbitrary<String> generateRomanString() {
        return Arbitraries.strings()
                .filter(RomanNumeraUtils::isValidRomanNumeral);
    }

    @Provide
    Arbitrary<Integer> validInt(){
        return Arbitraries.integers().between(0,3999);
    }
}

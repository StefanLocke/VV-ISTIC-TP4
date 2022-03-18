package fr.istic.vv;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RomanNumeraUtils {

        private static final TreeMap<Integer,String> otherValues = new TreeMap<>();
        static {
                otherValues.put(1000,"M");
                otherValues.put(900,"CM");
                otherValues.put(500,"D");
                otherValues.put(400,"CD");
                otherValues.put(100,"C");
                otherValues.put(90,"XC");
                otherValues.put(50,"L");
                otherValues.put(40,"XL");
                otherValues.put(10,"X");
                otherValues.put(9,"IX");
                otherValues.put(5,"V");
                otherValues.put(4,"IV");
                otherValues.put(1,"I");
                otherValues.put(0,"");
        }
        private static final boolean DEBUG = false;

        public static void debug(String message) {
                if (DEBUG) System.out.println(message);
        }

        private static final List<Character> repeatable = List.of('M', 'C', 'X', 'I');
        private static final List<Character> nonRepeatable = List.of('D','L','V');
        private static final Map<Character,Integer> values = Map.of(
                'M',1000,
                'D',500,
                'C',100,
                'L',50,
                'X',10,
                'V',5,
                'I',1
        );



        public static boolean isValidRomanNumeral(String value) {
                if (!hasCorrectCharacters(value)) return false;
                for (int i = 0; i < value.length();i++) {
                        int repeats = checkRepitions(value,i);
                        if (repeatable.contains(value.charAt(i))) {
                                if (repeats > 3) return false;
                        } else {
                                if (repeats > 1) return false;
                        }

                }

                for (int i = 0; i < value.length();i++) {
                        int current = values.get(value.charAt(i));
                        int next = values.get(value.charAt(i+1));
                        if (current < next) {
                                if (!((next/10 == current) || (next/5 == current))) return false;
                                i++;
                        }else {

                        }
                }


                return true;
        }
    
        public static int parseRomanNumeral(String numeral) {
                int value = 0;
                for (int i = 0; i < numeral.length();i++) {
                        int current = values.get(numeral.charAt(i));
                        int next = values.get(numeral.charAt(i+1));
                        if (current < next) {
                                value += (next - current);
                                i++;
                        }else {
                                value += current;
                        }
                }

                return value;
        }



    
        public static String toRomanNumeral(int number) {
                int l =  otherValues.floorKey(number);
                if ( number == l ) {
                        return otherValues.get(number);
                }
                return otherValues.get(l) + toRomanNumeral(number-l);
        }


        private static boolean hasCorrectCharacters(String value) {
                return value.matches("(M|D|C|L|X|V|I)*");
        };


        //checks for repition to the left
        private static int checkRepitions(String value,int pos) {
                char c = value.charAt(pos);
                int current = pos;
                int total = 0;
                while ((current < value.length()) && value.charAt(current) == c) {
                        total++;
                        current++;
                }
                current = pos-1;
                while ((current >= 0) && value.charAt(current) == c) {
                        total++;
                        current--;
                }
                debug(c + " has " + total + " repititions");
                return total;
        }
}

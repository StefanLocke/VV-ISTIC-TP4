package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }



    @Property
    boolean testBubbleSort(){
    }
}
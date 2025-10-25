package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    private static final double DELTA = 0.00001;

    // ------------------ average ------------------
    @Test
    public void testAverageWithOneElementArray() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.average();
        assertEquals(-1.0, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.average();
        assertEquals(1.0, actualResult, DELTA);
    }

    // ------------------ deviation ------------------
    @Test
    public void testDeviationWithOneElementArray() {
        double[] temperatureSeries = {5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(0.0, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(Math.sqrt(2.0 / 3.0), actualResult, DELTA);
    }

    @Test
    public void testDeviationAllEqual() {
        double[] temperatureSeries = {2.0, 2.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(0.0, actualResult, DELTA);
    }

    // ------------------ min ------------------
    @Test
    public void testMinWithOneElementArray() {
        double[] temperatureSeries = {10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();
        assertEquals(10.0, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.min();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();
        assertEquals(-5.0, actualResult, DELTA);
    }

    // ------------------ max ------------------
    @Test
    public void testMaxWithOneElementArray() {
        double[] temperatureSeries = {10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();
        assertEquals(10.0, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.max();
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();
        assertEquals(5.0, actualResult, DELTA);
    }

    // ------------------ findTempClosestToZero ------------------
    @Test
    public void testFindTempClosestToZeroWithOneElement() {
        double[] temperatureSeries = {0.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(0.5, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {1.0, -2.0, 3.0, -0.5, 0.6};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(-0.5, actualResult, DELTA);
    }

    @Test
    public void testFindTempClosestToZeroWithTie() {
        double[] temperatureSeries = {-0.2, 0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(0.2, actualResult, DELTA);
    }

    // ------------------ findTempClosestToValue ------------------
    @Test
    public void testFindTempClosestToValueWithOneElement() {
        double[] temperatureSeries = {10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToValue(5.0);
        assertEquals(10.0, actualResult, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToValue(0.0);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {1.0, 3.0, 5.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToValue(4.0);
        assertEquals(5.0, actualResult, DELTA);
    }

    @Test
    public void testFindTempClosestToValueWithTie() {
        double[] temperatureSeries = {1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToValue(3.0);
        assertEquals(5.0, actualResult, DELTA);
    }

    // ------------------ findTempsLessThan ------------------
    @Test
    public void testFindTempsLessThanWithEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] actualResult = seriesAnalysis.findTempsLessThan(0.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0, -2.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsLessThan(0.0);
        assertArrayEquals(new double[] {-1.0, -2.0}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsLessThanNoMatches() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsLessThan(0.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    // ------------------ findTempsGreaterThan ------------------
    @Test
    public void testFindTempsGreaterThanWithEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(0.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0, -2.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(0.0);
        assertArrayEquals(new double[] {0.0, 1.0, 2.0}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsGreaterThanNoMatches() {
        double[] temperatureSeries = {-1.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(0.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    // ------------------ findTempsInRange ------------------
    @Test
    public void testFindTempsInRangeWithEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] actualResult = seriesAnalysis.findTempsInRange(0.0, 10.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0, -2.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsInRange(0.0, 2.0);
        assertArrayEquals(new double[] {0.0, 1.0, 2.0}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsInRangeBoundary() {
        double[] temperatureSeries = {0.0, 5.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsInRange(0.0, 10.0);
        assertArrayEquals(new double[] {0.0, 5.0, 10.0}, actualResult, DELTA);
    }

    @Test
    public void testFindTempsInRangeInvertedBounds() {
        double[] temperatureSeries = {1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsInRange(10.0, 0.0);
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    // ------------------ reset ------------------
    @Test
    public void testReset() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.reset();
        try {
            seriesAnalysis.average();
            fail("Expected IllegalArgumentException after reset");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    // ------------------ sortTemps ------------------
    @Test
    public void testSortTempsWithEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] actualResult = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[] {}, actualResult, DELTA);
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, 1.0, 4.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[] {1.0, 2.0, 3.0, 4.0}, actualResult, DELTA);
    }

    @Test
    public void testSortTempsWithDuplicates() {
        double[] temperatureSeries = {2.0, 1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[] {1.0, 2.0, 2.0}, actualResult, DELTA);
    }

    // ------------------ summaryStatistics ------------------
    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();
        assertEquals(2.0, stats.getAvgTemp(), DELTA);
        assertEquals(1.0, stats.getDevTemp(), DELTA);
        assertEquals(1.0, stats.getMinTemp(), DELTA);
        assertEquals(3.0, stats.getMaxTemp(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.summaryStatistics();
    }

    // ------------------ addTemps ------------------
    @Test
    public void testAddTemps() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int actualResult = seriesAnalysis.addTemps(1.0, 2.0, 3.0);
        assertEquals(3, actualResult);
        assertEquals(2.0, seriesAnalysis.average(), DELTA);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsInvalid() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(1.0, TemperatureSeriesAnalysis.LowestTemp - 1.0, 3.0);
    }

    @Test
    public void testAddTempsInvalidNoChange() {
        double[] initial = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(initial);
        try {
            seriesAnalysis.addTemps(3.0, TemperatureSeriesAnalysis.LowestTemp - 1.0);
            fail("Expected InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        assertEquals(1.5, seriesAnalysis.average(), DELTA);
    }

    @Test
    public void testAddTempsDoublingCapacity() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(1.0);
        seriesAnalysis.addTemps(2.0);
        seriesAnalysis.addTemps(3.0);
        assertEquals(2.0, seriesAnalysis.average(), DELTA);
    }

    @Test
    public void testAddTempsEmptyVarargs() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int actualResult = seriesAnalysis.addTemps();
        assertEquals(0, actualResult);
    }

    // ------------------ constructors ------------------
    @Test
    public void testConstructorDefault() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        try {
            seriesAnalysis.average();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithInvalidTemp() {
        double[] temperatureSeries = {1.0, TemperatureSeriesAnalysis.LowestTemp - 1.0};
        new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testConstructorWithBoundaryTemp() {
        double[] temperatureSeries = {TemperatureSeriesAnalysis.LowestTemp};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(TemperatureSeriesAnalysis.LowestTemp, seriesAnalysis.min(), DELTA);
    }
}
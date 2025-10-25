package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int DefaultCapacity = 10;
    public static final double LowestTemp = -273.0;

    private double[] temperatureSeries;
    int realLen;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[DefaultCapacity];
        realLen = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < LowestTemp) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        realLen = temperatureSeries.length;
    }

    public double average() {
        if (realLen == 0) {
            throw new IllegalArgumentException();
        }
        double res = 0.0;
        for (int i = 0; i < realLen; ++i) {
            res += temperatureSeries[i];
        }
        return res / realLen;
    }

    public double deviation() {
        double avg = average();
        double res = 0.0;
        for (int i = 0; i < realLen; ++i) {
            res += Math.pow(temperatureSeries[i] - avg, 2);
        }
        return Math.sqrt(res / realLen);
    }

    public double min() {
        if (realLen == 0) {
            throw new IllegalArgumentException();
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < realLen; ++i) {
            res = Math.min(res, temperatureSeries[i]);
        }
        return res;
    }

    public double max() {
        if (realLen == 0) {
            throw new IllegalArgumentException();
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < realLen; ++i) {
            res = Math.max(res, temperatureSeries[i]);
        }
        return res;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (realLen == 0) {
            throw new IllegalArgumentException();
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < realLen; ++i) {
            if (Math.abs(temperatureSeries[i] - tempValue) < Math.abs(res - tempValue)
                || Math.abs(temperatureSeries[i] - tempValue) == Math.abs(res - tempValue)
                && temperatureSeries[i] > 0) {
                res = temperatureSeries[i];
            }
        }
        return res;
    }

    public double[] findTempsLessThan(double tempValue) {
        double[] res = new double[realLen];
        int resLen = 0;
        for (int i = 0; i < realLen; ++i) {
            if (temperatureSeries[i] < tempValue) {
                res[resLen++] = temperatureSeries[i];
            }
        }
        return Arrays.copyOf(res, resLen);
    }

    public double[] findTempsGreaterThan(double tempValue) {
        double[] res = new double[realLen];
        int resLen = 0;
        for (int i = 0; i < realLen; ++i) {
            if (temperatureSeries[i] >= tempValue) {
                res[resLen++] = temperatureSeries[i];
            }
        }
        return Arrays.copyOf(res, resLen);
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        double[] res = new double[realLen];
        int resLen = 0;
        for (int i = 0; i < realLen; ++i) {
            if (lowerBound <= temperatureSeries[i] && temperatureSeries[i] <= upperBound) {
                res[resLen++] = temperatureSeries[i];
            }
        }
        return Arrays.copyOf(res, resLen);
    }

    public void reset() {
        temperatureSeries = new double[10];
        realLen = 0;
    }

    public double[] sortTemps() {
        double[] res = Arrays.copyOf(temperatureSeries, realLen);
        Arrays.sort(res);
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(
            average(),
            deviation(),
            min(),
            max()
        );
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < LowestTemp) {
                throw new InputMismatchException();
            }
        }
        if (realLen + temps.length > temperatureSeries.length) {
            double[] newTemperatureSeries = new double[(realLen + temps.length) * 2];
            for (int i = 0; i < realLen; ++i) {
                newTemperatureSeries[i] = temperatureSeries[i];
            }
            temperatureSeries = newTemperatureSeries;
        }
        for (int i = 0; i < temps.length; ++i) {
            temperatureSeries[realLen + i] = temps[i];
        }
        return realLen += temps.length;
    }
}

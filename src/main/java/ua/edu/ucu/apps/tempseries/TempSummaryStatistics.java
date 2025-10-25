package ua.edu.ucu.apps.tempseries;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TempSummaryStatistics {
    final double avgTemp;
    final double devTemp;
    final double minTemp;
    final double maxTemp;
}

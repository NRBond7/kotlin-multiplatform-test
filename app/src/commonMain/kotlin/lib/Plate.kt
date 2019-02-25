package lib

enum class Plate(val weight: Double, val isMetric: Boolean) {

    IMPERIAL_2_5(2.5, false),
    IMPERIAL_5(5.0, false),
    IMPERIAL_10(10.0, false),
    IMPERIAL_25(25.0, false),
    IMPERIAL_45(45.0, false),

    METRIC_1(1.0, true),
    METRIC_1_25(1.25, true),
    METRIC_2_5(2.5, true),
    METRIC_5(5.0, true),
    METRIC_10(10.0, true),
    METRIC_15(15.0, true),
    METRIC_20(20.0, true);

    companion object {
        private val imperialPlates = Plate.values().filter { !it.isMetric }.sortedByDescending { it.weight }

        private val metricPlates = Plate.values().filter { it.isMetric }.sortedByDescending { it.weight }

        fun getAvailablePlates(isMetric: Boolean) = if (isMetric) metricPlates else imperialPlates
    }
}


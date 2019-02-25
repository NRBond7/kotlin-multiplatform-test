package lib

enum class Plate(val weight: Double, val isMetric: Boolean, val color: String, val height: Int) {

    IMPERIAL_2_5(2.5, false, "#7af441", 50),
    IMPERIAL_5(5.0, false, "#417cf4", 75),
    IMPERIAL_10(10.0, false, "#a7acb7", 100),
    IMPERIAL_25(25.0, false, "#10a845", 150),
    IMPERIAL_45(45.0, false, "#1026a8", 200),

    METRIC_1(1.0, true, "#7af441", 40),
    METRIC_1_25(1.25, true, "#417cf4", 50),
    METRIC_2_5(2.5, true, "#a7acb7", 75),
    METRIC_5(5.0, true, "#10a845", 100),
    METRIC_10(10.0, true, "#db8708", 150),
    METRIC_15(15.0, true, "#c60522", 175),
    METRIC_20(20.0, true, "#1026a8", 200);

    companion object {
        private val imperialPlates = Plate.values().filter { !it.isMetric }.sortedByDescending { it.weight }

        private val metricPlates = Plate.values().filter { it.isMetric }.sortedByDescending { it.weight }

        fun getAvailablePlates(isMetric: Boolean) = if (isMetric) metricPlates else imperialPlates
    }
}


package lib

import kotlin.test.*

class PlateTest {

    @Test fun platesSortedDescended() {
        assertEquals(Plate.METRIC_20, Plate.getAvailablePlates(true).first())
        assertEquals(Plate.METRIC_1, Plate.getAvailablePlates(true).last())
        assertEquals(Plate.IMPERIAL_45, Plate.getAvailablePlates(false).first())
        assertEquals(Plate.IMPERIAL_2_5, Plate.getAvailablePlates(false).last())
    }

    @Test fun getAvailablePlates() {
        val imperialPlates = listOf(
            Plate.IMPERIAL_45,
            Plate.IMPERIAL_25,
            Plate.IMPERIAL_10,
            Plate.IMPERIAL_5,
            Plate.IMPERIAL_2_5
        )
        val metricPlates = listOf(
            Plate.METRIC_20,
            Plate.METRIC_15,
            Plate.METRIC_10,
            Plate.METRIC_5,
            Plate.METRIC_2_5,
            Plate.METRIC_1_25,
            Plate.METRIC_1
        )
        assertEquals(imperialPlates, Plate.getAvailablePlates(false))
        assertEquals(metricPlates, Plate.getAvailablePlates(true))
    }
}
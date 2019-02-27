package home

import lib.Plate
import kotlin.test.*

class HomePresenterTest {

    private val presenter = HomePresenter()

    @Test fun createAvailablePlates() {
        createAvailablePlatesHelper(true)
        createAvailablePlatesHelper(false)
    }

    private fun createAvailablePlatesHelper(isMetric: Boolean) {
        val plateList = Plate.getAvailablePlates(isMetric).reversed()
        plateList.forEach {
            val currentWeight = it.weight
            val availablePlateSet = presenter.createAvailablePlates(isMetric, it.weight).sortedBy { it.weight }
            val actualPlateSet = plateList.filter { it.weight >= currentWeight }.sortedBy { it.weight }
            assertEquals(actualPlateSet, availablePlateSet)
        }
    }

    @Test fun generateOneEndOfBarWeight() {
        val testWeights = listOf(
            0.0,
            135.0,
            225.0,
            235.5,
            300.0,
            350.0
        )
        val actualBarWeights = listOf(
            0.0,
            45.0,
            90.0,
            95.25,
            127.5,
            152.5
        )
        actualBarWeights.forEach {
            val inputWeight = testWeights[actualBarWeights.indexOf(it)]
            assertEquals(it, presenter.generateOneEndOfBarWeight(inputWeight, 45.0))
        }
    }

    @Test fun generateImperialPlateSet() {
        val testWeights = listOf(
            135.0,
            250.0,
            185.0,
            255.0,
            445.0,
            565.0
        )

        val actualPlateSets = listOf(
            listOf(
                Plate.IMPERIAL_45
            ),
            listOf(
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45
            ),
            listOf(
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_25
            ),
            listOf(
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_10,
                Plate.IMPERIAL_5
            ),
            listOf(
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_10,
                Plate.IMPERIAL_10
            ),
            listOf(
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_45,
                Plate.IMPERIAL_25,
                Plate.IMPERIAL_10
            )
        )

        testPlateSet(testWeights, actualPlateSets, 45.0)
    }

    @Test fun generateMetricPlateSet() {
        val testWeights = listOf(
            100.0,
            150.0,
            175.0,
            217.5
        )

        val actualPlateSets = listOf(
            listOf(
                Plate.METRIC_20,
                Plate.METRIC_20
            ),
            listOf(
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_5
            ),
            listOf(
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_10,
                Plate.METRIC_5,
                Plate.METRIC_2_5
            ),
            listOf(
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_20,
                Plate.METRIC_10,
                Plate.METRIC_5,
                Plate.METRIC_2_5,
                Plate.METRIC_1_25
            )
        )

        testPlateSet(testWeights, actualPlateSets, 20.0)
    }

    private fun testPlateSet(weightSet: List<Double>, actualPlateSets: List<List<Plate>>, barWeight: Double) {
        actualPlateSets.forEach {
            val index = actualPlateSets.indexOf(it)
            val weight = presenter.generateOneEndOfBarWeight(weightSet[index], barWeight)
            val plateList = presenter.generatePlateSet(weight, it, false)
            assertEquals(it, plateList)
            assertEquals(it.sumByDouble { weight }, plateList.sumByDouble { weight })
        }
    }

    @Test fun generateConroyPlateList() {
        val testWeightsImperial = listOf(
            135.0,
            250.0,
            185.0,
            255.0,
            445.0,
            565.0
        )
        val testWeightsMetric = listOf(
            100.0,
            150.0,
            175.0
        )
        generateConroyPlateListHelper(testWeightsImperial, 45.0, Plate.getAvailablePlates(false))
        generateConroyPlateListHelper(testWeightsMetric, 20.0, Plate.getAvailablePlates(true).minus(listOf(Plate.METRIC_1_25, Plate.METRIC_1)))
    }

    private fun generateConroyPlateListHelper(weightList: List<Double>, barWeight: Double, availablePlates: List<Plate>) {
        weightList.forEach {
            val endWeight = presenter.generateOneEndOfBarWeight(it, barWeight)
            for (x in 1..100) {
                val plateList = presenter.generatePlateSet(endWeight, availablePlates, true)
                assertEquals(endWeight, plateList.sumByDouble { it.weight })
            }
        }
    }

    @Test fun onWeightInput() {

    }
}
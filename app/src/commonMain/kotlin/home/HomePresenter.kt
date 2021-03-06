package home

import base.BasePresenter
import base.Contract
import lib.Plate
import settings.GlobalSettings


class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter  {

    companion object {
        private const val INPUT_ERROR_WEIGHT_ROUNDED = "Weight was rounded down to "
    }

    lateinit var settings: GlobalSettings

    override fun attachView(view: Contract.View) {
        super.attachView(view)
        settings = GlobalSettings(view.getSettingsFactory())
        updateUI()
    }

    override fun dropView() {
        settings.initialBarLoad.set(view.getInputWeight())
        super.dropView()
    }

    override fun screenName(): String = "Plate calculator"

    override fun onWeightInput(weight: String) {
        val inputWeight = if (weight.isNotEmpty()) weight.toDouble() else 0.0
        val availablePlates = createAvailablePlates(settings.metricEnabled.get(), settings.smallestPlateWeight.get())
        val endWeight = generateOneEndOfBarWeight(inputWeight, settings.barWeight.get())
        val plateSet = generatePlateSet(endWeight, availablePlates, settings.conroyModeEnabled.get())
        val plateSetWeight = plateSet.sumByDouble { it.weight }
        val wasWeightRounded = endWeight != plateSetWeight
        val roundedWeight = plateSetWeight * 2 + settings.barWeight.get()
        val errorText = if (wasWeightRounded) INPUT_ERROR_WEIGHT_ROUNDED + roundedWeight else ""

        val eventParams = HashMap<String, String>()
        eventParams["input_weight"] = inputWeight.toString()
        view.logEvent("calculate_weight", eventParams)

        view.displayWeight(plateSet, errorText)
    }

    override fun handleSettingsClicked() {
        view.logEvent("settings_clicked", HashMap())
        view.openSettings()
    }

    private fun updateUI() {
        view.populateWeightField(
                "Total barbell weight (${settings.getWeightUnitString()})",
                settings.initialBarLoad.get().toString()
        )
    }

    fun generateOneEndOfBarWeight(inputWeight: Double, barWeight: Double) : Double {
        val weight = (inputWeight - barWeight) / 2.0
        return if (weight > 0.0) weight else 0.0
    }

    fun generatePlateSet(weightInput: Double, availablePlates: List<Plate>, plateRandomizationOn: Boolean): List<Plate> {
        val plateList = arrayListOf<Plate>()
        var remainingWeight = weightInput

        if (plateRandomizationOn) {
            while (remainingWeight >= availablePlates.last().weight) {
                val randomPlate = availablePlates[(0 until availablePlates.size).random()]
                if (randomPlate.weight <= remainingWeight) {
                    plateList.add(randomPlate)
                    remainingWeight -= randomPlate.weight
                }
            }
        } else {
            availablePlates.forEach {
                val numPlates = (remainingWeight / it.weight).toInt()
                for (x in 1..numPlates) { plateList.add(it) }
                remainingWeight -= (it.weight * numPlates)
            }
        }

        return plateList
    }

    fun createAvailablePlates(isMetric: Boolean, smallestPlateWeight: Double): List<Plate> =
            Plate.getAvailablePlates(isMetric).filter { it.weight >= smallestPlateWeight }

}
package home

import base.BasePresenter
import base.Contract
import lib.Plate
import settings.GlobalSettings
import kotlin.math.floor
import kotlin.random.Random


class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter  {

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

    override fun onWeightInput(weight: String) {
        val inputWeight = if (weight.isNotEmpty()) weight.toDouble() else 0.0
        val availablePlates = createAvailablePlates(settings.metricEnabled.get(), settings.smallestPlateWeight.get())
        val endWeight = generateOneEndOfBarWeight(inputWeight, settings.barWeight.get())

        view.displayWeight(generatePlateSet(endWeight, availablePlates, settings.conroyModeEnabled.get()))
    }

    override fun handleSettingsClicked() = view.openSettings()

    private fun updateUI() {
        view.populateWeightField(
                "Weight (${settings.getWeightUnitString()})",
                settings.initialBarLoad.get().toString()
        )
    }

    private fun generateOneEndOfBarWeight(inputWeight: Double, barWeight: Double) : Double {
        val weight = (inputWeight - barWeight) / 2.0
        return if (weight > 0.0) weight else 0.0
    }

    private fun generatePlateSet(weightInput: Double, availablePlates: List<Plate>, plateRandomizationOn: Boolean): List<Plate> {
        val plateList = arrayListOf<Plate>()
        var remainingWeight = weightInput

        if (plateRandomizationOn) {
            while (remainingWeight >= availablePlates.last().weight) {
                val randomPlate = availablePlates[(0 until availablePlates.size).random()]
                if (randomPlate.weight <= remainingWeight) {
                    plateList.add(randomPlate)
                    remainingWeight -= randomPlate.weight
                    println("Weight: $remainingWeight")
                }
            }
        } else {
            availablePlates.forEach {
                val numPlates = floor(remainingWeight / it.weight).toInt()
                for (x in 1..numPlates) { plateList.add(it) }
                remainingWeight -= (it.weight * numPlates)
            }
        }

        return plateList
    }

    private fun createAvailablePlates(isMetric: Boolean, smallestPlateWeight: Double): List<Plate> =
            Plate.getAvailablePlates(isMetric).filter { it.weight >= smallestPlateWeight }

}
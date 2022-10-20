package dtu.amd.worktracker.preview.data

import dtu.amd.worktracker.model.Work
import java.util.*

class Workitems() {
    var workitems: MutableList<Work> = mutableListOf()

    fun addWork(workitem: Work) {
        workitems.add(workitem)
    }

    fun getWork(populate: Boolean = false): MutableList<Work> {
        if (populate) {
            this.populate(34)
        }
        return workitems
    }

    fun populate(amount: Int) {
        for (i in 1..amount) {
            val work = Work(
                i,
                "Title $i",
                "Company $i",
                Date(),
                Date(),
                Date(),
                true,
                Date(),
                Date(),
                true,
                (100..5000).random().toDouble(),
                (1..12).random(),
                (2020..2022).random()
            )
            workitems.add(work)
        }
    }

    fun getByID(id: Int): Work {
        return getWork(true).first { it.id == id }
    }

}
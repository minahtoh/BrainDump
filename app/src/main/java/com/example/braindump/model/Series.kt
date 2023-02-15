package com.example.braindump.model

import com.androidplot.xy.XYSeries

class Series(private val Values :List<Int>): XYSeries {
    override fun getTitle(): String {
        return "Feelings"
    }

    override fun size(): Int {
        return Values.size
    }

    override fun getX(p0: Int): Number {
        return if (Values.isNotEmpty()) Values.indexOf(p0) + 1
        else 0
    }

    override fun getY(p0: Int): Number {
        return if (Values.isNotEmpty()){
            Values[p0]
        } else 0
    }
}
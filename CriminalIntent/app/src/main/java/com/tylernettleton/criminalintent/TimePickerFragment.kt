package com.tylernettleton.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "TIME_PICKER_DATE_ARG"

class TimePickerFragment: DialogFragment() {


    interface Callbacks {
        fun onTimeSet(date: Date)
    }

    val timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        val date = arguments?.get(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        targetFragment?.let {fragment ->
            (fragment as Callbacks).onTimeSet(calendar.time)
        }

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date = arguments?.get(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date

        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minOfDay = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            requireContext(),
            timeListener,
            hourOfDay,
            minOfDay,
            false)
    }

    companion object {
        fun newInstance(date: Date) : TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }

}
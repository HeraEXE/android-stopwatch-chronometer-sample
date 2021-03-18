package hera.com.stopwatchchronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import hera.com.stopwatchchronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isWorking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.stopwatch.base = savedInstanceState.getLong(TIME_VALUE)
            isWorking = savedInstanceState.getBoolean(WAS_WORKING)
        }

        if (isWorking) {
            binding.stopwatch.start()
            binding.startClearButton.setText(R.string.stop_text)
        }

        binding.startClearButton.setOnClickListener {
            if (isWorking) stopChronometer()
            else startChronometer()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(TIME_VALUE, binding.stopwatch.base)
        outState.putBoolean(WAS_WORKING, isWorking)
    }

    private fun stopChronometer() {
        binding.stopwatch.stop()
        binding.startClearButton.setText(R.string.restart_text)
        isWorking = false
    }

    private fun startChronometer() {
        binding.stopwatch.base = SystemClock.elapsedRealtime()
        binding.stopwatch.start()
        binding.startClearButton.setText(R.string.stop_text)
        isWorking = true
    }


    companion object {
        const val TIME_VALUE = "time_value"
        const val WAS_WORKING = "was_working"
    }
}
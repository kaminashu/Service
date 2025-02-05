package www.uzmd.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class MyJobService:JobService() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("MY_TAG", "onStartCommand: Ishga tushdi")
        scope.launch {

            for (i in 1..30) {
                Log.d("MY_TAG", "onStartJob: $i")
                delay(1000)
            }
            jobFinished(params,true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("MY_TAG", "onStopJob: To`xtadi")
       return true
    }
}
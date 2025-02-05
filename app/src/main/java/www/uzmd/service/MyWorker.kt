package www.uzmd.service

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyWorker(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
   var scope= CoroutineScope(Dispatchers.Main)

    override fun doWork(): Result {
        while (true){
            Thread.sleep(6000)
                Log.d("MY_TAG", "doWork: ishlayapti")
        }
    }

    companion object{
        var PAGE= "page"
        var WORK_NAME= "MyWorker"
        fun makeRequest(page:Int):OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setConstraints(makeConstrains())
                .build()
        }

        private fun makeConstrains():Constraints{
            return Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()
        }

    }

    override fun onStopped() {
        super.onStopped()
        scope.cancel()
    }
}
package www.uzmd.service

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.impl.WorkManagerImpl
import www.uzmd.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.forgroundId.setOnClickListener {
            ContextCompat.startForegroundService(this, MyForground.newInstanse(this))
        }
        binding.stopForground.setOnClickListener {
            stopService(MyForground.newInstanse(this))
        }
        binding.jobServiceBtn.setOnClickListener {
            jobService()
        }
        binding.workerBtn.setOnClickListener {
            worker()
        }
    }

    private fun worker() {
val workManager=WorkManager.getInstance(this)   // Manager Yasab olinadi
        workManager.enqueueUniqueWork(MyWorker.WORK_NAME,
            ExistingWorkPolicy.APPEND,
            MyWorker.makeRequest(1)
            )
    }

    private fun jobService() {
        //1-component name
        val companent = ComponentName(this, MyJobService::class.java)

//2- shartlar
        val jobInfo = JobInfo.Builder(124, companent)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) //wifi ulanganda
            .build()

        val sheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        sheduler.schedule(jobInfo)

    }


}
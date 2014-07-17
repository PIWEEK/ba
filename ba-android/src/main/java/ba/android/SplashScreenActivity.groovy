package ba.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import ba.android.contest.ContestListActivity

public class SplashScreenActivity extends Activity {

    private long splashDelay = 6000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, ContestListActivity)
                startActivity(mainIntent)
                finish()
            }
        }

        Timer timer = new Timer()
        timer.schedule(task, splashDelay)
    }
}

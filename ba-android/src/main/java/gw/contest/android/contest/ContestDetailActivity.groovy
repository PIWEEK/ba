package gw.contest.android.contest

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.FragmentActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import groovy.transform.CompileStatic
import gw.contest.android.R
import gw.contest.android.contender.ContenderConfirmationFragment

@CompileStatic
public class ContestDetailActivity
        extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    int resolveInt(int integerResourceId) {
        return getResources().getInteger(integerResourceId)
    }

    void toast(int stringId) {
        Toast.makeText(this, stringId, 5).show()
    }

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_detail)
        loaderManager.initLoader(resolveInt(R.integer.action_contest_detail_show), null, this)
    }


    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contest_detail, menu)
        return true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId()
        switch (id) {
            case R.id.action_back:
                this.finish()
            break
            case R.id.action_subscribe:
                new ContenderConfirmationFragment()
                    .setCallbacks(this)
                    .show(getSupportFragmentManager(), 'confirmation')
            break
        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Long contestId = intent.getLongExtra(getString(R.string.contest_id),0L)

        switch(id) {
            case resolveInt(R.integer.action_contest_detail_show):
                return getCursorLoaderFromUri(
                        ContestProvider.CONTEST_CONTENT_URI
                            .buildUpon()
                            .appendPath(contestId.toString())
                            .build())
            break
            case resolveInt(R.integer.action_contest_detail_subscribe):
               return getCursorLoaderFromUri(
                        ContestProvider.CONTENDER_CONTENT_URI
                            .buildUpon()
                            .appendPath("add")
                            .appendQueryParameter('contenderId',deviceId)
                            .appendQueryParameter('contestId', contestId.toString())
                            .build())
            break
        }

    }

    String getDeviceId() {
        Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID)
    }

    CursorLoader getCursorLoaderFromUri(Uri uri) {
        return new CursorLoader(this, uri, null, null, null, null)
    }

    @Override
    void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!loader) return

        switch(loader.id) {
            case resolveInt(R.integer.action_contest_detail_show):
                if (data?.moveToFirst()) {
                    String name = data.getString(ContestProvider.CONTEST_LIST_UI_COLUMNS_ORDER_NAME)
                    String description = data.getString(ContestProvider.CONTEST_LIST_UI_COLUMNS_ORDER_DESCRIPTION)

                    TextView contestNameView = (TextView) findViewById(R.id.contestName)
                    TextView contestDescriptionView = (TextView) findViewById(R.id.contestDescription)

                    contestNameView.setText(name)
                    contestDescriptionView.setText(description)

                    data.close()
                }
            break
            case resolveInt(R.integer.action_contest_detail_subscribe):
                if (data?.moveToFirst()) {
                    toast(R.string.action_confirm_contender_acknowledgement)
                } else {
                    toast(R.string.action_confirm_contender_error)
                }
            break
        }

    }

    @Override
    void onLoaderReset(Loader<Cursor> loader) {

    }
}

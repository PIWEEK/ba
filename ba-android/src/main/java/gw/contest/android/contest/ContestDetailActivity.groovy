package gw.contest.android.contest

import android.app.Activity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import groovy.transform.CompileStatic
import gw.contest.android.R

@CompileStatic
public class ContestDetailActivity
        extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_detail)

        loaderManager.initLoader(0, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contest_detail, menu)
        return true
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId()
        switch (id) {
            case R.id.action_back:
                this.finish()
            break
            case R.id.action_subscribe:

            break
        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Long contestId = intent.getLongExtra(getString(R.string.contest_id),0L)

        return new CursorLoader(
                this,
                Uri.withAppendedPath(ContestProvider.CONTEST_CONTENT_URI,contestId.toString()), null, null, null, null)
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data.moveToFirst()) {
            String name = data.getString(ContestProvider.CONTEST_LIST_UI_COLUMNS_ORDER_NAME)
            String description = data.getString(ContestProvider.CONTEST_LIST_UI_COLUMNS_ORDER_DESCRIPTION)

            TextView contestNameView = (TextView) findViewById(R.id.contestName)
            TextView contestDescriptionView = (TextView) findViewById(R.id.contestDescription)

            contestNameView.setText(name)
            contestDescriptionView.setText(description)

            data.close()
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

package gw.contest.android.contest

import android.app.ListActivity
import android.app.LoaderManager
import android.content.ContentProviderOperation
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.SimpleCursorAdapter
import groovy.transform.CompileStatic
import gw.contest.android.R
import gw.contest.android.contest.ContestProvider

@CompileStatic
class ContestsListActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contest_list)

        loaderManager.initLoader(0, null, this);
    }

    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.contest_list_menu, menu)
        return true
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()

    }

    @Override
    Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this, ContestProvider.CONTEST_CONTENT_URI_LIST, null, null, null, null)
    }

    @Override
    void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        this.setListAdapter(
            new SimpleCursorAdapter(
                this,
                R.layout.contest_list_item,
                data,
                ContestProvider.CONTEST_LIST_UI_COLUMNS,
                ContestProvider.CONTEST_LIST_UI_FIELDS))
    }

    @Override
    void onLoaderReset(Loader<Cursor> loader) {

    }
}

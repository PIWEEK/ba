package ba.android.contest

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Intent
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import ba.android.R
import com.squareup.picasso.Picasso
import groovy.transform.CompileStatic

@CompileStatic
class ContestListActivity extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_list)

        listView.setOnItemClickListener { AdapterView<?> parent, View v, int position, long id ->
            Intent newIntent = new Intent(this, ContestDetailActivity)
            newIntent.putExtra(getString(R.string.contest_id), id)
            startActivity(newIntent)
        }

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
                this, ContestProvider.CONTEST_CONTENT_URI, null, null, null, null)
    }

    @Override
    void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(
                    this,
                    R.layout.contest_list_item,
                    data,
                    ContestProvider.CONTEST_LIST_UI_COLUMNS,
                    ContestProvider.CONTEST_LIST_UI_FIELDS)

        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.id == R.id.contestImage) {
                    ImageView imageView = (ImageView) view
                    Picasso
                        .with(ContestListActivity.this)
                        .load(cursor.getString(columnIndex))
                        .into(imageView)
                    return true
                }
               return false
            }
        };
        adapter.setViewBinder(viewBinder);


        this.setListAdapter(adapter)
        TextView noOfContests = (TextView) findViewById(R.id.actionbar_notifcation_textview)
        noOfContests.setText(data.count.toString())

    }

    @Override
    void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        loaderManager.restartLoader(0, null, this)

        return super.onOptionsItemSelected(item)
    }
}

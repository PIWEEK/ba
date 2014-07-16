package gw.contest.android

import android.app.ListActivity
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import groovy.transform.CompileStatic

@CompileStatic
class ContestsListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contest_list)

        String[] googleIO = [1,"Google IO Watch","Se sortea un Google Watch powered by Android"]
        String[] fosdem = [2, "Fosdem tickets", "Se sortean dos tickets gratis para FOSDEM 2015"]
        String[] columns = ['_id','name', 'description']

        int[] fields = [R.id.contestId,R.id.contestName, R.id.contestDescription] as int[]

        MatrixCursor cursor = new MatrixCursor(columns)
        cursor.addRow(googleIO)
        cursor.addRow(fosdem)
        startManagingCursor(cursor)

        SimpleCursorAdapter namesAdapter =
                new SimpleCursorAdapter<Map>(
                        this,
                        R.layout.contest_list_item,
                        cursor,
                        columns,
                        fields
                )

        this.setListAdapter(namesAdapter)
        namesAdapter.notifyDataSetChanged()

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

}

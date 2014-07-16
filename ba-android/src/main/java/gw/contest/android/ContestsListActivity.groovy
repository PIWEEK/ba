package gw.contest.android

import android.app.ListActivity
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import groovy.transform.CompileStatic

@CompileStatic
class ContestsListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contest_list)

        String[] columns = ['_id','name', 'description']
        int[] fields = [R.id.contestId,R.id.contestName, R.id.contestDescription] as int[]

        Uri uri = Uri.parse('content://'+ContestProvider.CONTEST_PROVIDER_NAME+'/contests')
        Cursor myCursor = getContentResolver()
                .query(uri,null,null,null,null)

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.contest_list_item,
                        myCursor,
                        columns,
                        fields)

        this.startManagingCursor(myCursor)
        this.setListAdapter(cursorAdapter)
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

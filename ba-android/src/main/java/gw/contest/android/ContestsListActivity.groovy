package gw.contest.android

import android.app.ListActivity
import android.os.Bundle
import android.app.Activity
import android.widget.ArrayAdapter
import android.widget.ListView
import groovy.transform.CompileStatic

@CompileStatic
class ContestsListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contest_list)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.id.textView)
        List<String> names = Arrays.asList("john","peter","jonas","rodrigo","ines")

        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this,R.id.textView,names)

        this.setListAdapter(namesAdapter)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()

    }

}

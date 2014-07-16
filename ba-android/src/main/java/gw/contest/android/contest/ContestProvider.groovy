package gw.contest.android.contest;

import android.content.ContentProvider
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor
import android.database.MatrixCursor;
import android.net.Uri
import android.widget.SimpleCursorAdapter
import groovy.json.JsonSlurper
import gw.contest.android.R

public class ContestProvider extends ContentProvider {

    static final UriMatcher CONTEST_URI_MATCHER
    static final String CONTEST_PROVIDER_NAME = 'gw.contest.android.provider'

    static final Uri CONTEST_CONTENT_URI_LIST = Uri.parse('content://'+ContestProvider.CONTEST_PROVIDER_NAME+'/contests')
    static final String[] CONTEST_LIST_UI_COLUMNS = ['_id','name', 'description']
    static final int[] CONTEST_LIST_UI_FIELDS = [R.id.contestId,R.id.contestName, R.id.contestDescription] as int[]

    static final int CONTESTS = 1
    static final int CONTESTS_ID = 2

    static {
        CONTEST_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests', CONTESTS)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests/#', CONTESTS_ID)
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

    }

    @Override
    public String getType(Uri uri) {

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

    }

    @Override
    public boolean onCreate() {

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        switch(CONTEST_URI_MATCHER.match(uri)) {
            case CONTESTS:
                return new ContestRepository().list()
            break
            case CONTESTS_ID:

            break
        }

        return null
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

    }
}

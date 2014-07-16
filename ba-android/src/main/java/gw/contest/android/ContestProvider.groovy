package gw.contest.android;

import android.content.ContentProvider
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor
import android.database.MatrixCursor;
import android.net.Uri
import groovy.json.JsonSlurper

public class ContestProvider extends ContentProvider {

    static final UriMatcher CONTEST_URI_MATCHER
    static final String CONTEST_PROVIDER_NAME = 'gw.contest.android.provider'

    static final int CONTESTS = 1
    static final int CONTESTS_ID = 2
    static final String[] CONTEST_LIST_COLUMN_NAMES = ['_id', 'name', 'description']

    static final URI_CONTEST_LIST = 'http://10.8.1.134:8080/api/contest'

    static {
        CONTEST_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests', CONTESTS)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests/#', CONTESTS_ID)
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        Cursor cursor

        switch(CONTEST_URI_MATCHER.match(uri)) {
            case CONTESTS:
            cursor = new MatrixCursor(CONTEST_LIST_COLUMN_NAMES)
            def contestList = [
               [1,"Google IO Watch", "Rifa del Google Watch"],
               [2,"Google IO Watch", "Rifa del Google Watch"]

            ]

            contestList.each { cursor.addRow(it) }

            break
        }


        cursor
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

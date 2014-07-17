package gw.contest.android.contest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import groovy.transform.CompileStatic
import gw.contest.android.R

@CompileStatic
class ContestProvider extends ContentProvider {

    static final UriMatcher CONTEST_URI_MATCHER
    static final String CONTEST_PROVIDER_NAME = 'gw.contest.android.provider'

    static final Uri CONTEST_CONTENT_URI = Uri.parse('content://'+ContestProvider.CONTEST_PROVIDER_NAME+'/contests')
    static final Uri CONTENDER_CONTENT_URI = Uri.parse('content://'+ContestProvider.CONTEST_PROVIDER_NAME+'/contenders')

    static final String[] CONTEST_LIST_UI_COLUMNS = ['_id','name', 'description']
    static final int[] CONTEST_LIST_UI_FIELDS = [R.id.contestId,R.id.contestName, R.id.contestDescription] as int[]
    static final int CONTEST_LIST_UI_COLUMNS_ORDER_ID = 0
    static final int CONTEST_LIST_UI_COLUMNS_ORDER_NAME = 1
    static final int CONTEST_LIST_UI_COLUMNS_ORDER_DESCRIPTION = 2

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

        Uri backUri = Uri.parse(context.getString(R.string.back_root_address))

        switch(CONTEST_URI_MATCHER.match(uri)) {
            case CONTESTS:
                return new ContestRepository(backUri).list()
            break
            case CONTESTS_ID:
                return new ContestRepository(backUri).get(uri.getLastPathSegment().toLong())
            break
        }

        return null
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

    }
}

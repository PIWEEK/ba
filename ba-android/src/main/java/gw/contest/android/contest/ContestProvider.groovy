package gw.contest.android.contest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import groovy.transform.CompileStatic
import gw.contest.android.R
import gw.contest.android.contender.HttpContenderRepository

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
    static final int CONTENDER_ADD = 3

    static {
        CONTEST_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests', CONTESTS)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contests/#', CONTESTS_ID)
        CONTEST_URI_MATCHER.addURI(CONTEST_PROVIDER_NAME, 'contenders/add/', CONTENDER_ADD)
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
        Uri contestUri = backUri.buildUpon().appendEncodedPath("api/contest").build()
        Uri contenderUri = backUri.buildUpon().appendEncodedPath("api/contender").build()

        switch(CONTEST_URI_MATCHER.match(uri)) {
            case CONTESTS:
                return new HttpContestRepository(contestUri).list()
            case CONTESTS_ID:
                return new HttpContestRepository(contestUri).get(uri.getLastPathSegment().toLong())
            case CONTENDER_ADD:
                return new HttpContenderRepository(contenderUri)
                        .addContender(
                            uri.getQueryParameter('contenderId'),
                            uri.getQueryParameter('contestId')
                        )
        }

        return null
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

    }
}

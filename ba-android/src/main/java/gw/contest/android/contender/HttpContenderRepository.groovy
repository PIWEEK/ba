package gw.contest.android.contender

import android.database.Cursor
import android.database.MatrixCursor
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import gw.contest.android.core.HttpClient


@CompileStatic
@InheritConstructors
class HttpContenderRepository extends HttpClient implements ContenderRepository {

    static final String[] CONTENDER_LIST_COLUMN_NAMES = ['contenderId','contestId']

    @Override
    Cursor addContender(String contenderID, String contestId) {
        Cursor cursor = new MatrixCursor(CONTENDER_LIST_COLUMN_NAMES)
        Map result = (Map) httpPost("add",[contenderId: contenderID, contestId: contestId])

        cursor.addRow([result.contenderId, result.contestId])

        return cursor
    }
}
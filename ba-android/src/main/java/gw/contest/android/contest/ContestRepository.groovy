package gw.contest.android.contest

import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import gw.contest.android.core.Repository

@CompileStatic
class ContestRepository implements Repository {

    static final String[] CONTEST_LIST_COLUMN_NAMES = ['_id', 'name', 'description']
    static final Uri BACK_ADDRESS = Uri.parse('http://192.168.1.33:8080/')
    static final Uri URI_CONTEST_ROOT = Uri.withAppendedPath(BACK_ADDRESS, "api/contest")

    @Override
    Cursor list() {
        Cursor cursor = new MatrixCursor(CONTEST_LIST_COLUMN_NAMES)
        URL address = URI_CONTEST_ROOT.toString().toURL()
        Object contestList = new JsonSlurper().parse(address)

        return (Cursor) contestList.inject(cursor, withResultRows)

    }

    Closure<MatrixCursor> withResultRows = { MatrixCursor currentCursor, Map row ->
        List processedRow = [row.id, row.name, row.description]
        currentCursor.addRow(processedRow)
        currentCursor
    }

    @Override
    Cursor get(Long id) {
        Cursor cursor = new MatrixCursor(CONTEST_LIST_COLUMN_NAMES)
        URL address = Uri.withAppendedPath(URI_CONTEST_ROOT, "$id").toString().toURL()
        Map result = (Map) new JsonSlurper().parse(address)

        cursor.addRow([result.id, result.name, result.description])

        return cursor
    }
}
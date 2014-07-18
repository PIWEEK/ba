package ba.android.contest

import android.database.Cursor
import android.database.MatrixCursor
import ba.android.core.CrudRepository
import ba.android.core.HttpClient
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class HttpContestRepository extends HttpClient implements CrudRepository {

    final String[] CONTEST_LIST_COLUMN_NAMES = ['_id', 'name', 'description', 'image']

    @Override
    Cursor list() {
        Cursor cursor = new MatrixCursor(CONTEST_LIST_COLUMN_NAMES)

        return (Cursor) this.httpGet().inject(cursor, withResultRows)
    }

    Closure<MatrixCursor> withResultRows = { MatrixCursor currentCursor, Map row ->
        List processedRow = [row.id, row.name, row.description, row.image]
        currentCursor.addRow(processedRow)

        return currentCursor
    }

    @Override
    Cursor get(Long id) {
        Cursor cursor = new MatrixCursor(CONTEST_LIST_COLUMN_NAMES)
        Map result = (Map) httpGet("$id")
        cursor.addRow([result.id, result.name, result.description, result.image])

        return cursor
    }
}
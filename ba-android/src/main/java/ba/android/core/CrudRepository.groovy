package ba.android.core

import android.database.Cursor;

/**
 */
public interface CrudRepository extends Repository {
    Cursor list()
    Cursor get(Long id)

}

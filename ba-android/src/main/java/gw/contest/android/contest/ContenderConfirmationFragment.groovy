package gw.contest.android.contest

import android.app.AlertDialog
import android.app.Dialog
import android.app.LoaderManager
import android.content.DialogInterface
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.Toast
import groovy.transform.CompileStatic
import gw.contest.android.R
import org.codehaus.groovy.runtime.NullObject

/**
 *
 */
@CompileStatic
class ContenderConfirmationFragment extends DialogFragment {

    LoaderManager.LoaderCallbacks<Cursor> callbacks

    Closure<?> negativeAction = { DialogInterface dialogInterface, int id -> }
    Closure<?> positiveAction = { DialogInterface dialogInterface, int id ->
        activity.loaderManager.restartLoader(
                getResources().getInteger(R.integer.action_contest_detail_subscribe),
                null,
                callbacks)
    }

    ContenderConfirmationFragment setCallbacks(LoaderManager.LoaderCallbacks<Cursor> callbacks) {
        this.callbacks = callbacks
        return this
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        new AlertDialog.Builder(getActivity())
            .setMessage(R.string.action_confirm_contender)
            .setPositiveButton(R.string.action_confirm_contender_yes, asListener(positiveAction))
            .setNegativeButton(R.string.action_confirm_contender_no, asListener(negativeAction))
            .create()
    }

    DialogInterface.OnClickListener asListener(Closure<?> action) {
        (DialogInterface.OnClickListener) action
    }

}

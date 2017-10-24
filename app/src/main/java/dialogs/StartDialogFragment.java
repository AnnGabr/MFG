package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.anngabr.perfection.GameActivity;
import com.anngabr.perfection.R;


/**
 * Created by Ann on 07.10.2017.
 */

public class StartDialogFragment extends DialogFragment implements View.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.start_dialog, null);

        v.findViewById(R.id.confirmBut).setOnClickListener(this);

        builder.setView(v);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        startGame();
    }

    private void startGame(){
        ((GameActivity) getActivity()).startGame();
    }
}

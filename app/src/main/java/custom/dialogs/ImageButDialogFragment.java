package custom.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anngabr.perfection.GameActivity;
import com.anngabr.perfection.R;


/**
 * Created by Ann on 07.10.2017.
 */

public class ImageButDialogFragment extends DialogFragment implements View.OnClickListener{

    private int imgResId;
    private int btnTextResId;
    private int titleResId;

    public ImageButDialogFragment(int imgResId, int btnTextResId, int titleResId){
        this.imgResId = imgResId;
        this.btnTextResId = btnTextResId;
        this.titleResId = titleResId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.start_dialog, null);

        ((Button) v.findViewById(R.id.confirmBut)).setText(btnTextResId);
        v.findViewById(R.id.confirmBut).setOnClickListener(this);
        ((ImageView)v.findViewById(R.id.sd_imageV)).setImageResource(imgResId);
        ((TextView)v.findViewById(R.id.sd_title)).setText(titleResId);

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
    }
}

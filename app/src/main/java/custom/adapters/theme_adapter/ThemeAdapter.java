package custom.adapters.theme_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anngabr.perfection.R;

import java.util.ArrayList;

/**
 * Created by Ann on 29.10.2017.
 */

public class ThemeAdapter extends ArrayAdapter<Theme> {

    public ThemeAdapter(@NonNull Context context, ArrayList<Theme> players) {
        super(context, R.layout.theme_list_item, players);
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Theme theme = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.theme_list_item, null);
        }
        ((TextView) convertView.findViewById(R.id.tl_themeNameTextV))
                .setText(theme.getName());
        ((ImageView) convertView.findViewById(R.id.tl_themeImageV))
                .setImageResource(theme.getImgResId());


        return convertView;
    }
}
package custom.adapters.player_adapter.player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anngabr.perfection.R;

import java.util.ArrayList;

/**
 * Created by Ann on 29.10.2017.
 */

public class PlayerAdapter extends ArrayAdapter<Player> {

    public PlayerAdapter(@NonNull Context context, ArrayList<Player> players) {
        super(context, R.layout.records_list_item, players);
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Player player = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.records_list_item, null);
        }
        ((TextView) convertView.findViewById(R.id.rl_nameTextV))
                .setText(player.getName());
        ((TextView) convertView.findViewById(R.id.rl_recordTextV))
                .setText(String.format("%d", player.getRecord()));

        return convertView;
    }
}

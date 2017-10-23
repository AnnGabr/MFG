package player;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ann on 20.10.2017.
 */

public class PlayerDataWriteReader {
    public static final String PLAYER_DATA_FILENAME = "playerData";

    public static void writePlayerData(Context context) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput(PLAYER_DATA_FILENAME, MODE_PRIVATE));
        oos.reset();

        oos.writeObject(Player.instance);

        oos.flush();
        oos.close();
    }

    public static void readPlayerData(Context context) throws IOException, ClassNotFoundException {
        ObjectInputStream oos = new ObjectInputStream(context.openFileInput(PLAYER_DATA_FILENAME));

        Player.instance = (Player)oos.readObject();
        Player.instance.setNewRecord(false);

        oos.close();
    }
}

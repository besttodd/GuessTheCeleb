package game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class CelebrityManager {
    private String[] imageNames;
    private AssetManager assetManager;

    public CelebrityManager(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        try {
            imageNames = assetManager.list(assetPath);
        } catch (IOException e) {
            System.out.println("Failed to get names-------------------------------------------------");
        }
    }

    Bitmap get(int i) {
        InputStream stream = null;
        try {
            stream = assetManager.open("celebs/" + imageNames[i]);
        } catch (IOException e) {
            System.out.println("Failed to open celebs/" + imageNames[i] + "-------------------------");
        }
        return BitmapFactory.decodeStream(stream);
    }

    public String getName(int i) {
        int dotIndex = imageNames[i].lastIndexOf(".");
        String name = imageNames[i].substring(0, dotIndex);
        return name.replace("-", " ");
    }

    public int count() { return imageNames.length; }
}

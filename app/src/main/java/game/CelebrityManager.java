package game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class CelebrityManager {
    private String assetPath;
    private String[] imageNames;
    private AssetManager assetManager;

    public CelebrityManager(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        this.assetPath = assetPath;
        try {
            imageNames = assetManager.list("celebs");
        } catch (IOException e) {
            System.out.println("Failed to get names-------------------------------------------------");
        }
    }

    public Bitmap get(int i) {
        InputStream stream = null;
        try {
            stream = assetManager.open("celebs/" + imageNames[i]);
        } catch (IOException e) {
            System.out.println("Failed to open celebs/" + imageNames[i] + "-------------------------");
        }
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        return bitmap;
    }

    public String getName(int i) {
        int dotIndex = imageNames[i].lastIndexOf(".");
        String name = imageNames[i].substring(0, dotIndex);
        return name.replace("-", " ");
    }

    public int count() { return imageNames.length; }
}

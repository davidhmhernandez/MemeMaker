package es.tessier.mememaker;

import android.content.res.AssetManager;

import java.io.File;

import es.tessier.mememaker.utils.FileUtilities;

/**
 * Created by Evan Anger on 7/28/14.
 */
public class MemeMakerApplication extends android.app.Application {



    @Override
    public void onCreate() {
        super.onCreate();

        FileUtilities.saveAssetImage(this, "dogmess.jpg");
        FileUtilities.saveAssetImage(this, "excitedcat.jpg");
        FileUtilities.saveAssetImage(this, "guiltypup.jpg");
    }

}

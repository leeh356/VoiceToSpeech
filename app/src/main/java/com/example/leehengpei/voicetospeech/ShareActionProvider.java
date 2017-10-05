package com.example.leehengpei.voicetospeech;

import android.content.Context;
import android.content.Intent;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Lee Heng Pei on 3/10/2017.
 */
/*
public class ShareActionProvider extends ActionProvider {

    private ShareActionProvider mShareActionProvider;

    public ShareActionProvider(Context context) {
        super(context);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.

        //MenuInflater inflater = getMenuInflater();
       // getMenuInflater().inflate(R.menu.share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        // Return true to display menu
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }


}

*/
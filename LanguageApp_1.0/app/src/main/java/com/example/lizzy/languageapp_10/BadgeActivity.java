package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lizzy.languageapp_10.R;

import java.util.ArrayList;
import java.util.List;

public class BadgeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
        ListView badgeListView = (ListView) findViewById(R.id.badgeListView);

        BadgeAdapter adapter = new BadgeAdapter(this, R.layout.badge_listitem, Settings.badges);
        Log.w("myapp", Settings.badges.size() + "");
        badgeListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.badge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class BadgeAdapter extends ArrayAdapter<Badge> {

        ArrayList<Badge> badges;

        BadgeAdapter(Context context, int resource, ArrayList<Badge> objects) {
            super(context, resource, objects);
            badges = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.badge_listitem, null);
            }

            Badge currentBadge = badges.get(position);
            if (!currentBadge.achieved)
                currentBadge = Settings.lockedBadge;

            if (currentBadge != null) {
                BadgeView badgeImage = (BadgeView) v.findViewById(R.id.badgeimage);
                TextView badgeText = (TextView) v.findViewById(R.id.badgedescription);

                badgeImage.setBadge(currentBadge);
                badgeImage.setManager();
                badgeImage.setMinimumHeight(badgeImage.getWidth());

                badgeText.setText(currentBadge.getDescription());
            }
            return v;

        }
    }
}

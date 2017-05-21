package com.huk.installedapps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huk on 21/5/17.
 */

class ApplicationAdapter extends BaseAdapter {

    private List<ApplicationInfo> appsList = null;
    private Context context;
    private PackageManager packageManager;

    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<ApplicationInfo> appsList) {
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((appsList == null) ? 0 : appsList.size());
    }

    @Override
    public Object getItem(int position) {
        return ((appsList == null) ? null : appsList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_item, null);
        }

        final ApplicationInfo applicationInfo = appsList.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LaunchIntent = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);
                context.startActivity(LaunchIntent);
            }
        });
        if (null != applicationInfo) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_paackage);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(applicationInfo.loadLabel(packageManager));
            packageName.setText(applicationInfo.packageName);
            iconview.setImageDrawable(applicationInfo.loadIcon(packageManager));
        }
        return view;
    }
}

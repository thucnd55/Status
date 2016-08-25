package com.james.status.data.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.james.status.R;
import com.james.status.adapters.AppColorPreviewAdapter;
import com.james.status.dialogs.AppColorDialog;
import com.james.status.utils.PreferenceUtils;

public class AppsColorPreferenceData extends PreferenceData {

    public AppsColorPreferenceData(Context context) {
        super(context, new Identifier(PreferenceUtils.PreferenceIdentifier.STATUS_COLOR_APPS, context.getString(R.string.preference_app_colors), Identifier.SectionIdentifier.COLORS));
    }

    public static ViewHolder getViewHolder(Context context) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_preference_apps, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final AppColorPreviewAdapter adapter = new AppColorPreviewAdapter(getContext()).setOnSizeChangedListener(new AppColorPreviewAdapter.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int size) {
                if (size > 0) {
                    holder.v.findViewById(R.id.more).setVisibility(View.VISIBLE);
                    holder.v.findViewById(R.id.addMore).setVisibility(View.GONE);
                } else {
                    holder.v.findViewById(R.id.more).setVisibility(View.GONE);
                    holder.v.findViewById(R.id.addMore).setVisibility(View.VISIBLE);
                }
            }
        });

        ((TextView) holder.v.findViewById(R.id.title)).setText(getIdentifier().getTitle());

        RecyclerView recyclerView = (RecyclerView) holder.v.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);

        holder.v.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppColorDialog dialog = new AppColorDialog(getContext());
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        adapter.reload();
                    }
                });
                dialog.show();
            }
        });

        holder.v.findViewById(R.id.addMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppColorDialog dialog = new AppColorDialog(getContext());
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        adapter.reload();
                    }
                });
                dialog.show();
            }
        });
    }
}
package linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.DatabaseManager;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogOptionUrl;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;

public class SearchAdapter extends BaseAdapter {

    private ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private DatabaseManager databaseManager;


    public SearchAdapter(Context context, ArrayList<ItemUrl> arrItemUrl) {
        this.context = context;
        this.arrItemUrl = arrItemUrl;
        inflater = LayoutInflater.from(context);
        databaseManager = new DatabaseManager(context);
    }

    @Override
    public int getCount() {
        return arrItemUrl.size();
    }

    @Override
    public ItemUrl getItem(int position) {
        return arrItemUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_url_search, null);
            holder = new ViewHolder();
            holder.imvImageUrl = (ImageView) view.findViewById(R.id.imv_image_url_search);
            holder.tvTitleUrl = (TextView) view.findViewById(R.id.tv_title_url_search);
            holder.tvUrl = (TextView) view.findViewById(R.id.tv_url_search);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ItemUrl item = arrItemUrl.get(position);

        Picasso.with(context).load(item.getUrlIcon())
                .fit()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_main_item)
                .into(holder.imvImageUrl);

//        Picasso.with(context).load(Conts.URL + item.getId() + Conts.FORMAT_IMAGE)
//                .fit()
//                .placeholder(R.drawable.ic_loading)
//                .error(R.drawable.ic_main_item)
//                .into(holder.imvImageUrl);
        holder.tvTitleUrl.setText(item.getTitleUrl());
        holder.tvTitleUrl.setSelected(true);
        holder.tvUrl.setText(item.getUrl());
        return view;
    }


    private class ViewHolder {
        ImageView imvImageUrl;
        TextView tvTitleUrl;
        TextView tvUrl;
    }

    public boolean filter(String charText) {
        boolean show = false;
        ArrayList<ItemUrl> arrItemSearchTemp = new ArrayList<>();
        arrItemSearchTemp = databaseManager.getAllItem();
        charText = charText.toLowerCase(Locale.getDefault());
        arrItemUrl.clear();
        if (charText.length() == 0) {
            arrItemUrl.clear();
            show = false;
        } else {
            for (ItemUrl item : arrItemSearchTemp) {
                if (item.getTitleUrl().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    arrItemUrl.add(item);
                    show = true;
                }
            }
        }
        notifyDataSetChanged();
        return show;
    }

    public void showAllItem() {
        arrItemUrl.clear();
        arrItemUrl = databaseManager.getAllItem();
        notifyDataSetChanged();
    }

}

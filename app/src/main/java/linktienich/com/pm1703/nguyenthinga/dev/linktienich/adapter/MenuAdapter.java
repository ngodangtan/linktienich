package linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemMenu;

/**
 * Created by Hoang on 6/26/2016.
 */
public class MenuAdapter extends BaseAdapter {

    private ArrayList<ItemMenu> arrItemMenu = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public MenuAdapter(Context context){
        initData();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private void initData() {
        arrItemMenu.add(new ItemMenu(R.drawable.ic_bao_yeu_thich, "Báo yêu thích"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_tintuc, "Tin tức"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_tintuc_giaitr1i, "Tin tức giải trí"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_nghenhac, "Nghe nhạc"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_video_clip, "Video - Film - Clip"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_game, "Game"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_mangxahoi_diendan, "Mạng xã hội - Diễn đàn"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_phunu_giadinh, "Phụ nữ - Gia đình"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_dulich, "Du lịch"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_nhiepanh, "Nhiếp ảnh"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_thethao, "Thể thao"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_taichinh_kinhdoanh, "Tài chính kinh doanh"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_thuongmai_dientu, "Thương mại điện tử"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_batdongsan, "Bất động sản"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_vieclam, "Việc làm"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_oto_xemay, "Ô tô - Xe máy"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_maytinh_congnghe, "Máy tính - Công nghệ"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_suckhoe, "Sức khỏe"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_tailieu_hoctap, "Học tập - Tài liệu"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_doctruyen, "Đọc truyện"));
        arrItemMenu.add(new ItemMenu(R.drawable.ic_dichvutienich, "Dịch vụ tiện ích"));
    }

    @Override
    public int getCount() {
        return arrItemMenu.size();
    }

    @Override
    public ItemMenu getItem(int position) {
        return arrItemMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            view = inflater.inflate(R.layout.item_menu, null);
            holder = new ViewHolder();
            holder.imvImageMenu = (ImageView)view.findViewById(R.id.imv_image_menu);
            holder.tvTitleMenu = (TextView) view.findViewById(R.id.tv_title_menu);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }

        ItemMenu item = arrItemMenu.get(position);
        holder.imvImageMenu.setImageResource(item.getImage());
        holder.tvTitleMenu.setText(item.getTitle());
        return view;
    }

    private class ViewHolder {
        ImageView imvImageMenu;
        TextView tvTitleMenu;
    }
}

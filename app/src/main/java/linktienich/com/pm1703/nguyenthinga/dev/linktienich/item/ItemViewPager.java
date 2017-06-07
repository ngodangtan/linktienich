package linktienich.com.pm1703.nguyenthinga.dev.linktienich.item;

import java.util.ArrayList;

/**
 * Created by Hoang on 6/28/2016.
 */
public class ItemViewPager {
    private String topic;
    private ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();

    public ItemViewPager(String topic, ArrayList<ItemUrl> arrItemUrl) {
        this.topic = topic;
        this.arrItemUrl = arrItemUrl;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<ItemUrl> getArrItemUrl() {
        return arrItemUrl;
    }

    public void setArrItemUrl(ArrayList<ItemUrl> arrItemUrl) {
        this.arrItemUrl = arrItemUrl;
    }
}

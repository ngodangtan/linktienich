package linktienich.com.pm1703.nguyenthinga.dev.linktienich.item;

/**
 * Created by Hoang on 6/28/2016.
 */
public class ItemUrl {
    int id;
    String titleUrl;
    String url;
    int type;
    int yeuThich;
    int enableDelete;
    String urlIcon;

    public ItemUrl(int id, String titleUrl, String url, int type, int yeuThich, int enableDelete, String urlIcon) {
        this.id = id;
        this.titleUrl = titleUrl;
        this.url = url;
        this.type = type;
        this.yeuThich = yeuThich;
        this.enableDelete = enableDelete;
        this.urlIcon = urlIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public int getEnableDelete() {
        return enableDelete;
    }

    public void setEnableDelete(int enableDelete) {
        this.enableDelete = enableDelete;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }
}

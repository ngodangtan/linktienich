package linktienich.com.pm1703.nguyenthinga.dev.linktienich.item;

/**
 * Created by Hoang on 6/26/2016.
 */
public class ItemMenu {
    int image;
    String title;

    public ItemMenu(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

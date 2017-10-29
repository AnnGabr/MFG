package custom.adapters.theme_adapter;

/**
 * Created by Ann on 29.10.2017.
 */

public class Theme {
    private int name;
    private int imgResId;
    private int themeResId;

    public Theme(){}

    public Theme(int name, int imgResId, int themeResId){
        this.name = name;
        this.imgResId = imgResId;
        this.themeResId = themeResId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getThemeResId() {
        return themeResId;
    }

    public void setThemeResId(int themeResId) {
        this.themeResId = themeResId;
    }
}

package custom.adapters.theme_adapter;

/**
 * Created by Ann on 29.10.2017.
 */

public class Theme {
    private String name;
    private int imgResId;
    private int themeResId;

    public Theme(){}

    public Theme(String name, int imgResId, int themeResId){
        this.name = name;
        this.imgResId = imgResId;
        this.themeResId = themeResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

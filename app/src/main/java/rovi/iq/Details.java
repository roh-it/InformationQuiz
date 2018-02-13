package rovi.iq;

/**
 * Created by Rrohi on 12-02-2018.
 */
public class Details {


    private   int image_id;
    private String cat_name;

    public Details(int image_id,String cat_name)
    {
        this.setImage_id(image_id);
        this.setCat_name(cat_name);
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
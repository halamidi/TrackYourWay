package randomcompany.trackyourway;

/**
 * Created by Evan on 09/03/2016.
 */
public class Ratings {
    private String comment;
    private int iconID;

    public Ratings(String comment, int iconID) {
        super();
        this.comment = comment;
        this.iconID = iconID;
    }

    public String getComment() {
        return comment;
    }

    public int getIconID(){
        return iconID;
    }

}

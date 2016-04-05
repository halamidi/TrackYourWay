package randomcompany.trackyourway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Comments_Rating_Activity extends AppCompatActivity {

    private List<Ratings> myRatings = new ArrayList<Ratings>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments__rating_);

        populateRatingsList();
        populateListView();
    }

    private void populateRatingsList() {
        myRatings.add(new Ratings("Brilliant", R.drawable.stars4));
        myRatings.add(new Ratings("NCI is the best all the lecturers are great", R.drawable.stars5));
    }

    private void populateListView() {
        ArrayAdapter<Ratings> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.ratingListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ratings>{

        public MyListAdapter(){
            super(Comments_Rating_Activity.this,R.layout.item_view, myRatings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Make sure our view works may have passed null
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            //Find The Rating to use
            Ratings currentRating = myRatings.get(position);

            //fill the list view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_rating_icon);
            imageView.setImageResource(currentRating.getIconID());

            //Comment
            TextView commentText = (TextView) itemView.findViewById(R.id.item_comment_txt);
            commentText.setText(currentRating.getComment());

            return itemView;
        }

    }

}

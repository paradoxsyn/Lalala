package dj.rabithole.losu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dj.rabithole.losu.R;


public class ImageAdapter extends BaseAdapter {
    private final String[] text;
    private final int[] imageIDs;

    //Integer[] imageIDs = {
      //      R.drawable.anniebutton,
        //    R.drawable.ryzebutton
    //};

    private Context context;

    public ImageAdapter(Context c, String[] text, int[] imageIDs)
    {
        context = c;
        this.imageIDs = imageIDs;
        this.text = text;
    }

    //---returns the number of images---
    public int getCount() {
        return text.length;
        //return imageIDs.length;
    }

    //---returns the ID of an item---
    public Object getItem(int position) {
        return null;
        //return position;
    }

    public long getItemId(int position) {
        return 0;
        // return position;
    }

    //---returns an ImageView view---
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.champ_pic,null);

            TextView textView =  (TextView) grid.findViewById(R.id.grid_text);

            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(text[position]);
            imageView.setImageResource(imageIDs[position]);


            //imageView = new ImageView(context);
            //imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(5, 5, 5, 5);
        } else {
            grid = (View) convertView;
            //imageView = (ImageView) convertView;
        }

        return grid;
        //imageView.setImageResource(imageIDs[position]);
        //return imageView;
    }
}

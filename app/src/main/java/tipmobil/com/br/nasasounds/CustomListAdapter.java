package tipmobil.com.br.nasasounds;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jose.pereira on 30/10/2014.
 */
public class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private MyActivity.Item[] items ;
    MediaPlayer mp;


    public CustomListAdapter(Context context, int textViewResourceId , MyActivity.Item[] list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.textView);
        ImageButton play = (ImageButton) mView.findViewById(R.id.btn_play);
        ImageButton share = (ImageButton) mView.findViewById(R.id.btn_share);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MyActivity)mContext).playSound(position);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MyActivity)mContext).shareSound(position);
            }
        });

        if(items[position] != null )
        {
            int color = Color.argb(150, 55, 59, 68);

            text.setTextColor(Color.WHITE);
            text.setText(items[position].nome);
            text.setTextSize(20);
            text.setBackgroundColor( color );

            play.setBackgroundColor( color );
            share.setBackgroundColor( color );

        }

        return mView;
    }

}
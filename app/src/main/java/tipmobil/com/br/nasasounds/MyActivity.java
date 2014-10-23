package tipmobil.com.br.nasasounds;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;


public class MyActivity extends Activity {

    ListView listView ;
    ArrayAdapter<Item> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, getRawFiles());


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View view, int position, long id) {
                Item sound = (Item)listView.getItemAtPosition(position);
                Log.d("############", "Items " + sound.arquivo);
                MediaPlayer mp = getSound(sound.arquivo);
                if(mp!=null) {
                    mp.start();
                }
            }
        });

    }

    private MediaPlayer getSound(String soundName){
        if(soundName.equals("eagle_has_landed")){
            return MediaPlayer.create(this, R.raw.eagle_has_landed);
        }else if(soundName.equals("computers_are_in_control")){
            return MediaPlayer.create(this, R.raw.computers_are_in_control);
        }else if(soundName.equals("go_for_deploy")){
            return MediaPlayer.create(this, R.raw.go_for_deploy);
        }else if(soundName.equals("houston_problem")){
            return MediaPlayer.create(this, R.raw.houston_problem);
        }else if(soundName.equals("kennedy_we_choose")){
            return MediaPlayer.create(this, R.raw.kennedy_we_choose);
        }else if(soundName.equals("landing_gear_drop")){
            return MediaPlayer.create(this, R.raw.landing_gear_drop);
        }else if(soundName.equals("launch")){
            return MediaPlayer.create(this, R.raw.launch);
        }else if(soundName.equals("launch_nats")){
            return MediaPlayer.create(this, R.raw.launch_nats);
        }else if(soundName.equals("liftoff")){
            return MediaPlayer.create(this, R.raw.liftoff);
        }else if(soundName.equals("saturn_radio_waves")){
            return MediaPlayer.create(this, R.raw.saturn_radio_waves);
        }else if(soundName.equals("small_step")){
            return MediaPlayer.create(this, R.raw.small_step);
        }else if(soundName.equals("sputnik_beep")){
            return MediaPlayer.create(this, R.raw.sputnik_beep);
        }
        return null;
    }

    private Item[] getRawFiles(){

        Field[] fields = R.raw.class.getFields();

        Item[] filename = new Item[fields.length];

        // loop for every file in raw folder
        for(int count=0; count < fields.length; count++){

            try {

                int rid = fields[count].getInt(fields[count]);

                Item item = new Item();
                item.nome = Helper.capitalize(fields[count].getName());
                item.arquivo = fields[count].getName();

                // Use that if you just need the file name
                filename[count] = item;

                // Use this to load the file
                Resources res = getResources();
                InputStream in = res.openRawResource(rid);

                byte[] b = new byte[in.available()];
                in.read(b);
                // do whatever you need with the in stream
            } catch (Exception e) {
                // log error
            }
        }

        return filename;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class Item {
        String nome;
        String arquivo;

        @Override
        public String toString() {
            return nome;
        }
    }

}

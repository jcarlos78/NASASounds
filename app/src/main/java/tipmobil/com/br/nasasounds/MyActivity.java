package tipmobil.com.br.nasasounds;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusOneButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;


public class MyActivity extends FragmentActivity implements PlusOneFragment.OnFragmentInteractionListener {

    ListView listView ;
    ArrayAdapter<Item> adapter;
    MediaPlayer mp;
    PlusOneFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new PlusOneFragment();
        fragmentTransaction.add(R.id.plus_one_button, fragment);
        fragmentTransaction.commit();

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);


        adapter = new CustomListAdapter(MyActivity.this, R.layout.custom_list,getRawFiles());
        listView.setAdapter(adapter);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View view, int position, long id) {
                playSound(position);
            }
        });

    }

    public void playSound(int position){
        if(mp!=null) {
            mp.stop();
        }
        Item sound = (Item)listView.getItemAtPosition(position);
        mp = getSound(sound.arquivo);
        if(mp!=null) {
            mp.start();
        }
    }

    public void shareSound(int position){

        Item sound = (Item)listView.getItemAtPosition(position);
        File dest = Environment.getExternalStorageDirectory();
        InputStream in = getResources().openRawResource(getShare(sound.arquivo));

        try
        {
            OutputStream out = new FileOutputStream(new File(dest, sound.arquivo+".mp3"));
            byte[] buf = new byte[1024];
            int len;
            while ( (len = in.read(buf, 0, buf.length)) != -1)
            {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
        catch (Exception e) {}

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("audio/*");
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() + "/"+sound.arquivo+".mp3"));
        startActivity(Intent.createChooser(share, "Share Sound File"));

    }

    private MediaPlayer getSound(String soundName){
        if(soundName.equals("eagle_has_landed")){
            return MediaPlayer.create(this, R.raw.eagle_has_landed);
        }else if(soundName.equals("computers_in_control")){
            return MediaPlayer.create(this, R.raw.computers_in_control);
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

    private int getShare(String soundName){
        if(soundName.equals("eagle_has_landed")){
            return R.raw.eagle_has_landed;
        }else if(soundName.equals("computers_in_control")){
            return R.raw.computers_in_control;
        }else if(soundName.equals("go_for_deploy")){
            return R.raw.go_for_deploy;
        }else if(soundName.equals("houston_problem")){
            return R.raw.houston_problem;
        }else if(soundName.equals("kennedy_we_choose")){
            return R.raw.kennedy_we_choose;
        }else if(soundName.equals("landing_gear_drop")){
            return R.raw.landing_gear_drop;
        }else if(soundName.equals("launch")){
            return R.raw.launch;
        }else if(soundName.equals("launch_nats")){
            return R.raw.launch_nats;
        }else if(soundName.equals("liftoff")){
            return R.raw.liftoff;
        }else if(soundName.equals("saturn_radio_waves")){
            return R.raw.saturn_radio_waves;
        }else if(soundName.equals("small_step")){
            return R.raw.small_step;
        }else if(soundName.equals("sputnik_beep")){
            return R.raw.sputnik_beep;
        }
        return 0;
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class Item {
        String nome;
        String arquivo;

        @Override
        public String toString() {
            return nome;
        }
    }

    protected void onResume() {
        super.onResume();
    }

}

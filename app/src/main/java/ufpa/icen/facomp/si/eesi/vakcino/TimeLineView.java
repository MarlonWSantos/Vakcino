package ufpa.icen.facomp.si.eesi.vakcino;

import android.content.Intent;
import android.graphics.Color;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import android.graphics.BitmapFactory;
import android.view.View;

public class TimeLineView extends AppCompatActivity {

    //Create Timeline Rows List
    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;
    static int index=0;
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_view);

        voltar = (Button)findViewById(R.id.botaoVoltar);

        Intent intent = getIntent();
        index = intent.getIntExtra("Posicao do item da lista",0);

        BD bd = new BD();
        ArrayList<String> listaVacinasUsuario = new ArrayList<String>();
        ArrayList<String> listaVacinasSistema = new ArrayList<String>();

        listaVacinasSistema = bd.carregaListaVacinas("adulto");
        boolean dadosVacinasNaoVazio = bd.isDadosVacinaNaoVazio();
        System.out.println("usuario"+listaVacinasUsuario);
        System.out.println("sistema"+listaVacinasSistema);
        System.out.println("tem dados"+dadosVacinasNaoVazio);

        if(dadosVacinasNaoVazio) {
            ArrayList<ArrayList<String>> dadosVacina = new ArrayList<ArrayList<String>>();
            System.out.println("antes de baixar vacinas");
            dadosVacina=bd.getInfoVacina();
            System.out.println("apos baixar vacinas");
            System.out.println("dados"+dadosVacina);

            for (int i =0;i<dadosVacina.size();i++) {
                if(dadosVacina.get(i).get(0)==String.valueOf(index)){
                    listaVacinasUsuario.add(dadosVacina.get(i).get(1));
                }
            }
            System.out.println("salvou vacinas na lista do usuario");

            System.out.println("usuario"+listaVacinasUsuario);
            System.out.println("sistema"+listaVacinasSistema);
        }

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TelaInfoUsuario.class);
                i.putExtra("Posicao do item da lista", index);
                startActivity(i);
                finish();
            }
        });

        boolean vacinaFoiTomada = false;
        String nomeVacina ="";

        // Add Random Rows to the List
        for (int i = 0; i < listaVacinasSistema.size(); i++) {
            nomeVacina=listaVacinasSistema.get(i);

            System.out.println("dadosNãoVazio"+dadosVacinasNaoVazio);

            if(dadosVacinasNaoVazio){

                System.out.println(listaVacinasUsuario.contains(nomeVacina));

                if(listaVacinasUsuario.contains(nomeVacina)) {
                    vacinaFoiTomada=true;
                    System.out.println("vai salvar a vacina");
                    timelineRowsList.add(createRandomTimelineRow(i,nomeVacina,vacinaFoiTomada));
                }else{
                    System.out.println("Nao vai salvar vacina");
                    vacinaFoiTomada=false;
                    timelineRowsList.add(createRandomTimelineRow(i,nomeVacina,vacinaFoiTomada));
                }

            }else{
                System.out.println("Nao vai salvar vacina");
                vacinaFoiTomada=false;
                timelineRowsList.add(createRandomTimelineRow(i,nomeVacina,vacinaFoiTomada));
            }
        }

        //Create the Timeline Adapter
        myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,
                //if true, list will be sorted by date
                true);

        //Get the ListView and Bind it with the Timeline Adapter
        ListView myListView = (ListView) findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);

        //if you wish to handle list scrolling
        myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                this.currentScrollState = scrollState;
                //this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    ////on scrolling to end of the list, add new rows
                    for (int i = 0; i < 15; i++) {
                        //myAdapter.add(createRandomTimelineRow(i));
                    }
                }
            }
        });

        //if you wish to handle the clicks on the rows
        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineRow row = timelineRowsList.get(position);
                Toast.makeText(TimeLineView.this, row.getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        myListView.setOnItemClickListener(adapterListener);
    }

    //Method to create new Timeline Row
    private TimelineRow createRandomTimelineRow(int id,String nomeVacina,boolean vacinaFoiTomada) {

        // Create new timeline row (pass your Id)
        TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional)
        //myRow.setDate(getRandomDate());
        //to set the row Title (optional)
        myRow.setTitle(nomeVacina);
        //to set the row Description (optional)
        //myRow.setDescription("Description " + id);
        //to set the row bitmap image (optional)

        if(vacinaFoiTomada){
            myRow.setImage(BitmapFactory.decodeResource(getResources(), R.mipmap.checked));
        }else {
            myRow.setImage(BitmapFactory.decodeResource(getResources(), R.mipmap.nochecked));
        }
        //to set row Below Line Color (optional)
        myRow.setBellowLineColor(0x0ff00bb00);
        //int color = Color.argb(0,0,255,0);
        //myRow.setBellowLineColor(color);
        //to set row Below Line Size in dp (optional)
        //myRow.setBellowLineSize(getRandomNumber(2, 25));
        //to set row Image Size in dp (optional)
        //myRow.setImageSize(getRandomNumber(25, 40));
        myRow.setImageSize(30);
        //to set background color of the row image (optional)
        //myRow.setBackgroundColor(getRandomColor());
        //to set the Background Size of the row image in dp (optional)
        //myRow.setBackgroundSize(getRandomNumber(25, 40));
        //to set row Date text color (optional)
        //myRow.setDateColor(getRandomColor());
        //to set row Title text color (optional)
        //myRow.setTitleColor(getRandomColor());
        //to set row Description text color (optional)
        //myRow.setDescriptionColor(getRandomColor());

        return myRow;
    }

    //Random Methods
    public int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ;
        System.out.println(color);
        return color;
    }

    public int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * max);
    }

    public Date getRandomDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("02/09/2015");
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            endDate = new Date(random);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }
}
package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;


public class PersonnesTrouvesActivity extends Activity {

    int Glob = 0;
    int Max;
    long err;
    String LogUtil;
    ImageView PPImage;
    ArrayList<String> ListeLovers;
    ArrayList<String> ListeAmis;
    ArrayList<String> ListeRejet;
    RechercheFiltres Filtre;
    UtilisateurManager UM = new UtilisateurManager(this);
    RechercheFiltresManager RM = new RechercheFiltresManager(this);
    RelationManager RLM = new RelationManager(this);
    Utilisateur U = new Utilisateur();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil = getIntent().getStringExtra("LogUtil");
        setContentView(R.layout.activity_personnes_trouves);

        ImageView PPImage = (ImageView) findViewById(R.id.ViewPP);

        final TextView LoginV = (TextView) findViewById(R.id.TextLogin);

        final TextView PrenomV = (TextView) findViewById(R.id.TextPrenom);

        final TextView NomV = (TextView) findViewById(R.id.TextNom);

        final TextView DateV = (TextView) findViewById(R.id.TextDate);

        final TextView TelephoneV = (TextView) findViewById(R.id.TextTelephone);

        final TextView MailV = (TextView) findViewById(R.id.TextMail);

        final TextView SexeV = (TextView) findViewById(R.id.TextSexe);

        final TextView VilleV = (TextView) findViewById(R.id.TextVille);

        final TextView OrientationV = (TextView) findViewById(R.id.TextOrientation);

        final TextView CheveuxV = (TextView) findViewById(R.id.TextCheveux);

        final TextView YeuxV = (TextView) findViewById(R.id.TextYeux);

        RM.open();
        Filtre = RM.getRechercheFiltres(LogUtil);
        ListeLovers=RM.getLovers(LogUtil, Filtre);
        RM.close();
        if (ListeLovers != null) {


            for ( int i = 0;  i < ListeLovers.size(); i++){
                String tempName = ListeLovers.get(i);
                if(tempName.equals(LogUtil)){
                    ListeLovers.remove(i);
                }
            }
            //Prends la Liste d'amis et l'enlève
            RLM.open();
            ListeAmis=RLM.getFriendsofUser(LogUtil);
            //ListeRejet=RLM.getRejectedUser(LogUtil);
            RLM.close();

            ListeLovers.removeAll(ListeAmis);
            //ListeLovers.removeAll(ListeRejet);
            Max = ListeLovers.size();


            UM.open();
            U = UM.getInfoUser(ListeLovers.get(Glob), 2);
            UM.close();


            YeuxV.setText(U.getYeux());
            LoginV.setText(U.getLogin());
            PrenomV.setText(U.getPrenom());
            NomV.setText(U.getNom());
            DateV.setText(U.getNom());
            TelephoneV.setText(U.getTelephone());
            MailV.setText(U.getMail());
            SexeV.setText(U.getNom());
            VilleV.setText(U.getVille());
            OrientationV.setText(U.getOrientation());
            CheveuxV.setText(U.getCheveux());
            if (U.getPhoto() != null) {
                PPImage.setImageBitmap(BitmapFactory.decodeByteArray(U.getPhoto(), 0, U.getPhoto().length));
            }


            Button HotButton = (Button) findViewById(R.id.HotButton);
            Button NotButton = (Button) findViewById(R.id.NotButton);


            NotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Glob < Max-1) {
                        Glob++;
                        UM.open();
                        U = UM.getInfoUser(ListeLovers.get(Glob), 2);
                        UM.close();
                        YeuxV.setText(U.getYeux());
                        LoginV.setText(U.getLogin());
                        PrenomV.setText(U.getPrenom());
                        NomV.setText(U.getNom());
                        DateV.setText(U.getNom());
                        TelephoneV.setText(U.getTelephone());
                        MailV.setText(U.getMail());
                        SexeV.setText(U.getNom());
                        VilleV.setText(U.getVille());
                        OrientationV.setText(U.getOrientation());
                        CheveuxV.setText(U.getCheveux());

                        Relation Rej = new Relation();
                        Date currentDate = new Date(System.currentTimeMillis());
                        Rej.setLogin1(LogUtil);
                        Rej.setLogin2(ListeLovers.get(Glob-1));
                        Rej.setTypeRelation("rejet");
                        Rej.setDateRelation(currentDate);

                        RLM.open();
                        err= RLM.addRelation(Rej);
                        RLM.close();



                    }
                    else {}

                }
            });
            HotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Glob < Max-1) {
                        Glob++;
                        UM.open();
                        U = UM.getInfoUser(ListeLovers.get(Glob), 2);
                        UM.close();
                        YeuxV.setText(U.getYeux());
                        LoginV.setText(U.getLogin());
                        PrenomV.setText(U.getPrenom());
                        NomV.setText(U.getNom());
                        DateV.setText(U.getNom());
                        TelephoneV.setText(U.getTelephone());
                        MailV.setText(U.getMail());
                        SexeV.setText(U.getNom());
                        VilleV.setText(U.getVille());
                        OrientationV.setText(U.getOrientation());
                        CheveuxV.setText(U.getCheveux());

                        Relation Req = new Relation();
                        Date currentDate = new Date(System.currentTimeMillis());
                        Req.setLogin1(LogUtil);
                        Req.setLogin2(ListeLovers.get(Glob-1));
                        Req.setTypeRelation("en attente");
                        Req.setDateRelation(currentDate);

                        RLM.open();
                        err= RLM.addRelation(Req);
                        RLM.close();



                    } else {}

                }
            });


        }
    }
}

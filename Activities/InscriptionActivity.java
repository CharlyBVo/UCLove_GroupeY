package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Antoine on 01-05-16.
 */
public class InscriptionActivity extends Activity {
    public String LogUtil;
    Utilisateur a;
    public Utilisateur MainUser ;
    Date temp;
    Bitmap bmcool;
    String EnterLangue;
    String EnterOrientation;
    String EnterGenre;
    UtilisateurManager utilm;
    ImageView ivImage;
    byte[] arrayphoto;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Uri imageUri;
    String fdestination;
    final UtilisateurManager TestManagUtil =new UtilisateurManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription1);

        ivImage= (ImageView) findViewById(R.id.imageView2);
        final EditText EnterLogin = (EditText) findViewById(R.id.editText12);
        final EditText EnterPassword = (EditText) findViewById(R.id.editText13);
        final EditText EnterConfirmPassword = (EditText) findViewById(R.id.editText14);
        final EditText EnterPrenom = (EditText) findViewById(R.id.editText11);
        final EditText EnterNom = (EditText) findViewById(R.id.editText6);
        final EditText EnterDateDeNaissance = (EditText) findViewById(R.id.editText7);
        final EditText EnterTelephone = (EditText) findViewById(R.id.editText8);
        final EditText EnterEmail = (EditText) findViewById(R.id.editText9);
        final EditText EnterVille = (EditText) findViewById(R.id.editText10);

        final Spinner Cheveux1 = (Spinner) findViewById(R.id.spinner);
        final Spinner Cheveux2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner yeux = (Spinner) findViewById(R.id.spinner3);
        Button butAddIm = (Button) findViewById(R.id.button11);
        Button butInscr = (Button) findViewById(R.id.button10);

        RadioGroup Sexe = (RadioGroup) findViewById(R.id.radiogroup4);
        RadioGroup Orientation = (RadioGroup) findViewById(R.id.radiogroup5);
        RadioGroup Langue = (RadioGroup) findViewById(R.id.radiogroup5);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.CheveuxTaille, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Cheveux1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.CheveuxCouleur, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Cheveux2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.YeuxCouleur, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        yeux.setAdapter(adapter3);

        butInscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;


                if (EnterLogin.getText().toString() == null) {
                    Toast.makeText(InscriptionActivity.this, "Veuillez entrer un login", Toast.LENGTH_SHORT).show();
                    ok = false;
                }
                else {
                    TestManagUtil.open();
                    MainUser = TestManagUtil.getMainUtilisateur(EnterLogin.getText().toString());
                    TestManagUtil.close();
                if (EnterLogin.getText().toString().length() < 6) {
                    Toast.makeText(InscriptionActivity.this, "Veuillez entrer un login de minimum 5 caractères", Toast.LENGTH_SHORT).show();
                    ok = false;
                }
                else if (MainUser !=null){
                    Toast.makeText(InscriptionActivity.this, "Login déja existant", Toast.LENGTH_SHORT).show();
                    ok = false;
                }

                    else if (EnterPassword.getText().toString() == null) {
                        Toast.makeText(InscriptionActivity.this, "Veuillez entrer un mot de passe", Toast.LENGTH_SHORT).show();
                    ok = false;
                    } else if (EnterPassword.getText().toString().length() < 7) {
                        Toast.makeText(InscriptionActivity.this, "Veuillez entrer un mot de passe de minimum 6 caractères", Toast.LENGTH_SHORT).show();
                    ok = false;
                    } else if (EnterConfirmPassword.getText().toString() != EnterPassword.getText().toString()) {
                        Toast.makeText(InscriptionActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    ok = false;
                    } else if (EnterPrenom.getText().toString() == null) {
                        Toast.makeText(InscriptionActivity.this, "Veuillez entrer un prénom", Toast.LENGTH_SHORT).show();
                    ok = false;
                    } else if (EnterPrenom.getText().toString().length() < 2) {
                        Toast.makeText(InscriptionActivity.this, "Veuillez entrer un prénom de minimum un caractère", Toast.LENGTH_SHORT).show();
                    ok = false;
                    } else if (EnterDateDeNaissance.getText().toString() != null) {
                        try {
                            temp = new SimpleDateFormat("dd/MM/yyyy").parse(EnterDateDeNaissance.getText().toString());
                            // Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateNoConvert);
                        } catch (Exception e) {
                            Toast.makeText(InscriptionActivity.this, "Veuillez entrer votre de naissance au format JJ/MM/AAAA", Toast.LENGTH_SHORT).show();
                            ok = false;
                        }
                    } else if (EnterEmail.getText().toString() != null) {
                        if (!EnterEmail.getText().toString().contains("@")) {
                            Toast.makeText(InscriptionActivity.this, "Veuillez entrer un mail valide ou ne pas le renseigner", Toast.LENGTH_SHORT).show();
                            ok = false;
                        }
                    }}
                    if (ok) {
                        if (((RadioButton) findViewById(R.id.radioButton10)).isChecked()) {

                            EnterLangue = "Français";
                        } else if (((RadioButton) findViewById(R.id.radioButton11)).isChecked()) {
                            EnterLangue = "Anglais";
                        }

                        if (((RadioButton) findViewById(R.id.radioButton4)).isChecked()) {
                            EnterGenre = "M";
                        } else if (((RadioButton) findViewById(R.id.radioButton5)).isChecked()) {
                            EnterGenre = "F";
                        } else if (((RadioButton) findViewById(R.id.radioButton6)).isChecked()) {
                            EnterGenre = "Autre";
                        }

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        if (bmcool != null) {
                            bmcool.compress(Bitmap.CompressFormat.PNG, 0, stream);
                            arrayphoto = stream.toByteArray();
                        }

                        if (((RadioButton) findViewById(R.id.radioButton7)).isChecked()) {
                            EnterOrientation = "Hétéro";
                        } else if (((RadioButton) findViewById(R.id.radioButton8)).isChecked()) {
                            EnterOrientation = "Bi";
                        } else if (((RadioButton) findViewById(R.id.radioButton9)).isChecked()) {
                            EnterOrientation = "Homo";
                        }

                        a = new Utilisateur(EnterLogin.getText().toString(), EnterPassword.getText().toString(),
                                EnterNom.getText().toString(), EnterPrenom.getText().toString(), EnterVille.getText().toString(),
                                temp, yeux.getSelectedItem().toString(),
                                Cheveux1.getSelectedItem().toString() + " " + Cheveux2.getSelectedItem().toString(),
                                EnterTelephone.getText().toString(), EnterLangue, EnterEmail.getText().toString(), EnterGenre,
                                arrayphoto, EnterOrientation);

                        utilm.open();
                        utilm.modUtilisateur(a);
                        utilm.close();
                        Toast.makeText(InscriptionActivity.this, "Bienvenue", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            );
            butAddIm.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View arg0){
                selectImage();
            }
        });


    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        fdestination = destination.toString();

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
        if(thumbnail.getHeight()>thumbnail.getWidth()){

            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            thumbnail = Bitmap.createBitmap(thumbnail, 0, 0, thumbnail.getWidth(),
                    thumbnail.getHeight(), matrix, true);
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 0, stream);
        bmcool = thumbnail;
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);
        fdestination = selectedImagePath;

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        if(bm.getHeight()>bm.getWidth()){

            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                    bm.getHeight(), matrix, true);
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 0, stream);
        ivImage.setImageBitmap(bm);
        bmcool=bm;
    }

    }

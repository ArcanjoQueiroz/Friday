package br.com.alexandre.friday;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import br.com.alexandre.friday.service.Backend;
import br.com.alexandre.friday.service.BackendService;
import br.com.alexandre.friday.task.Answer;

public class MainActivity extends LocationActivity {

    private static final int TTS_DATA_CHECK = 1;
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    private static final int REQUEST_INTERNET_PERMISSION = 505;
    private static final int REQUEST_READ_CONTACTS_PERMISSION = 506;


    private Backend backend;
    private String userName;
    private TextToSpeech textToSpeech;
    private Button button;
    private Locale locale;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.checkPermissions();

        this.locale = new Locale(getResources().getString(R.string.locale));

        this.backend = new BackendService();

        this.userName = getResources().getString(R.string.user_name);

        this.textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                final int result = textToSpeech.setLanguage(locale);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    final Intent intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                    startActivityForResult(intent, TTS_DATA_CHECK);
                }
            }
        });

        button = (Button) findViewById(R.id.hello_friday);
        button.setOnClickListener(view -> ask() );
    }

    protected void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.INTERNET}, REQUEST_INTERNET_PERMISSION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_CONTACTS_PERMISSION: {
                if (grantResults[0] == 0) {
                    Log.i("Permission", "REQUEST_READ_CONTACTS_PERMISSION granted");
                }
                break;
            }
            case REQUEST_INTERNET_PERMISSION: {
                if (grantResults[0] == 0) {
                    Log.i("Permission", "REQUEST_INTERNET_PERMISSION granted");
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    answer(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
                }
                break;
            }
        }
    }

    private void ask() {
        final String[] languageAndCountry = locale.getLanguage().split("_");
        final String extraLanguage = languageAndCountry[0] + "-" + languageAndCountry[1].toUpperCase();
        Log.i("Language", "Extra Language: " + extraLanguage);

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, extraLanguage);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ask something to Friday!!!");
        this.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
    }

    private void answer(final String text) {
        try {
            Log.i("Request", text);
            new Answer(this.backend, new Answer.Context(userName, getApplication(), this.getLocation()))
                    .execute(text)
                    .get().forEach(this::speak);
        } catch (final InterruptedException e) {
            speak("Ocorreu um erro sistêmico na execução!");
            Log.e("Error", "There is an error on answer: " + e.getMessage(), e);
        } catch (final ExecutionException e) {
            speak("Ocorreu um erro na execução!");
            Log.e("Error", "There is an error on answer: " + e.getMessage(), e);
        }
    }

    private void speak(final String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        } else {
            this.textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "You have selected Settings Menu", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

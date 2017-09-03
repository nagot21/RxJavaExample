package nagot.com.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {
    private Calculations mCalculations;
    private Observable<Integer> mObserver;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculations = new Calculations();
        mObserver = mCalculations.getPrintNumbersObservable();

        mText = (TextView) findViewById(R.id.mainActivityText);

        mObserver.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Observable has been completed", Toast.LENGTH_LONG).show();
                mText.setText("And that's how RxJava Works ;-)");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("number", "Printed number: " + integer + ". Thread running: " + Thread.currentThread().getName());
                mText.setText(integer.toString());
            }
        });
    }
}

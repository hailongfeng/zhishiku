package net.zetetic.mytest;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.zetetic.R;

public class BookDbActivity extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    private Button mBtnAdd;
    private Button mBtnQuery;
    private TextView mTvShow;

    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_db);
        initView();
    }
    private void initView() {
        SQLiteDatabase.loadLibs(this);
        dbHelper = new MyDatabaseHelper(this, "demo.db", null, 1);
        db = dbHelper.getWritableDatabase("secret_key");
        mBtnAdd = (Button) findViewById(R.id.add_data);
        mBtnQuery = (Button) findViewById(R.id.query_data);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnAdd) {
            ContentValues values = new ContentValues();
            values.put("name", "密码");
            values.put("pages", 566);
            db.insert("Book", null, values);
        } else if (v == mBtnQuery) {
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    result += "book name is " + name + "\n";
                    result += "book pages is " + pages + "\n";
                }
            }
            cursor.close();

            mTvShow.setText(result);
            result = "";
        }
    }
}

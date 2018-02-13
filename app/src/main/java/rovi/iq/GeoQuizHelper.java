package rovi.iq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rrohi on 12-02-2018.
 */

public class GeoQuizHelper extends SQLiteOpenHelper{
    private Context context;
    private static final String DB_NAME = "Geo.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME_GEO = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_GEO + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_GEO;

    GeoQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion("Guwahati is situated on the banks of river?", "Brahmaputra ", "Ganga", "Yammuna", "Godavari", "Brahmaputra "));

        arraylist.add(new TriviaQuestion("The Gulf of Mannar is situated along the coast which state in India?", "Karnatka", "Kerala", "Andhra Pradesh", "Tamil Nadu ", "Tamil Nadu "));

        arraylist.add(new TriviaQuestion("The city of Nasik is situated on the banks of which river in India?", " Krishna", "Godavari", "Koshi", "Yammuna", "Godavari"));

        arraylist.add(new TriviaQuestion("Which one of the following pairs is correctly matched?", "Haldia : Orissa", "Jamnagar : Maharashtra", "Numaligarh : Gujarat", "Panangudi : Tamil Nadu", "Panangudi : Tamil Nadu"));

        arraylist.add(new TriviaQuestion("Which one of the following rivers originates near Mahabaleshwar ?", "Godavari", "Krishna ", "Kaveri", "Tapi", "Krishna "));

        arraylist.add(new TriviaQuestion("With reference to the climate of India, the western disturbances originate over which one of the following ?", "Arabian Sea ", "Baltic Sea", "Caspian Sea", "Mediterranean Sea", "Arabian Sea "));

        arraylist.add(new TriviaQuestion("In which one of the following states is the Nanga Parbat peak located ?", "Sikkim", "Himachal Pradesh", "Jammu and Kashmir ", "Uttarakhand", "Jammu and Kashmir "));

        arraylist.add(new TriviaQuestion("In India, which of the following are the Southernmost hills ?", "Anaimalai hills", "Cardamom hills", " Nilgiri hills", "Javacli hills", "Cardamom hills"));

        arraylist.add(new TriviaQuestion("Where are the coal reserves of India largely concentrated ?", "Son valley", " Mahanadi valley", "Damodar valley", " Godavari valley", "Damodar valley"));

        arraylist.add(new TriviaQuestion("Which of the following Indian island lies between India and Sri Lanka ?", "Elephanta", "Nicobar", " Rameshwaram ", "Salsette", "Rameshwaram "));



        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME_GEO, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME_GEO, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }



}

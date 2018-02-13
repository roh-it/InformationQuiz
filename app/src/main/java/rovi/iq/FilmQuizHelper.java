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

public class FilmQuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "film.db";


    private static final int DB_VERSION = 5;
    private static final String TABLE_NAME_FILM = "TQ";
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
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_FILM + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_FILM;

    FilmQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

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

        arraylist.add(new TriviaQuestion("Filmfare award started from the year", "1952", "1954 ", "1960", "1964", "1954 "));

        arraylist.add(new TriviaQuestion("First Indian movie submitted for Oscars", "Mother India", "Amrapali", "Madhumati", "The Guide", "Mother India"));

        arraylist.add(new TriviaQuestion("Which Indian film has been chosen as India’s official entry in the Best Foreign Language Film category at Oscars 2018?", "Newton", "Toilet: Ek Prem Katha", "Bhoomi", "Shubh Mangal Savdhan", "Newton"));

        arraylist.add(new TriviaQuestion("Which movie has become India’s first movie to earn Rs.2000 crore?", "M S Dhoni", "Dangal", "Sultan", "3 Idiots", "Dangal"));

        arraylist.add(new TriviaQuestion("Which city will host the 2017 Whatashort Independent International Film Festival (WIIFF)?", "New Delhi", "Kolkata", "Pune", "Kochi", "New Delhi"));

        arraylist.add(new TriviaQuestion("KR Mohanan is associated with which of the following?", "Journalism", "Politics", "Sports", "Film Maker", "Film Maker"));

        arraylist.add(new TriviaQuestion("Which one film declared as the Best Film in 62nd Filmfare award?", "Nil Battey Sannata", "Aye Dil Hai Mushkil", "Dangal", "Sultan", "Dangal"));

        arraylist.add(new TriviaQuestion("Which Indian-origin filmmaker has been honoured with the Sikh Jewel Award for 2017?", "Y K Sinha", "Sukhbeer Singh Sandhu", "Gurinder Chadha", "Permlata Singh", "Gurinder Chadha"));

        arraylist.add(new TriviaQuestion("Sabita Chowdhury, the noted singer has passed away. She hailed from which state?", "Uttar Pradesh", "Maharashtra", "Odisha", "West Bengal", "West Bengal"));

        arraylist.add(new TriviaQuestion("Who was the first Indian to make a movie?", "Dhundiraj Govind Phalke", " Asha Bhonsle", " Ardeshir Irani", "V. Shantaram", "Dhundiraj Govind Phalke"));

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
                db.insert(TABLE_NAME_FILM, null, values);
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
        Cursor cursor = db.query(TABLE_NAME_FILM, coloumn, null, null, null, null, null);


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

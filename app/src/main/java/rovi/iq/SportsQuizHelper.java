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

public class SportsQuizHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DB_NAME = "SpQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 4;
    //Table name
    private static final String TABLE_NAME_SP = "TQ";
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
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_SP + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_SP;

    SportsQuizHelper(Context context) {
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

        arraylist.add(new TriviaQuestion("Former Australian captain Mark Taylor has had several nicknames over his playing career.", "Tubby", "Stodge", "Helium Bat", "Stumpy", "Stumpy"));

        arraylist.add(new TriviaQuestion("Which was the 1st non Test playing country to beat India in an international match?", "Canada", "Sri Lanka", "Zimbabwe", "East Africa", "Sri Lanka"));

        arraylist.add(new TriviaQuestion("Track and field star Carl Lewis won how many gold medals at the 1984 Olympic games?", "Two", "Three", "Four", "Eight", "Four"));

        arraylist.add(new TriviaQuestion("Which county did Ravi Shastri play for?", "Glamorgan", "Leicestershire", "Gloucestershire", "Gloucestershire", "Glamorgan"));

        arraylist.add(new TriviaQuestion("Who was the first Indian to win the World Amateur Billiards title?", "Geet Sethi", "Wilson Jones", "Michael Ferreira", "Manoj Kothari", "Wilson Jones"));

        arraylist.add(new TriviaQuestion("Who is the first Indian woman to win an Asian Games gold in 400m run?", "P.T.Usha", "M.L.Valsamma", "Kamaljit Sandhu", "K.Malleshwari", "Kamaljit Sandhu"));

        arraylist.add(new TriviaQuestion("When was Amateur Athletics Federation of India established?", "1936", "1946", "1956", "1966", "1946"));

        arraylist.add(new TriviaQuestion("Who did Stone Cold Steve Austin wrestle at the 1998 edition of \"Over the Edge\"?\n", "Cactus Jack", "Mankind", "Dude Love", "Mick Foley", "Dude Love"));

        arraylist.add(new TriviaQuestion("Ricky Ponting is also known as what?", "The Rickster", "Ponts", "Ponter", "Punter", "Punter"));

        arraylist.add(new TriviaQuestion("Which two counties did Kapil Dev play?", "Northamptonshire & Worcestershire", "Northamptonshire & Warwickshire", " Nottinghamshire & Worcestershire", "Nottinghamshire & Warwickshire", "Northamptonshire & Worcestershire"));



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
                db.insert(TABLE_NAME_SP, null, values);
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
        Cursor cursor = db.query(TABLE_NAME_SP, coloumn, null, null, null, null, null);


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

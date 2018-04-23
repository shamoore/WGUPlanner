package com.smoo182.wguplanner.data;

import com.smoo182.wguplanner.data.datatypes.Assessment;
import com.smoo182.wguplanner.data.datatypes.Course;
import com.smoo182.wguplanner.data.datatypes.Mentor;
import com.smoo182.wguplanner.data.datatypes.Note;
import com.smoo182.wguplanner.data.datatypes.Quote;
import com.smoo182.wguplanner.data.datatypes.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//To be replaced with SQLite db
public class FakeDataSource implements DataSourceInterface {


    @Override
    public List<Term> getTermList() {
        return null;
    }

    @Override
    public List<Course> getCourseList() {
        return null;
    }

    @Override
    public List<Assessment> getAssessmentList() {
        return null;
    }

    @Override
    public List<Mentor> getMentorList() {
        return null;
    }

    @Override
    public List<Note> getNoteList() {
        return null;
    }

    @Override
    public Term addNewTerm() {
        return null;
    }

    @Override
    public void deleteTerm() {

    }

    @Override
    public Course addNewCourse() {
        return null;
    }

    @Override
    public void deleteCourse() {

    }

    @Override
    public Assessment addNewAssessment() {
        return null;
    }

    @Override
    public void deleteAssessment() {

    }

    @Override
    public Mentor addNewMentor() {
        return null;
    }

    @Override
    public void deleteMentor() {

    }

    @Override
    public Note addNewNote() {
        return null;
    }

    @Override
    public void deleteNote() {

    }

    @Override
    public List<Quote> getQuoteList() {
        ArrayList<Quote> quotesArrayList = new ArrayList<>();
        quotesArrayList.add(new Quote("“If you hear a voice within you say ‘you cannot paint,’ then by all means paint, and that voice will be silenced.”", "– Vincent Van Gogh"));
        quotesArrayList.add(new Quote("“Don’t go around saying the world owes you a living. The world owes you nothing. It was here first.”", "– Mark Twain"));
        quotesArrayList.add(new Quote("“Success consists of going from failure to failure without loss of enthusiasm.”", "– Winston Churchil"));
        quotesArrayList.add(new Quote("“Your time is limited, don’t waste it living someone else’s life. Don’t be trapped by dogma, which is living the result of other people’s thinking. Don’t let the noise of other’s opinion drowned your own inner voice. And most important, have the courage to follow your heart and intuition, they somehow already know what you truly want to become. Everything else is secondary.”", "– Steve Jobs"));
        quotesArrayList.add(new Quote("“When one door closes, another opens; but we often look so long and so regretfully upon the closed door that we do not see the one which has opened for us.”", "– Alexander Graham Bell"));
        quotesArrayList.add(new Quote("“Don’t say you don’t have enough time. You have exactly the same number of hours per day that were given to Helen Keller, Pasteur, Michelangelo, Mother Teresa, Leonardo da Vinci, Thomas Jefferson, and Albert Einstein.”", "– H. Jackson Brown Jr."));
        quotesArrayList.add(new Quote("“Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.”", "– Thomas A. Edison"));
        quotesArrayList.add(new Quote("“If you don’t go after what you want, you’ll never have it. If you don’t ask, the answer is always no. If you don’t step forward, you’re always in the same place.”", "– Nora Roberts"));
        quotesArrayList.add(new Quote("“Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle.”", "– Christian D. Larson"));
        quotesArrayList.add(new Quote("“Success is the sum of small efforts, repeated day in and day out.”", "– Robert Collier"));
        quotesArrayList.add(new Quote("“Failure is the opportunity to begin again more intelligently.”", "– Henry Ford"));
        quotesArrayList.add(new Quote("“There are no secrets to success. It is the result of preparation, hard work, and learning from failure.”", "– Colin Powell"));
        quotesArrayList.add(new Quote("“The will to win, the desire to succeed, the urge to reach your full potential... these are the keys that will unlock the door to personal excellence.”", "– Confucius"));
        quotesArrayList.add(new Quote("“I am always doing what I cannot do yet in order to learn how to do it.”", "– Vincent Van Gogh"));
        quotesArrayList.add(new Quote("“The future belongs to those who believe in the beauty of their dreams.”", "– Eleanor Roosevelt"));
        quotesArrayList.add(new Quote("“And will you succeed? Yes you will indeed! (98 and 3/4 percent guaranteed.”", "– Dr. Seuss"));
        quotesArrayList.add(new Quote("“It always seems impossible until it's done. ”", "– Nelson Mandela"));

        return quotesArrayList;
    }

    @Override
    public Quote getRandomQuote() {
        ArrayList<Quote> list = (ArrayList<Quote>) getQuoteList();
        Random random = new Random();
        return list.get(random.nextInt(list.size()));

    }
}


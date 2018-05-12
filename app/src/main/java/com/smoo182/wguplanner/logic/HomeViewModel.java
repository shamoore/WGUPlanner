package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.data.datatypes.Quote;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private PlannerRepository plannerRepository;

    @Inject
    HomeViewModel(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public LiveData<Quote> getRandomQuote() {
        return this.plannerRepository.getRandomQuote();
    }

    public void InsertQuotes() {
        new AddQuoteTask().execute(new Quote("“If you hear a voice within you say ‘you cannot paint,’ then by all means paint, and that voice will be silenced.”", "– Vincent Van Gogh"));
        new AddQuoteTask().execute(new Quote("“Don’t go around saying the world owes you a living. The world owes you nothing. It was here first.”", "– Mark Twain"));
        new AddQuoteTask().execute(new Quote("“Success consists of going from failure to failure without loss of enthusiasm.”", "– Winston Churchil"));
        new AddQuoteTask().execute(new Quote("“When one door closes, another opens; but we often look so long and so regretfully upon the closed door that we do not see the one which has opened for us.”", "– Alexander Graham Bell"));
        new AddQuoteTask().execute(new Quote("“Don’t say you don’t have enough time. You have exactly the same number of hours per day that were given to Helen Keller, Pasteur, Michelangelo, Mother Teresa, Leonardo da Vinci, Thomas Jefferson, and Albert Einstein.”", "– H. Jackson Brown Jr."));
        new AddQuoteTask().execute(new Quote("“Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.”", "– Thomas A. Edison"));
        new AddQuoteTask().execute(new Quote("“If you don’t go after what you want, you’ll never have it. If you don’t ask, the answer is always no. If you don’t step forward, you’re always in the same place.”", "– Nora Roberts"));
        new AddQuoteTask().execute(new Quote("“Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle.”", "– Christian D. Larson"));
        new AddQuoteTask().execute(new Quote("“Success is the sum of small efforts, repeated day in and day out.”", "– Robert Collier"));
        new AddQuoteTask().execute(new Quote("“Failure is the opportunity to begin again more intelligently.”", "– Henry Ford"));
        new AddQuoteTask().execute(new Quote("“There are no secrets to success. It is the result of preparation, hard work, and learning from failure.”", "– Colin Powell"));
        new AddQuoteTask().execute(new Quote("“The will to win, the desire to succeed, the urge to reach your full potential... these are the keys that will unlock the door to personal excellence.”", "– Confucius"));
        new AddQuoteTask().execute(new Quote("“I am always doing what I cannot do yet in order to learn how to do it.”", "– Vincent Van Gogh"));
        new AddQuoteTask().execute(new Quote("“The future belongs to those who believe in the beauty of their dreams.”", "– Eleanor Roosevelt"));
        new AddQuoteTask().execute(new Quote("“And will you succeed? Yes you will indeed! (98 and 3/4 percent guaranteed.”", "– Dr. Seuss"));
        new AddQuoteTask().execute(new Quote("“It always seems impossible until it's done. ”", "– Nelson Mandela"));
    }


    private class AddQuoteTask extends AsyncTask<Quote, Void, Void> {

        @Override
        protected Void doInBackground(Quote... quote) {
            plannerRepository.createNewQuote(quote[0]);
            return null;
        }
    }
}



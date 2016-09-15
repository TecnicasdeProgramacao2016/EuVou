package com.mathheals.euvou.controller.show_event;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mathheals.euvou.R;
import com.mathheals.euvou.controller.utility.LoginUtility;
import com.mathheals.euvou.controller.utility.Mask;

import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import java.util.ArrayList;

import dao.CategoryDAO;
import dao.EvaluatePlaceDAO;
import dao.EventCategoryDAO;
import dao.EventDAO;
import dao.EventEvaluationDAO;
import exception.EventEvaluationException;
import model.EventEvaluation;


public class ShowEvent extends android.support.v4.app.Fragment implements View.OnClickListener
{

    private TextView eventCategoriesText;
    private EventDAO eventDAO;
    private final String PRICE_COLUMN = "price";
    private String eventPrice;
    private TextView eventPriceText;
    private Button showEventOnMapButton, participateButton;
    private String eventLongitude;
    private String eventLatitude;
    private String eventId;

    private final String GO = "#EUVOU";
    private final String NOTGO = "#NÃOVOU";

    private final String SUCCESSFULL_EVALUATION_MESSAGE = "Avaliação cadastrada com sucesso";

    // fields for Event Evaluation
    private final Integer LOGGED_OUT = -1;
    private int userId;
    private boolean isUserLoggedIn;
    private TextView ratingMessage;
    private View showEventView;
    private RatingBar ratingBar;
    private EventEvaluation eventEvaluation;

    public ShowEvent()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // private TextView eventCategoriesText;
        // private EventDAO eventDAO;
        // private final String PRICE_COLUMN = "price";
        // private String eventPrice;
        // private Button showEventOnMapButton, participateButton;
        // private String eventLongitude;
        // private String eventLatitude;
        // private String eventId;
        // private final String GO = "#EUVOU";
        // private final String NOTGO = "#NÃOVOU";

        // private final Integer LOGGED_OUT = -1;
        //private int userId;

        setShowEventView(inflater.inflate(R.layout.fragment_show_event, container, false));
        showEventOnMapButton = (Button) showEventView.findViewById(R.id.showEventOnMapButton);
        participateButton = (Button) showEventView.findViewById(R.id.EuVou);
        showEventOnMapButton.setOnClickListener(this);
        participateButton.setOnClickListener(this);

        eventDAO = new EventDAO(this.getActivity());
        eventId = this.getArguments().getString("id");
        JSONObject eventDATA = eventDAO.searchEventById(Integer.parseInt(eventId));

        setUserId(new LoginUtility(getActivity()).getUserId());
        if(userId == LOGGED_OUT)
        {
            participateButton.setVisibility(showEventView.GONE);
        }
        else
        {
            participateButton.setVisibility(View.VISIBLE);
            if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) == null)
            {
                participateButton.setText(GO);
            }
            else
            {
                participateButton.setText(NOTGO);
            }
        }

        try
        {

            String eventNameDB = eventDATA.getJSONObject("0").getString("nameEvent");
            String eventAdress = eventDATA.getJSONObject("0").getString("address");
            String eventDescription = eventDATA.getJSONObject("0").getString("description");
            String eventDateTime = eventDATA.getJSONObject("0").getString("dateTimeEvent");
            eventPrice = eventDATA.getJSONObject("0").getString(PRICE_COLUMN);
            eventLongitude = eventDATA.getJSONObject("0").getString("longitude");
            eventLatitude = eventDATA.getJSONObject("0").getString("latitude");

            TextView name1Event = (TextView) showEventView.findViewById(R.id.nameEventShow);
            TextView dateEvent = (TextView) showEventView.findViewById(R.id.dateEvent);
            TextView description = (TextView) showEventView.findViewById(R.id.descriptionEvent);
            TextView addressShow = (TextView) showEventView.findViewById(R.id.eventPlaces);
            eventCategoriesText = (TextView) showEventView.findViewById(R.id.eventCategories);
            eventPriceText = (TextView) showEventView.findViewById(R.id.eventPrice);
            name1Event.setText(eventNameDB);
            description.setText(eventDescription);
            dateEvent.setText(Mask.getDateTimeInBrazilianFormat(eventDateTime));
            setPriceText(eventPriceText, eventPrice);
            setCategoriesText(new Integer(eventId), eventCategoriesText);
            addressShow.setText(eventAdress);

        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        catch (NullPointerException exception)
        {
            Toast.makeText(getActivity(), "O nome não foi encontrado", Toast.LENGTH_LONG);
        }

        setIsUserLoggedIn(userId != LOGGED_OUT);
        setRatingMessage(isUserLoggedIn);
        setRatingBarIfNeeded();

        return showEventView;
    }


    private String[] getEventCategoriesById(int eventId)
    {
        final String ID_CATEGORY = "idCategory";
        final String NAME_CATEGORY = "nameCategory";
        final String FIRST_COLUMN = "0";

        EventCategoryDAO eventCategoryDAO = new EventCategoryDAO(getActivity());
        JSONObject eventCategoryJSON = eventCategoryDAO.searchCategoriesByEventId(eventId);
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());


        ArrayList<String> categories = new ArrayList<>();

        for(int i = 0; i < eventCategoryJSON.length(); ++i)
        {
            try
            {
                int categoryId = eventCategoryJSON.getJSONObject(Integer.toString(i)).getInt(ID_CATEGORY);
                JSONObject categoryJSON = categoryDAO.searchCategoryById(categoryId);
                String categoryName = categoryJSON.getJSONObject(FIRST_COLUMN).getString(NAME_CATEGORY);
                categories.add(categoryName);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        String[] categoriesArray = new String[categories.size()];
        categoriesArray = categories.toArray(categoriesArray);

        return categoriesArray;
    }

    public void setCategoriesText(int eventId, TextView eventCategoriesText)
    {
        String[] eventCategories = getEventCategoriesById(eventId);
        String text = eventCategories[0];
        for(int i = 1; i < eventCategories.length; ++i)
            text += (", " + eventCategories[i]);
        eventCategoriesText.setText(text);
    }

    public void setPriceText(TextView eventPriceText, String eventPrice)
    {
        // private String eventPrice;
        final int PRICE = new Integer(eventPrice);
        final String REAIS_PART = Integer.toString(PRICE / 100);
        final String CENTS = Integer.toString(PRICE % 100);
        final String CENTS_PART = CENTS.length() > 1 ? CENTS : "0" + CENTS;
        eventPriceText.setText("R$ " + REAIS_PART + "," + CENTS_PART);

        return;
    }

    private void showEventOnMap()
    {
        // private String eventLongitude;
        //  private String eventLatitude;
        Bundle latitudeAndLongitude = new Bundle();
        latitudeAndLongitude.putStringArray("LatitudeAndLongitude", new String[]{eventLatitude, eventLongitude});
        Intent intent = new Intent(getContext(), ShowOnMap.class);
        intent.putExtras(latitudeAndLongitude);
        startActivity(intent);
    }
    private void markParticipate()
    {
        // private EventDAO eventDAO;
        // private int userId;

        if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) != null)
            Toast.makeText(getActivity(), "Heyy, você já marcou sua participação", Toast.LENGTH_SHORT).show();
        else
        {
            eventDAO.markParticipate(userId, Integer.parseInt(eventId));
            Toast.makeText(getActivity(),"Salvo com sucesso" , Toast.LENGTH_SHORT).show();
        }
    }
    private void markOffParticipate()
    {
        // private EventDAO eventDAO;
        // private int userId;

        if(eventDAO.verifyParticipate(userId,Integer.parseInt(eventId)) == null)
            Toast.makeText(getActivity(), "Heyy, você já desmarcou sua participação", Toast.LENGTH_SHORT).show();
        else
        {

            eventDAO.markOffParticipate(userId, Integer.parseInt(eventId));
            Toast.makeText(getActivity(),"Salvo com sucesso" , Toast.LENGTH_SHORT).show();
        };
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.showEventOnMapButton:
                showEventOnMap();
                break;

            case R.id.EuVou:
                if(((Button)view.findViewById(R.id.EuVou)).getText().equals(GO))
                    markParticipate();
                else
                    markOffParticipate();
                break;
        }
    }

    // private int userId;
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setIsUserLoggedIn(boolean isUserLoggedIn)
    {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    private void setRatingMessage(boolean isUserLoggedIn)
    {
        final String LOGGED_IN_MESSAGE = "Sua avaliação:";
        final String LOGGED_OUT_MESSAGE = "Faça login para avaliar este evento!";
        String message = isUserLoggedIn ? LOGGED_IN_MESSAGE : LOGGED_OUT_MESSAGE;

        ratingMessage = (TextView) showEventView.findViewById(R.id.rate_event_text);
        ratingMessage.setText(message);
    }

    public void setShowEventView(View showEventView)
    {
        this.showEventView = showEventView;
    }

    private void setRatingBarIfNeeded()
    {
        if(isUserLoggedIn)
            setRatingBar();
    }


    private void setRatingBar()
    {
        // private String eventId;
        // private int userId;
        ratingBar = (RatingBar) showEventView.findViewById(R.id.ratingBar);
        ratingBar.setVisibility(View.VISIBLE);

        EventEvaluationDAO eventEvaluationDAO = new EventEvaluationDAO();

        JSONObject evaluationJSON = eventEvaluationDAO.searchEventEvaluation(Integer.parseInt(eventId), userId);

        if(evaluationJSON!=null)
        {
            Float evaluation = null;
            try
            {
                evaluation = new Float(evaluationJSON.getJSONObject("0").getDouble("grade"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            ratingBar.setRating(evaluation);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar arg0, float rateValue, boolean arg2)
            {
                setEventEvaluation(rateValue, userId, new Integer(eventId));

                EventEvaluationDAO eventEvaluationDAO = new EventEvaluationDAO();

                eventEvaluationDAO.evaluateEvent(getEventEvaluation());
            }
        });

        setRatingBarStyle();
    }

    public EventEvaluation getEventEvaluation()
    {
        return eventEvaluation;
    }

    public void setEventEvaluation(Float rating, Integer userId, Integer eventId)
    {
        //private final String SUCCESSFULL_EVALUATION_MESSAGE = "Avaliação cadastrada com sucesso";

        try
        {
            this.eventEvaluation = new EventEvaluation(rating, userId, eventId);
            Toast.makeText(getActivity().getBaseContext(), SUCCESSFULL_EVALUATION_MESSAGE, Toast.LENGTH_LONG).show();
        }
        catch (EventEvaluationException exception)
        {
            if(exception.getMessage() == EventEvaluation.EVALUATION_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
            if(exception.getMessage() == EventEvaluation.EVENT_ID_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
            if(exception.getMessage() == EventEvaluation.USER_ID_IS_INVALID)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }

            /*

            if(   exception.getMessage() == EventEvaluation.EVALUATION_IS_INVALID ||
                  exception.getMessage() == EventEvaluation.EVENT_ID_IS_INVALID ||
                  exception.getMessage() == EventEvaluation.USER_ID_IS_INVALID )
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }

            */
        }
    }

    private void setRatingBarStyle()
    {
/*        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.turquesa_app), PorterDuff.Mode.SRC_ATOP);
 */ }
}

/**
 * file:Consult.java
 * purpose:class to do the consultation to the database
 */
package dao;

import android.os.AsyncTask;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consult
{
    private final String DEFALT_STRING_MESSAGE = " ";
    private  String url = DEFALT_STRING_MESSAGE;
    private String result  = DEFALT_STRING_MESSAGE;
    private boolean isDoing;
    private String query = DEFALT_STRING_MESSAGE;
    private final String PARAM = "query";
    private final static Logger logger = Logger.getLogger(Consult.class.getName());
    public Consult(String query, String url)
    {
        assert(query != null);
        assert(url != null);

        this.query= query;
        this.url = url;

        setIsDoing(false);
    }

    public boolean getIsDoing()
    {
        return isDoing;
    }

    public void setIsDoing(boolean value)
    {
        isDoing = value;
    }

    public String exec()
    {

        logger.log(Level.INFO,"entered in the method that makes the connection to access the database");
        new Access().execute();
        return getResult();
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        assert(result != null);
        this.result = result;
    }

    private class Access extends AsyncTask<String, String, String>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args)
        {

            try
            {

                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                pairs.add(new BasicNameValuePair(PARAM, query));

                HttpPost post = new HttpPost(url);
                post.setEntity(new UrlEncodedFormEntity(pairs));

                HttpClient client = new DefaultHttpClient();
                HttpResponse   response = client.execute(post);

                result = inputStreamToString(response.getEntity().getContent()).toString();
                setIsDoing(true);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result)
        {
            assert(result != null);
            Consult.this.setIsDoing(true);
        }

        private StringBuilder inputStreamToString(InputStream is) throws IOException
        {
            assert(is != null);

            String rLine = "";

            InputStreamReader streamReader = new InputStreamReader(is);

            BufferedReader rd = new BufferedReader(streamReader);

            StringBuilder answer = new StringBuilder();

            while ((rLine = rd.readLine()) != null)
            {
                answer.append(rLine);
            }
            return answer;
        }
    }


}
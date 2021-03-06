/*
* File name: EventTest.
* File pourpose: Test if an event is valid.
*/

package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Vector;

import exception.EventException;
import model.Event;



public class EventTest extends TestCase
{
    private static Event event;  //Event that is going to be used at tests

    //Test if event's category is null
    public void testIfCategoryIsNull ()
    {
        boolean ok = true; //Checks if there is an exception
        try
        {
            event = new Event(3,"Swing na casa do Miranda",  10010, "casa do miranda",
                                "25/10/2016", "e trenzinho e eu fico no meio",
                                "1", "2", null);
            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        }
        catch (EventException eventException)
        {
            ok = false;
        }
        catch (ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
    }

    //Test if event's category is empty
    public void testIfCategoryIsEmpty()
    {
        Vector<String> categories = new Vector<>(); //List with all categories
        boolean ok = true; //Checks if there is an exception
        try
        {
            event = new Event(3,"Swing na casa do Miranda", 10010, "casa do miranda",
                                "25/10/2016", "e trenzinho e eu fico no meio","1",
                                "2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch(EventException eventException)
        {
            ok = false;
        }
        catch (ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if event's category is not null
    public void testIfCategoryIsNotNull ()
    {
        boolean ok = true; //Checks if there is an exception
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Museus");

        try
        {
            event = new Event(3,"Swing na casa do Miranda", 10010, "casa do miranda",
                                "25/10/2016", "e trenzinho e eu fico no meio", "1",
                                "2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        }
        catch (EventException eventException)
        {
            ok = false;
        }
        catch (ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
        finilize(categories);
    }

    //Test if event's name is empty
    public void testNameEmpty() throws ParseException
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Educacao");

        boolean ok = true;  //Checks if there is an exception //List with all the categories

        try
        {
            event = new Event(3,"", 10010, "casa do miranda", "25/10/2016",
                                "e trenzinho e eu fico no meio", "50.01",
                                "60.002", categories);

            String event_name = event.getNameEvent();
            int event_name_size = event_name.length();
            final int NAME_MAX_SIZE = 30;
            if(event_name_size > NAME_MAX_SIZE)
            {
                assertTrue(false); //Name bigger than allowed can indicate security failure
            }
            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch(EventException eventException)
        {
            ok = false;
        } catch(ParseException parsetException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if event's name is not empty
    public void testNameIsNotEmpty()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Balada");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event =  new Event(3,"Geovanni", 10010, "casa do miranda", "25/10/2016",
                                 "e trenzinho e eu fico no meio","21.4","30.2",
                                 categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch(EventException eventException)
        {
            ok = false;
        } catch(ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
        finilize(categories);
    }

    //Test name's length
    public void testNameIsBiggerThanMax()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Museus");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Joãozinho da Silva Gosta da Dilma, venham conhecer esse jovem muito jovem",
                              10010, "casa do miranda", "25/10/2016",
                              "e trenzinho e eu fico no meio","50.8","60.2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch (EventException eventException)
        {
            eventException.printStackTrace();
            ok = false;
        } catch(ParseException paseExecption)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }


    //Test if event's description is empty
    public void testIfDescriptionIsEmpty()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Show");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"FG Party", 10010, "casa do miranda", "25/10/2016",
                              "","44.2","46.2", categories);
            ok = true;

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }

        } catch(EventException e)
        {
            ok = false;
        } catch(ParseException ex)
        {
            ok=false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if event's description if not empty
    public void testIfDescriptionIsNotEmpty()
    {
        Vector<String> categories = new Vector<String>();  //List with all the categories
        categories.add("Cinema");
        boolean ok = true; //Checks if there is an exception
        try
        {
            event = new Event(3,"FG a Party",10010, "FGA","10/10/2016",
                              "Venha se perder com a gente!", "44.2","65.2",
                              categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            if(event.getEvaluation()>10 || event.getEvaluation()<0)
            {
                throw new EventException("Invalid Evaluation");
            }
            else
            {
                //NOTHINHG TO DO
            }

        }catch(EventException eventException)
        {
            ok = false;
        }catch(ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
        finilize(categories);
    }

    //Test if event's description is bigger than possivel
    public void testifDescriptionGoesOverTheMaximumValue()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Teatro");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"FG a Party",10010, "FGA","10/10/2016", "Test\n" + "Test\n"
                    +"Test\n" + "Test\n" + "Test\n" + "Test\n" + "Test\n" + "Test\n" + "Test\n" +
                    "Test\n" + "Test", "55.2","79.9", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }

        } catch(EventException eventException)
        {
            ok = false;
        } catch(ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test latitude is too big
    public void testIfLatitudeIsUnderMinus90()
    {
        Vector<String> categories = new Vector<String >();  //List with all the categories
        categories.add("Esportes");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Evento teste",10010, "endereco", "12/12/2016",
                              "Descrição","-90.9","140.2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch(EventException eventException)
        {
            ok = false;
        } catch(ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if latitude is too small
    public void testIfLatitudeIsAbove90()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Museus");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Evento teste",10010, "endereco", "12/12/2016",
                              "Descrição","99.2","130.2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }
        } catch(EventException eventException)
        {
            ok = false;
        } catch(ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if latitude is valid
    public void testIfLatitudeIsOk()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Museus");
        boolean ok = true; //Checks if there is an exception
        try
        {
            event = new Event(3,"Evento teste",10010, "endereco", "12/12/2016",
                              "Descrição","-40.9","140.2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }

        }catch(EventException eventException)
        {
            ok = false;
        }catch(ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
        finilize(categories);
    }

    //Test if longitude is too small
    public void testIfLongitudeIsUnderMinus180()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Outros");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Evento teste",10010, "endereco", "12/12/2016",
                                "Descrição","90.0","-181.1", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }

        }catch(EventException eventException)
        {
            ok = false;
        }catch(ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if longitude is too big
    public void testIfLongitudeIsAbove180()
    {
        Vector<String> categories = new Vector<String >(); //List with all the categories
        categories.add("Museus");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Evento teste",10010, "endereco", "12/12/2016",
                                "Descrição","19.2","190.2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }

        }catch(EventException eventException)
        {
            ok = false;
        }catch(ParseException parseException)
        {
            ok = false;
        }

        assertFalse(ok);
        finilize(categories);
    }

    //Test if longitude if valid
    public void testIfLongitudeIsOk()
    {
        Vector<String> categories = new Vector<>(); //List with all the categories
        categories.add("Museus");
        boolean ok = true; //Checks if there is an exception

        try
        {
            event = new Event(3,"Evento teste", 10010, "endereco", "12/12/2016",
                                "Descrição", "1", "2", categories);

            if(event == null)
            {
                throw new EventException("Evento nulo");
            }
            else
            {
                //NOTHINHG TO DO
            }

        }catch(EventException eventException)
        {
            ok = false;
        }catch(ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
        finilize(categories);
    }


    //Test if event constructor add's event's id
    public void testEventConstructorWithId() throws ParseException, EventException
    {
        Vector<String> categories = new Vector<>();  //List with all the categories
        categories.add("Esporte");
        Event event = new Event(1,"nome",10010, "endereco", "14/02/2020 20:15:10","DESCRICAO",
                                "12.20","78.41520", categories); //Event to be tested
        assertEquals("nome",event.getNameEvent());

        finilize(categories);
    }

    //Free the memory that the categories vector use
    private void finilize(Vector<String> categories)
    {
        for(String category : categories)
        {
            category = null;
        }
        categories = null;
    }

}
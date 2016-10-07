/*
* File name: PlaceTest.
* File pourpose: Test registration place.
* Created by: viny on 20/10/15.
* Edited by: bernardohrl on 10/09/16
*/

package com.mathheals.euvou;

import junit.framework.TestCase;

import java.text.ParseException;

import exception.PlaceException;
import model.Place;


public class PlaceTest extends TestCase
{

    //Test if got name value
    public void testGetName()
    {
        boolean ok = true;
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                    "rua das flores","33613110");
            assertTrue(place.getName().equals("Nome"));

        } catch (PlaceException placeException)
        {
            ok = false;
        } catch (ParseException parseException)
        {
            ok = false;
        }

        assertTrue(ok);
    }

    //Test if place's name is empty
    public void testEmptyName()
    {
        boolean ok = false;
        try
        {
            Place place= new Place(null,"2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertFalse(ok);
        }
    }

    //Test if place's name is valid
    public void testValidName()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertTrue(ok);
        }
    }

    //Test if place's latitude is empty
    public void testEmptyLatitude()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Pizza","2",null,"14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception eception)
        {
            ok = false;
        } finally
        {
            assertFalse(ok);
        }
    }

    //Test if place's longitude is empty
    public void testEmptyLongitude()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Pizza","2","14.0025",null,"8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertFalse(ok);
        }
    }

    //Test if latitude is valid
    public void testValidLatitude()
    {
        boolean ok = false;
        try
        {
            Place place = new Place("Pizza","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertTrue(ok);
        }
    }

    //Test if place's longitude is valid
    public void testValidLongitude()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Pizza","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertTrue(ok);
        }
    }

    //Test if latitude's convertion is valid
    public void testConvertLatitude()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Pizza","2","","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertFalse(ok);
        }
    }

    //Test if longitude's convertion is valid
    public void testConvertLongitude()
    {
        boolean ok = false;
        try
        {
            Place place= new Place("Pizza","2","14.0025","","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            ok = true;
        } catch (Exception exception)
        {
            ok = false;
        } finally
        {
            assertFalse(ok);
        }
    }

    //Test if latitude value was gotten
    public void testGetLatitude()
    {
        boolean ok = true;
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertTrue(place.getLatitude() == 14.0025);

        } catch (PlaceException placeException)
        {
            ok = false;
        } catch (ParseException parseException)
        {
            ok = false;
        }
        assertTrue(ok);
    }

    //Test if longitude value was gotten
    public void testGetLongitude()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertTrue(place.getLongitude() == 14.0025);

        } catch (PlaceException placeException)
        {
            assertTrue(false);
        } catch (ParseException parseException)
        {
            assertTrue(false);
        }
    }

    //Test if adress value was gotten
    public void testGetAddress()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertTrue(place.getAddress().equals("rua das flores"));

        } catch (PlaceException placeException)
        {
            assertTrue(false);
        } catch (ParseException parseException)
        {
            assertTrue(false);
        }
    }

    //Test if place's name is valid
    public void testInvalidName()
    {
        try
        {
            Place place= new Place("","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");

            assertFalse(true);
        } catch (PlaceException placeException)
        {
            assertFalse(false);
        } catch (ParseException parseException)
        {
            assertFalse(false);
        }
    }

    //Test if is possible to add an comment
    public void testAddComment()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            place.addComment("numero1");
            place.addComment("numero2");
            assertTrue(place.getComment().size() == 2);

        } catch (PlaceException placeException)
        {
            assertTrue(false);
        } catch (ParseException parseException)
        {
            assertTrue(false);
        }
    }

    //Test if comment is null
    public void testAddNullComment()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores","33613110");
            place.addComment(null);
            assertFalse(place.getComment().size() == 1);

        } catch (PlaceException placeException)
        {
            assertFalse(false);
        } catch (ParseException parseException)
        {
            assertFalse(false);
        }
    }

    //Test if comment is empty
    public void testAddEmptyComment()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição","rua das flores","33613110");
            place.addComment("");
            assertFalse(place.getComment().size() == 1);

        } catch (PlaceException placeException)
        {
            assertFalse(false);
        } catch (ParseException parseException)
        {
            assertFalse(false);
        }
    }

    //Test if comment's value was gotten
    public void testGetComment()
    {
        try
        {
            Place place= new Place("Nome","2","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            place.addComment("numero1");
            place.addComment("numero2");
            assertTrue(place.getComment().get(0).equals("numero1") &&
                       place.getComment().get(1).equals("numero2"));

        } catch (PlaceException placeException)
        {
            assertTrue(false);
        } catch (ParseException parseException)
        {
            assertTrue(false);
        }
    }

    //Test if wrong evaluation can be set
    public void testSetWrongEvaluated()
    {
        try
        {
            Place place= new Place("Nome","ghj","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertFalse(true);
        } catch (PlaceException placeException)
        {
            assertFalse(false);
        } catch (ParseException parseException)
        {
            assertFalse(false);
        } catch (NumberFormatException numberFormatException)
        {
            assertFalse(false);
        }
    }

    //Test if evaluation is set
    public void testSetEvaluated()
    {
        try
        {
            Place place= new Place("Nome","4.5","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertTrue(true);
        } catch (PlaceException placeException)
        {
            assertTrue(false);
        } catch (ParseException parseException)
        {
            assertTrue(false);
        } catch (NumberFormatException numberFormatException)
        {
            assertTrue(false);
        }
    }

    //Test if evaluation string can be null
    public void testSetNullStringEvaluated()
    {
        boolean ok = true;
        try
        {
            Place place= new Place("Nome","null","14.0025","14.0025","8h às 12h","Descrição",
                                    "rua das flores","33613110");
            assertTrue(true);
        } catch (PlaceException placeException)
        {
            ok = false;
        } catch (ParseException parseException)
        {
            ok = false;
        } catch (NumberFormatException numberFormatException)
        {
            ok = false;
        }

        assertTrue(ok);
    }
}

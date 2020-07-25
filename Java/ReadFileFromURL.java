import java.util.Scanner;

public class ReadFileFromURL
{
   public static void main(String[] args)
   {
      int state = 0, first_time = 1, i = 0, indx;
      final int MAX_MOVIES = 20;
      char ch;
      String URLString = "http://www.boxofficemojo.com/weekend/chart/";
      String movieName     = "";
      String movieGrossAmt = "";

      try
      {
         java.net.URL url = new java.net.URL(URLString);
         int count = 0;

         Scanner input = new Scanner(url.openStream());
    
         while (input.hasNext())
         {
            String line = input.nextLine();
            count += line.length();

            switch (state)
            {
               case 0:
                  if (line.contains("<br><table border"))
                  {
                     i = 0;
                     state = 1;
                  }
               break;

               case 1:
                  if (line.contains("movies"))
                  {
                     if (line.contains("<b>"))
                     {
                        if ((indx = line.indexOf("<b>")) != -1)
                        {
                           indx += 3;

                           while ((ch = line.charAt(indx)) != '<')
                           {
                              movieName = movieName + ch;
                              indx++;
                           }

                           state = 2;
                        }
                     }
                  }
               break;

               case 2:
                  if (line.contains("right"))
                  {
                     if (line.contains("<b>"))
                     {
                        if ((indx = line.indexOf("<b>")) != -1)
                        {
                           indx += 3;
 
                           if (first_time == 1)
                           {
                              System.out.printf("  ------------ TOP 20 Weekend Movies ------------\n");
                              first_time = 0;
                           }

                           while ((ch = line.charAt(indx)) != '<')
                           {
                              movieGrossAmt = movieGrossAmt + ch;
                              indx++;
                           }

                           System.out.printf("  %2d) %-28.28s   %s\n",
                                  i+1, movieName, movieGrossAmt);

                           movieName = "";
                           movieGrossAmt = "";

                           if (++i >= MAX_MOVIES)
                              state = 3;
                           else
                              state = 1;
                        }
                     }
                  }
               break;
            }
         }
      }

      catch (java.net.MalformedURLException ex)
      {
         System.out.println("Invalid URL");
      }

      catch (java.io.IOException ex)
      {
         System.out.println("I/O Errors : no such file");
      }
   }
}

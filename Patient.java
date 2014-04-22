// Class representing patient
// Holds information about patient

public class Patient
{
   String name;
   String surname;
   long IDnumber;
   int urgency = 0;
   int queueNumber;
   String colour;
   
   // get methods
   public int getUrgency()
   {
      return urgency;
   }
   
   public long getID()
   {
      return IDnumber;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getSurname()
   {
      return surname;
   }
   
   public String getColour()
   {
      return colour;
   }
   
   // constructor
   public Patient(String firstName, String lastName, long ID, int urg)
   {
      name = firstName;
      surname = lastName;
      IDnumber = ID;
      urgency = urg;
      
      // here we define colour codes based on urgency. this is only relevant for later when we split into three queues based on colour
      // if it falls in a certain bracket of urgency, it is assigned a certain colour
      if (urgency <= 20)
      {
         colour = "RED";
      }
      else if (urgency <= 40)
      {
         colour = "YELLOW";
      }
      else
      {
         colour = "GREEN";
      }
   }
}
// Class representing patient
// Holds information about patient

public class Patient
{
   String name;
   String surname;
   long IDnumber;
   int urgency = 0;
   int queueNumber;
   
   // get methods
   public int getUrgency()
   {
      return urgency;
   }
   
   // constructor
   public Patient(String firstName, String lastName, long ID, int urg)
   {
      name = firstName;
      surname = lastName;
      IDnumber = ID;
      urgency = urg;
   }
}
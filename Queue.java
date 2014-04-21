import java.lang.Math;

public class Queue
{
   Patient[] patientQueue;
   static int spotsFilled = 0;
   
   public void insertPatient(Patient newPerson)
   {
      boolean inserted = false;
      int parentIndex;
      int childIndex = spotsFilled;
      Patient parent = null;
      int urgency = newPerson.getUrgency();
      
      while (inserted == false)
      {
         parentIndex = (int) Math.floor((childIndex-1)/2);
         parent = patientQueue[parentIndex];
                  
         if (urgency < patientQueue[parentIndex].getUrgency())
         {
            patientQueue[parentIndex] = newPerson;
            patientQueue[childIndex] = parent;
            childIndex = parentIndex;
         }
         else
         {
            patientQueue[childIndex] = newPerson;
            inserted = true;
         }
      }
      
      spotsFilled += 1;
   }
   
   public void treatPatients()
   {
      
   }
   
   // constructor   
   public Queue()
   {
      patientQueue = new Patient[100];
      Patient initialise = new Patient("", "", 0, 0);
      patientQueue[0] = initialise;
   }
}
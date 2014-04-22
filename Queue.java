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
   
   public void treatPatient()
   {
      boolean deleted = false;
      int parentIndex = 0;
      int leftIndex;
      int rightIndex;
      Patient lastEntry = patientQueue[(spotsFilled - 1)];
      
      for (int i = 0; i <= 9; i++)
      {
         if (patientQueue[i] != null)
         {
            System.out.println((i+1)+". "+patientQueue[i].getUrgency());
         }
      }
            
      while (deleted == false)
      {
         // note: deletion only occurs once all nodes have percolated upwards and we arrive at the last node (which is empty) and insert the rightmost entry on the final level
         // therefore set "deleted = true" at place of insertion of lastEntry
         
         // could produce out of bounds exception if indices > 9
         leftIndex = parentIndex*2 + 1;
         rightIndex = parentIndex*2 + 2;
         
         // in the case where both are out of bounds, we have a situation where the node has no children
         // left index being out of bounds implies that right index is too
         // we simply execute the case where it has no children, described below
         if (leftIndex > 9)
         {
            // here we just need to assign the last entry here as this node would already had to have percolated upwards to be reached
            patientQueue[parentIndex] = lastEntry;
            deleted = true;
         }
         
         // in the case where the right one is out of bounds and the left one isn't, the left child must be of index 99
         // this means we must execute the case where we assign the left child to the parent node (percolating it up)
         // it's left child will have no children so we may as well assign the left child null at thi stage and then end
         else if (leftIndex == 9)
         {
            patientQueue[parentIndex] = patientQueue[9];
            patientQueue[9] = null;
            deleted = true;
         }
         
         // the case where left is out of bounds but right isnt doesn't exist since left index is always 1 less than right index
         
         
         // if both child nodes exists and are within bounds
         // reaching this statement predicates that they're within bounds or one of the previous statements would have executed
         else if ((patientQueue[leftIndex]!=null)&&(patientQueue[rightIndex]!=null))
         {
            if ( patientQueue[leftIndex].getUrgency() <= patientQueue[rightIndex].getUrgency())
            {
               patientQueue[parentIndex] = patientQueue[leftIndex];
               patientQueue[leftIndex] = null;
               parentIndex = leftIndex;
            }
            else
            {
               patientQueue[parentIndex] = patientQueue[rightIndex];
               patientQueue[rightIndex] = null;
               parentIndex = rightIndex;
            }
         }
         // else if the left one exists but not the right one; statement only includes existence of left one because previous statement predicates that not both of them exist
         else if(patientQueue[leftIndex]!=null)
         {
            patientQueue[parentIndex] = patientQueue[leftIndex];
            patientQueue[leftIndex] = null;
            parentIndex = leftIndex;
         }
         // else if the right one exists but not the left one
         else if(patientQueue[rightIndex]!=null)
         {
            patientQueue[parentIndex] = patientQueue[rightIndex];
            patientQueue[rightIndex] = null;
            parentIndex = rightIndex;
         }
         // else (i.e. neither exists)
         // this means the entries in the heap have percolated up and we have a situation where either:
         // A: this node is null (been deleted / percolated upward) so we assign the last entry here
         // or B: this is the final node to be deleted; i.e. not null and needing to be assigned null; it IS the lastEntry
         else
         {
            if (patientQueue[parentIndex] == null)
            {
               patientQueue[parentIndex] = lastEntry;
               deleted = true;
            }
            else
            {
               patientQueue[parentIndex] = null;
               deleted = true;
            }
         }
      }
      
      patientQueue[(spotsFilled - 1)] = null;
         
      spotsFilled = spotsFilled - 1;

   }
   
   public Patient[] getQueueArray()
   {
      return patientQueue;
   }
   
   // constructor   
   public Queue()
   {
      patientQueue = new Patient[10];
      Patient initialise = new Patient("", "", 0, 0);
      patientQueue[0] = initialise;
   }
}
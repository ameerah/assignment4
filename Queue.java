// Class that has queue functions: e.g. adding a patient to the queue, dequeueing the patient

import java.lang.Math;

public class Queue
{
   Patient[] patientQueue;
   int spotsFilled = 0; // counts how many non-empty Patient items there are in the array; initialised at zero since nobody is added yet
   
   public void insertPatient(Patient newPerson)
   {
      // method to queue patients 
      // binary heap, array implementation
      
      boolean inserted = false;
      int parentIndex;
      int childIndex = spotsFilled; // child (patient to be queued) initially starts at the end
      Patient parent = null;
      int urgency = newPerson.getUrgency();
      
      // inserts at rightmost position on lowest level then percolates up
      while (inserted == false)
      {
         parentIndex = (int) Math.floor((childIndex-1)/2); // works out where parent is located in the array
         parent = patientQueue[parentIndex];
         
         // if the inserted patient is more urgent than it's current parent, percolate up         
         if (urgency < patientQueue[parentIndex].getUrgency())
         {
            patientQueue[parentIndex] = newPerson;
            patientQueue[childIndex] = parent;
            childIndex = parentIndex;
         }
         // inerted patient is less urgent than parent; stay where it is
         else
         {
            patientQueue[childIndex] = newPerson;
            inserted = true;
         }
      }
      
      spotsFilled += 1;
   }
   
   public void insertAtPosition(Patient newPerson, int position)
   {
      // method to queue patients 
      // binary heap, array implementation
      
      boolean inserted = false;
      int parentIndex;
      int childIndex = position; // child (patient to be re-queued) enters where gap is (this given as input)
      Patient parent = null;
      int urgency = newPerson.getUrgency();
      
      // inserts at given index then percolates up
      while (inserted == false)
      {
         parentIndex = (int) Math.floor((childIndex-1)/2); // works out where parent is located in the array
         parent = patientQueue[parentIndex];
         
         // if the inserted patient is more urgent than it's current parent, percolate up         
         if (urgency < parent.getUrgency())
         {
            patientQueue[parentIndex] = newPerson;
            patientQueue[childIndex] = parent;
            childIndex = parentIndex;
         }
         // inserted patient is less urgent than parent or of equal urgency; stay where it is
         else
         {
            patientQueue[childIndex] = newPerson;
            inserted = true;
         }
      }
   }
   
   // creates duplicate of the same heap
   public Patient[] clonePatients()
   {
      // need one exact copy for one heap, three doctors modification
      Patient[] clone = new Patient[10];
      for (int i = 0; i <= 9; i++)
      {
         clone[i] = patientQueue[i];
      }
      
      return clone;
   }
   
   public void treatPatient()
   {
      boolean deleted = false;
      int parentIndex = 0;
      int leftIndex;
      int rightIndex;
      Patient lastEntry = patientQueue[(spotsFilled - 1)];
      
      
      // printing output
      String name = patientQueue[0].getName();
      String surname = patientQueue[0].getSurname();
      long ID = patientQueue[0].getID();
      int urgency = patientQueue[0].getUrgency();
      String colour = patientQueue[0].getColour();
      
      System.out.println("Now treating Patient ID: "+ID+" - "+name+" "+surname+"; Code: "+colour+"; urgency: "+urgency);
      
      // while the deletion process has not completed (completion decided when we reach a node with no children)
      while (deleted == false)
      {
         leftIndex = 2*parentIndex + 1;
         rightIndex = 2*parentIndex + 2;
         
         // does this node have a left child? (not null, within bounds)
         // yes: check for right child
         // no: implies there can't be no right child because binary heaps fill from left to right; therefore: stand-alone node
         if ((leftIndex <= 9)&&(patientQueue[leftIndex] != null)) // within bounds and non-empty
         {
            // has non-empty left node within bounds
            // does it have a right node?
            if ((rightIndex > 9)||(patientQueue[rightIndex] == null))
            {
               // right node is out of bounds or empty
               // leaves left node to percolate up
               patientQueue[parentIndex] = patientQueue[leftIndex];
               parentIndex = leftIndex;
               patientQueue[leftIndex] = null;
            }
            else
            {
               // has a right node
               // consider which node is more urgent
               if (patientQueue[leftIndex].getUrgency() <= patientQueue[rightIndex].getUrgency())
               {
                  // left is more urgent or of equal urgency
                  // therefore left percolates up
                  patientQueue[parentIndex] = patientQueue[leftIndex];
                  patientQueue[leftIndex] = null;
                  parentIndex = leftIndex;
               }
               else
               {
                  // right is more urgent or of equal urgency
                  // therefore rigth percolates up
                  patientQueue[parentIndex] = patientQueue[rightIndex];
                  patientQueue[rightIndex] = null;
                  parentIndex = rightIndex;
               }
            }
         }
         else // has no left child therefore no right child
         {
            // is it root?
            if (parentIndex == 0)
            {
               // root with no children implies last patient to be treated
               patientQueue[0] = null;
               deleted = true;
            }
            else
            {
               // not root. no children => all nodes below it have percolated up
               // does this mean the final node that would usually be inserted at this end was percolated up?
               if (parentIndex == (spotsFilled - 1))
               {
                  // spots filled = number of non-empty array items; less one gives the index of where the rightmost node on the lowest level is
                  // in this scenario, the node we're looking at is where the node that shifts would have been
                  // already been percolated up so no need to do anything else here
                  deleted = true;
               }
               else
               {
                  // not root but not where the rightmost node is
                  // the rightmost node now needs to be moved here to maintain binary heap structure
                  // this shifting satifies the condition that it is filled from left to right but may disrupt order property
                  insertAtPosition(lastEntry, parentIndex);
                  patientQueue[(spotsFilled-1)] = null;
                  deleted = true;
               }
            }
         }
      }
      
      spotsFilled = spotsFilled - 1;
   }
   
   public void printWaitlist(int numDoctors)
   {
      // printing output
      String name;
      String surname;
      long ID;
      int urgency;
      String colour;
      
      if (numDoctors == 3)
      {
         System.out.println("Could the above please proceed to Doctors Smith, Raj and Pieters respectively.\n\n");
      }
      else
      {
         System.out.println("Please go to Dr Smith's room.\n\n");
      }
      
      System.out.println("Those still waiting to be seen to are:");
      
      int i = 0;
      while (patientQueue[i] != null)
      {
         name = patientQueue[i].getName();
         surname = patientQueue[i].getSurname();
         ID = patientQueue[i].getID();
         urgency = patientQueue[i].getUrgency();
         colour = patientQueue[i].getColour();
         
         System.out.println((i+1)+". Patient ID: "+ID+" - "+name+" "+surname+"; Code: "+colour+"; urgency: "+urgency);
         
         i += 1;
      }
      System.out.println("The doctor(s) will see to you shortly.\n");
   }
   
   // constructors   
   public Queue()
   {
      // constructor for first instantiation of queue when no patients have been added yet
      patientQueue = new Patient[10];
      Patient initialise = new Patient("", "", 0, 0);
      patientQueue[0] = initialise;
   }
   
   public Queue(Patient[] duplicateArray, int num)
   {
      // creates duplicate queue for one heap, three doctors modification
      patientQueue = duplicateArray;
      spotsFilled = num;
   }
}
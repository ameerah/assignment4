Author: Ameerah Allie
Date: 23 April 2014
Assignment 4

Description: Using binary heap structure (array implementation) to simulate the arrival and treatment of patients in a hospital ER

Sources: assignment4.java, StreamGenerator.java, Queue.java, Patient.java, names.txt, surnames.txt

Instructions:
1. Use makefile to compile (command prompt "make")
2. Run (command prompt "make run")

Notes on the code and program:
I chose not to include the actual bit of code that creates the delay in time between treating patients or batches of patients because it just serves to delay the running time of the entire program when in fact this delay is completely arbitrary and not accurately representing a real time delay in patient treatment. Instead, we can analytically say that we treat patients in "batches" where, with one doctor, there is only one patient per batch and, with three doctors, there are three patients per batch. Each batch should take roughly equal times to treat if we ignore the fact that more serious ailments should take longer to treat (a broken leg patient would take long with scans and body examinations and discussions about the patient's pain and where it is, as opposed to a non-serious patient coming in with a case of flu which involves the prescription of medication, dispensing it and then sending them on their way).

The total treatment time of all 10 patients is equal to the time it takes to treat each patient. Instead with three doctors, the total treatment time is the sum of the times taken to treat each batch (which is equal to the longest time taken to treat an individual patient in the batch since we this can't consider parallel processes or threads and we can't have a doctor treating the next patient as soon as he's done with the first).

This means that treatment time in the "one doctor, one heap" phase is the sum of ten individual times whereas the treatment time in the "one heap, three doctors" phase will be, AT WORST, the four largest of those individual times (10 patients results in 4 batches of three or less patients).

Theoretically and empirically, the "three heaps, three doctors" phase should be the same as the "one heap, three doctors" phase because the assignment of the patients to three different heaps does not affect the time taken to treat them, the rate at which patients are treated and the order in which they're treated. It's completely pointless to code this.

I also only chose to simulate the arrival of 10 patients and not a larger number because 10 was big enough to illustrate my understand of the binary heap structure but not fill the terminal with too many lines of output after displaying each intermediate heap after every deletion.

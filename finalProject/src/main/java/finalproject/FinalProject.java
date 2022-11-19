/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package finalproject;

/**
 *
 * @author Cole Yonkers
 */
import java.util.Arrays;
import java.util.Random;

public class FinalProject {

    public static void main(String[] args) {

        thirdExperiment();

    }

    //step 1-4;
    public static void firstExperiment() {

        //declare necessary variables.
        int N = 1000;
        double alpha = .005;
        double beta = .01;
        Random random = new Random();
        random.setSeed(1234567890);

        Person[] population = new Person[1000];
        //We could waste time checking every single persons infection status, or we could keep an array of indexes of which people are infected.
        int[] indexesOfInfected = new int[1000];
        //While there are dynamic ways to check for how many people are infected, this is far simpler (and faster) as long as you increment and/or decrement properly. 
        int numInfected = 0;
        //create array of 1000 people
        for (int i = 0; i < N; i++) {
            population[i] = new Person();

        }

        indexesOfInfected[0] = 1;
        numInfected++;

        //Who're the lucky winners of Covid 2.0?
        for (int i = 0; i < 2000; i++) {

            //go through every infected person
            for (int l = 0; l < numInfected; l++) {
                //go through there social connections
                for (int j = 0; j < (int) (alpha * N); j++) {
                    //Sick people can have contact with sick people, this is fine. 
                    int subject = random.nextInt(1000);
                    if (random.nextInt(100) == 1) {
                        if (population[subject].infected == 0 && population[subject].fullImmunity != 1) {

                            population[subject].setInfected();
                            //TODO: Theres a bug that causes there to be one more infected person then intended. This is fine, but it'd be nicer to find the bug.
                            if (numInfected != 1000) {
                                indexesOfInfected[numInfected] = 1;
                                numInfected++;
                            }

                        }

                    }
                }
            }

            System.out.println("Experiment 1 Has " + numInfected + " Infected Individuals on round " + i);

        }

    }

    //step 6, TODO: Report amount of people infected that round. 
    public static void secondExperiment() {

        //declare necessary variables.
        int N = 1000;
        double alpha = .005;
        double beta = .01;
        Random random = new Random();
        random.setSeed(1234567890);

        Person[] population = new Person[1000];
        //We could waste time checking every single persons infection status, or we could keep an array of indexes of which people are infected. After clearing someone from the index, likely should 
        //should shift array appropriately for ease of use and avoid holes.
        int[] indexesOfInfected = new int[1000];
        //While there are dynamic ways to check for how many people are infected, this is far simpler (and faster) as long as you increment and/or decrement properly. 
        int numInfected = 0;
        int numInfectedThisRound = 0;
        //create array of 1000 people
        for (int i = 0; i < N; i++) {
            population[i] = new Person();

        }

        for (int i = 0; i < 2000; i++) {
            numInfectedThisRound = 0;
            //Handle the timer for infected individuals, more complicated then I thought. 
            if (i > 0) {
                int temp = 0;
                while (indexesOfInfected[temp] != 0) {
                    population[temp].infectionCountdown();
                    if (population[temp].infected == 0) {
                        indexesOfInfected[temp] = 0;
                        population[temp].fullImmunity = 1;
                        
                    }
                    temp++;
                }
                
                int[] buffer = new int[indexesOfInfected.length];
                for (int j = 0; j < indexesOfInfected.length; j++) {
                    if (indexesOfInfected[j] != 0) {
                        buffer[j] = indexesOfInfected[j];
                        indexesOfInfected[j] = 0;
                    }
                }

                //copy buffer into indexes, whole purpose of this is to remove holes in the Index. 
                for (int j = 0; j < indexesOfInfected.length; j++) {
                    //Stops the loop when buffer has run out of input
                    if (buffer[j] == 0) {
                        break;
                    }
                    indexesOfInfected[j] = buffer[j];

                }
            } else if (i == 0) {
                for (int j = 0; j < 20; j++) {
                    int index = random.nextInt(1000);
                    indexesOfInfected[j] = index;
                    population[index].setInfected();
                    numInfected++;
                }
            }

            for (int l = 0; l < numInfected; l++) {
                //go through there social connections
                for (int j = 0; j < (int) (alpha * N); j++) {
                    int subject = random.nextInt(1000);
                    //TODO: Implement beta into if statement here
                    if (random.nextInt(100) == 1) {
                        if (population[subject].infected == 0 && population[subject].fullImmunity != 1) {

                            population[subject].setInfected();
                            //TODO: Theres a bug that causes there to be one more infected person then intended. This is fine, but it'd be nicer to find the bug.
                            if (numInfected != 1000) {
                                indexesOfInfected[numInfected] = 1;
                                numInfected++;
                                numInfectedThisRound++;
                            }

                        }

                    }

                }

            }

            System.out.println("On round " + i + " there are " + numInfected + " individuals who have been infected so far, " + numInfectedThisRound  + " have been infected this round.");
                
        }
    }

    //step 7
    public static void thirdExperiment() {

        //declare necessary variables.
        int N = 1000;
        double alpha = .005;
        double beta = .01;
        Random random = new Random();
        random.setSeed(1234567890);

        Person[] population = new Person[1000];
        //We could waste time checking every single persons infection status, or we could keep an array of indexes of which people are infected. After clearing someone from the index, likely should 
        //should shift array appropriately for ease of use and avoid holes.
        int[] indexesOfInfected = new int[1000];
        int[] indexesOfImmune = new int[1000];
        //While there are dynamic ways to check for how many people are infected, this is far simpler (and faster) as long as you increment and/or decrement properly. 
        int numInfected = 0;
        int numImmune = 0;
        int numInfectedThisRound = 0;
        //create array of 1000 people
        for (int i = 0; i < N; i++) {
            population[i] = new Person();

        }

        for (int i = 0; i < 2000; i++) {
            numInfectedThisRound = 0;
            //Handle immunity timer countdown
            int temp = 0;
            if (i > 0) {
                while (indexesOfImmune[temp] != 0) {
                    population[temp].immunityCountdown();
                    if (population[temp].immunityTimer == 0) {

                        indexesOfImmune[temp] = 0;
                        numImmune--;

                    }
                    temp++;

                    int[] buffer = new int[1000];
                    for (int j = 0; j < indexesOfImmune.length; j++) {
                        if (indexesOfImmune[j] != 0) {
                            buffer[j] = indexesOfImmune[j];
                            indexesOfImmune[j] = 0;
                        }

                    }

                    for (int j = 0; j < indexesOfImmune.length; j++) {

                        if (buffer[j] == 0) {
                            break;
                        }

                        indexesOfImmune[j] = buffer[j];

                    }

                }

            }

            //Handle the timer for infected individuals, create index of immune people who have recovered
            if (i > 0) {
                temp = 0;
                while (indexesOfInfected[temp] != 0) {
                    population[temp].infectionCountdown();
                    if (population[temp].infected == 0) {
                        indexesOfInfected[temp] = 0;
                        population[temp].immunityCountdown(20);
                        indexesOfImmune[temp] = temp;
                        numImmune++;
                    }
                    temp++;
                }
                int[] buffer = new int[indexesOfInfected.length];
                for (int j = 0; j < indexesOfInfected.length; j++) {
                    if (indexesOfInfected[j] != 0) {
                        buffer[j] = indexesOfInfected[j];
                        indexesOfInfected[j] = 0;
                    }
                }

                //copy buffer into indexes, whole purpose of this is to remove holes in the Index. 
                for (int j = 0; j < indexesOfInfected.length; j++) {
                    //Stops the loop when buffer has run out of input
                    if (buffer[j] == 0) {
                        break;
                    }
                    indexesOfInfected[j] = buffer[j];

                }

            } else if (i == 0) {
                for (int j = 0; j < 20; j++) {
                    int index = random.nextInt(1000);
                    indexesOfInfected[j] = index;
                    population[index].setInfected();
                    numInfected++;
                }
            }

            for (int l = 0; l < numInfected; l++) {
                //go through there social connections
                for (int j = 0; j < (int) (alpha * N); j++) {
                    int subject = random.nextInt(1000);
                    if (random.nextInt(100) == 1) {
                        if (population[subject].infected == 0 && population[subject].immunityTimer == 0) {

                            population[subject].setInfected();
                            //TODO: Theres a bug that causes there to be one more infected person then intended. This is fine, but it'd be nicer to find the bug.
                            if (numInfected != 1000) {
                                indexesOfInfected[numInfected] = 1;
                                numInfected++;
                                numInfectedThisRound++;
                            }

                        }

                    }

                }

            }

            System.out.println("On round " + i + " there are " + numInfected + " individuals who have been infected so far, " + numInfectedThisRound  + " have been infected this round.");

        }

    }

}

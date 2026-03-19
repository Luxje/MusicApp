package com.example.MusicApp;

import java.util.Random;

public class Logic {
    public void shuffleArray(int[] array) {
        Random random = new Random();

        // Start from the last element and swap one by one
        for (int i = array.length - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = random.nextInt(i + 1);

            // Swap array[i] with the element at random index array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }


//    public void shuffleByGenres()
}

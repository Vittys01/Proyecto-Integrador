package com.digital.booking.adapter.input.controller.models.output.utils;

import com.digital.booking.adapter.input.controller.models.output.RentalRestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShuffleRand {
    // A Function to generate a random permutation of arr[]
    public static List<RentalRestModel> randomizeRentalRestModel(List<RentalRestModel> rentalRestModels)
    {
        // Creating a object for Random class
        Random r = new Random();
        RentalRestModel arrRent[] = rentalRestModels.toArray(new RentalRestModel[0]);
        int n = arrRent.length;

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i);

            // Swap arr[i] with the element at random index
            RentalRestModel temp = arrRent[i];
            arrRent[i] = arrRent[j];
            arrRent[j] = temp;
        }

        // Prints the random array
//        System.out.println(Arrays.toString(arrRent));
        return new ArrayList<>(List.of(arrRent));
    }

    // Driver Program to test above function
//    public static void main(String[] args)
//    {
//        List<RentalRestModel> list = new ArrayList<>(List.of(RentalRestModel.builder().id(1L).build(),RentalRestModel.builder().id(2L).build(),RentalRestModel.builder().id(3L).build()));
//        List<RentalRestModel> newList= randomizeRentalRestModel (list);
//        System.out.println(list);
//        System.out.println(newList);
//
//    }
}

package com.waelawada.learn.springboot.fixtures;

import com.waelawada.learn.springboot.domain.Address;

/**
 * Created by waelawada on 2/28/15.
 */
public class AddressFixtures {

    public static final Address FL_ADDRESS_1 =
            Address.newBuilder()
                    .streetAddress("234 Glades Rd.")
                    .city("Boca Raton").state("FL").country("US").zipCode("33431").build();

    public static final Address FL_ADDRESS_2 =
            Address.newBuilder()
                    .streetAddress("1052 Palmetto Rd")
                    .city("Boca Raton").state("FL").country("US").zipCode("33432").build();

    public static final Address FL_ADDRESS_3 =
            Address.newBuilder()
                    .streetAddress("362 Linton Rd.")
                    .city("Delray Beach").state("FL").country("US").zipCode("33444").build();


    public static final Address GA_ADDRESS_1 =
            Address.newBuilder()
                    .streetAddress("1031 Paces Ferry Rd")
                    .city("Smyrna").state("GA").country("US").zipCode("30080").build();

    public static final Address GA_ADDRESS_2 =
            Address.newBuilder()
                    .streetAddress("655 Peachtree Rd")
                    .city("Atlanta").state("GA").country("US").zipCode("30092").build();

    public static final Address GA_ADDRESS_3 =
            Address.newBuilder()
                    .streetAddress("655 Piedmont Dr.")
                    .city("Atlanta").state("GA").country("US").zipCode("30092").build();
}

package com.immani.mydwich

import grails.test.GrailsUnitTestCase

class RestaurantTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testRestaurantCreation() {
            mockDomain(Restaurant)

            def restaurant = new Restaurant();

            assertFalse restaurant.validate()

            println restaurant.errors;
    }
}

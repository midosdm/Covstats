package com.covstats.services;

import com.covstats.entities.CovData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CovDataServiceTest {

    @Autowired
    private CovDataService covDataService;

    @AfterEach
    void tearDown(){
    }

    @Test
    void save() {
    }

    @Test
    void getByPays() {
    }

    @Test
    void getByPaysAndDate() {
    }

    /**
     * this method tests the function getByPaysToday and compare the returned value with that of the function gitByPaysAndDateNonCumule
     * the test passes if the data from both function is similar
     */
    @Test
    void itShouldReturnCountryDataForToday() {
        //given
        String pays = "France";
        //when
        CovData expected = covDataService.getByPaysToday(pays);
        CovData actual = covDataService.getByPaysAndDateNonCumule(pays, LocalDate.now());
        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDeces(), actual.getDeces());
        assertEquals(expected.getGuerisons(), actual.getGuerisons());
        assertEquals(expected.getInfections(), actual.getInfections());
        assertEquals(expected.getTauxDeces(), actual.getTauxDeces());
        assertEquals(expected.getTauxGuerison(), actual.getTauxGuerison());
        assertEquals(expected.getTauxInfection(), actual.getTauxInfection());
    }
}
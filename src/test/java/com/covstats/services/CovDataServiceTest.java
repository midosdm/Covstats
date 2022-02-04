package com.covstats.services;

import com.covstats.entities.CovData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CovDataServiceTest {

    @Autowired
    private CovDataService covDataService;

    @Test
    void save() {
    }

    @Test
    void getByPays() {
    }

    @Test
    void getByPaysAndDate() {
    }

    @Test
    void itShouldReturnCountryDataForToday() {
        //given
        String pays = "France";
        //when
        CovData expected = covDataService.getByPaysToday(pays);
        //then
        assertNotNull(expected);

    }
}
package org.dlesnic.route.service;


import org.junit.jupiter.api.Test;
import org.dlesnic.route.model.dto.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Test
    public void shouldReturnRoute() {
        RouteResponse response = new RouteResponse(List.of("CZE", "AUT", "ITA"));
        assertThat(routeService.findRoute("CZE", "ITA")).isEqualTo(response);
    }

    @Test
    public void shouldReturnCountryWhenOriginAndDestinationAreTheSame() {
        RouteResponse response = new RouteResponse(List.of("CZE"));
        assertThat(routeService.findRoute("CZE", "CZE")).isEqualTo(response);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNoRoute() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> routeService.findRoute("CZE", "BRA"));

        String expectedMessage = "No existing route from CZE to BRA";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForWrongCountryName() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> routeService.findRoute("CCC", "BRA"));

        String expectedMessage = "Graph must contain the source vertex!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}

package org.dlesnic.route.controller;


import org.junit.jupiter.api.Test;
import org.dlesnic.route.model.dto.RouteResponse;
import org.dlesnic.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.StringContains.containsString;

@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService service;

    @Test
    public void shouldReturnShortestPath() throws Exception {
        when(service.findRoute("CZE", "ITA"))
                .thenReturn(new RouteResponse(List.of("CZE", "AUT", "ITA")));

        mockMvc.perform(get("/routing/CZE/ITA"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"route\":[\"CZE\",\"AUT\",\"ITA\"]}"));
    }

    @Test
    public void shouldReturnBadRequestWhenMissingPath() throws Exception {
        when(service.findRoute("CZE", "TWN"))
                .thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/routing/CZE/TWN"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Route not found")));
    }

    @Test
    public void shouldReturnValidationError() throws Exception {
        mockMvc.perform(get("/routing/CZEE/ITA"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("size must be between 3 and 3")));
    }
}

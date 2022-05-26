package org.pwc.route.controller;

import org.pwc.route.model.dto.RouteResponse;
import org.pwc.route.service.RouteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/routing")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/{origin}/{destination}")
    public RouteResponse findRoute(@PathVariable @Size(min = 3, max = 3) String origin,
                                   @PathVariable @Size(min = 3, max = 3) String destination) {
        return this.routeService.findRoute(origin, destination);
    }
}

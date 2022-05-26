package org.dlesnic.route.service;

import org.dlesnic.route.model.dto.RouteResponse;


public interface RouteService {
    RouteResponse findRoute(String origin, String destination);
}

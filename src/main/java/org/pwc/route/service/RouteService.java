package org.pwc.route.service;

import org.pwc.route.model.dto.RouteResponse;


public interface RouteService {
    RouteResponse findRoute(String origin, String destination);
}

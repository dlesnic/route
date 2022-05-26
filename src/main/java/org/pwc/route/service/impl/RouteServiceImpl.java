package org.pwc.route.service.impl;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.pwc.route.model.dto.RouteResponse;
import org.pwc.route.service.RouteService;
import org.springframework.stereotype.Service;


@Service
public class RouteServiceImpl implements RouteService {

    private final ShortestPathAlgorithm<String, DefaultEdge> dijkstraAlg;

    public RouteServiceImpl(ShortestPathAlgorithm<String, DefaultEdge> dijkstraAlg) {
        this.dijkstraAlg = dijkstraAlg;
    }

    @Override
    public RouteResponse findRoute(String origin, String destination) {
        GraphPath<String, DefaultEdge> path = dijkstraAlg.getPath(origin, destination);
        if (path == null) {
            throw new IllegalArgumentException(String.format("No existing route from %s to %s", origin, destination));
        }
        return new RouteResponse(path.getVertexList());
    }
}

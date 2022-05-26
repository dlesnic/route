package org.pwc.route;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.pwc.route.model.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteApplication.class, args);
	}


	@Bean
	public ShortestPathAlgorithm<String, DefaultEdge> createDijkstraGraph() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		File dataFile = new File("src/main/resources/countries.json");
		List<Country> countries = objectMapper.readValue(dataFile, new TypeReference<>() {});

		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		countries.forEach(country -> {
			country.borders().forEach(neighbor -> Graphs.addEdgeWithVertices(graph, country.cca3(), neighbor));
		});

		return new DijkstraShortestPath<>(graph);
	}
}

package com.example.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.example.demo.domain.Estudiante;
import com.example.demo.entity.Alumno;
import com.example.demo.mapper.MapeadorEstudiante;

@Service
public class EstudiantesServiceImpl implements EstudiantesService {
	private final String URL_BASE = "http://localhost:8080/api/alumnos";

	private final RestClient restClient;

	public EstudiantesServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Estudiante> findByRangoNotas(Integer min, Integer max) {
		Alumno[] alumnos = restClient.get()
				.uri(URL_BASE)
				.retrieve()
				.body(Alumno[].class);

		if (alumnos == null || alumnos.length == 0) {
			return Collections.emptyList();
		}

		return Arrays.stream(alumnos)
				.map(MapeadorEstudiante::toDto)
				.filter(e -> {
					if (e == null || e.getNota() == null) {
						return false;
					}
					float nota = e.getNota();
					if (min != null && nota < min) {
						return false;
					}
					if (max != null && nota > max) {
						return false;
					}
					return true;
				})
				.collect(Collectors.toList());
	}

	@Override
	public Alumno createEstudiante(Estudiante estudiante) {
		Alumno alumno = MapeadorEstudiante.toEntity(estudiante);
		try {
			Alumno creado = restClient.post()
					.uri(URL_BASE)
					.contentType(MediaType.APPLICATION_JSON)
					.body(alumno)
					.retrieve()
					.body(Alumno.class);
			return creado;
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.CONFLICT) {
				// En caso de 409 devolvemos null (política asumida); ajustar si se requiere otra acción
				return null;
			}
			throw ex;
		}
	}

}

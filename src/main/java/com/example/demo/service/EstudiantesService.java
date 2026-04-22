package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Estudiante;
import com.example.demo.entity.Alumno;

public interface EstudiantesService {
	List<Estudiante> findByRangoNotas(Integer min, Integer max);
	Alumno createEstudiante(Estudiante estudiante);
}

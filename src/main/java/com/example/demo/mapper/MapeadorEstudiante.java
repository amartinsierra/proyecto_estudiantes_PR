package com.example.demo.mapper;

import com.example.demo.domain.Estudiante;
import com.example.demo.entity.Alumno;

public class MapeadorEstudiante {
	//metodo estático toDto que transforma un objeto Alumno en un objeto Estudiante
	//teniendo en cuenta que el atributo calificacion de alumno se corresponde con el atributo nota de estudiante
	public static Estudiante toDto(Alumno alumno) {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre(alumno.getNombre());
		estudiante.setDni(alumno.getDni());
		estudiante.setEmail(alumno.getEmail());
		estudiante.setCurso(alumno.getCurso());
		estudiante.setNota(alumno.getCalificacion());
		return estudiante;
	}
	//metodo estático toEntity que transforma un objeto Estudiante en un objeto Alumno
	public static Alumno toEntity(Estudiante estudiante) {
		Alumno alumno = new Alumno(estudiante.getNombre(), estudiante.getDni(), estudiante.getEmail(), estudiante.getCurso(), estudiante.getNota());
		return alumno;
	}
}

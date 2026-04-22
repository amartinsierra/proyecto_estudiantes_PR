package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Estudiante;
import com.example.demo.entity.Alumno;
import com.example.demo.service.EstudiantesService;

@RestController
public class EstudiantesController {
	//declara una variable EstudiantesService que se inyectará mediante el constructor
	private final EstudiantesService estudiantesService;
	public EstudiantesController(EstudiantesService estudiantesService) {
		this.estudiantesService = estudiantesService;
	}
	//define un método que ante una petición get con la ruta "/estudiantes" devuelva la lista de estudiantes obtenida a través del servicio.
	//se reciben dos variables en URL con las notas minima y máxima para filtrar los estudiantes por nota
	//ejemplo de URL: http://localhost:8080/estudiantes?minNota=5&maxNota=10
	//el resultado se envuelve en un ResponseEntity con el código de estado HTTP 200 OK
	@GetMapping("/estudiantes")
	public ResponseEntity<List<Estudiante>> getEstudiantes(@RequestParam(required = false) Integer minNota,
														 @RequestParam(required = false) Integer maxNota) {
		List<Estudiante> estudiantes = estudiantesService.findByRangoNotas(minNota, maxNota);
		return ResponseEntity.ok(estudiantes);
	}
	
	//definir un método que ante una petición POST con la ruta "/estudiantes" reciba un objeto Estudiante en el cuerpo de la solicitud, lo cree a través del servicio y devuelva el estudiante creado envuelto en un ResponseEntity con el código de estado HTTP 201 Created
	//si el valor devuelto por el método createEstudiante es null, se devuelve un ResponseEntity con el código de estado HTTP 409 Conflict
	//debes asociar el método a la ruta "/estudiantes" y al verbo HTTP POST utilizando la anotación @PostMapping
	@PostMapping("/estudiantes")
	public ResponseEntity<Alumno> createEstudiante(@RequestBody Estudiante estudiante) {
		Alumno alumnoCreado = estudiantesService.createEstudiante(estudiante);
		if (alumnoCreado == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoCreado);
	}
}

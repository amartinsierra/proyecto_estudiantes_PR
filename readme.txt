promopt inicial:
Implementa el método findRangoNotas, teniendo en cuenta que una petición get a la URL_BASE devuelve todos los alumnos existentes. 
Para ello, utilizarás el skill definido en la sección de skills que se encuentra en la subcarpeta del proyecto .github/skills. 
Utiliza también MapeadorEstudiante para el mapeado entre objetos

prompt siguiente:
Implementa createEstudiante, pero ten en cuenta que habrá que capturar la excepción HttpClientErrorException
que se puede producir si la petición post() falla devolviendo un error 409

prompt final:
Traslada todos los cambios al proyecto, incluida la clase de configuración de RestTemplate.



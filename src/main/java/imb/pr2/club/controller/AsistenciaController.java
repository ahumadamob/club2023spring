package imb.pr2.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.service.IAsistenciaService;
import java.util.ArrayList;//
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/asistencia")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    // Mostrar todas las asistencias
    @GetMapping
    public ResponseEntity<APIResponse<List<Asistencia>>> mostrarTodos() {
        List<Asistencia> asistencias = asistenciaService.buscarAsistencia();
        APIResponse<List<Asistencia>> response = new APIResponse<>(HttpStatus.OK.value(), null, asistencias);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Mostrar asistencias por clase
    @GetMapping("/buscarporclase/{idclase}")
    public ResponseEntity<APIResponse<List<Asistencia>>> mostrarAsistenciasPorClase(@PathVariable("idclase") Integer idclase) {
        List<Asistencia> asistencias = asistenciaService.buscarAsistenciaPorClase(idclase);
        APIResponse<List<Asistencia>> response = new APIResponse<>(HttpStatus.OK.value(), null, asistencias);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Mostrar asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarAsistenciaPorId(@PathVariable("id") Integer id) {
        Asistencia asistencia = asistenciaService.buscarAsistenciaPorId(id);

        if (asistencia != null) {
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.OK.value(), null, asistencia);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("No se encontró la asistencia con el ID = " + id.toString());
            messages.add("Revise nuevamente el dato");
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), messages, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Inscribir a una clase
    @PostMapping("/inscribir/{claseId}")
    public ResponseEntity<?> inscribir(@PathVariable Integer claseId) {
        int cupo = 10; // Define el cupo máximo
        int inscritos = asistenciaService.getCountInscritosByClaseId(claseId);

        if (inscritos < cupo) {
            asistenciaService.inscribir(claseId);
            return ResponseEntity.ok("Inscripción exitosa");
        } else {
            return ResponseEntity.badRequest().body("Cupo máximo alcanzado");
        }
    }

    // Crear una asistencia
    @PostMapping
    public ResponseEntity<?> crearAsistencia(@RequestBody Asistencia asistencia) {
        Integer id = asistencia.getId();
        
        if (id != null && asistenciaService.buscarAsistenciaPorId(id) != null) {
            List<String> messages = new ArrayList<>();
            messages.add("Ya existe una asistencia con el ID = " + id.toString());
            messages.add("Para actualizar, utilice el verbo PUT");
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            asistenciaService.guardarAsistencia(asistencia);
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.CREATED.value(), null, asistencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    // Marcar como presente
    @PutMapping("/marcarPresente/{asistenciaId}")
    public ResponseEntity<?> marcarPresente(@PathVariable Integer asistenciaId) {
        asistenciaService.marcarPresente(asistenciaId);
        return ResponseEntity.ok("Asistencia marcada como presente");
    }

    // Marcar como ausente
    @PutMapping("/marcarAusente/{asistenciaId}")
    public ResponseEntity<?> marcarAusente(@PathVariable Integer asistenciaId) {
        asistenciaService.marcarAusente(asistenciaId);
        return ResponseEntity.ok("Asistencia marcada como ausente");
    }

    // Modificar una asistencia
    @PutMapping
    public ResponseEntity<?> modificarAsistencia(@RequestBody Asistencia asistencia) {
        Integer id = asistencia.getId();

        if (id != null && asistenciaService.buscarAsistenciaPorId(id) != null) {
            asistenciaService.guardarAsistencia(asistencia);
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.OK.value(), null, asistencia);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("No existe una asistencia con ese ID");
            messages.add("Para crear una nueva asistencia, utilice el verbo POST");
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Cancelar inscripción
    @DeleteMapping("/cancelar/{asistenciaId}")
    public ResponseEntity<?> cancelar(@PathVariable Integer asistenciaId) {
        asistenciaService.cancelarInscripcion(asistenciaId);
        return ResponseEntity.ok("Inscripción cancelada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsistencia(@PathVariable("id") Integer id) {
        if (existe(id)) {
            asistenciaService.eliminarAsistencia(id);
            return ResponseEntity.ok("La asistencia ha sido eliminada correctamente");
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("No existe una asistencia con el ID = " + id.toString());
            APIResponse<Asistencia> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), messages, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Método privado para verificar si existe una asistencia
    private boolean existe(Integer id) {
        return id != null && asistenciaService.buscarAsistenciaPorId(id) != null;
    }

    // Manejo de excepciones para restricciones de validación
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        APIResponse<?> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);
        return ResponseEntity.badRequest().body(response);
    }
}


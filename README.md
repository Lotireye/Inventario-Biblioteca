# Inventario Biblioteca

Sistema de gestión de biblioteca desarrollado en Java, con foco en aplicar Programación Orientada a Objetos y patrones de diseño de forma deliberada — no como una plantilla más, sino pensando cada decisión de arquitectura.

## Características

- Alta de libros y revistas, con generación automática de ID (nunca lo ingresa el usuario, para evitar duplicados)
- Gestión de stock: cada título puede tener múltiples copias (`stock` / `disponibles`)
- Préstamo y devolución de ítems, con manejo de errores cuando no hay copias disponibles
- Edición y eliminación de ítems existentes
- Listado de todo el inventario
- Validación robusta de entrada por consola: reintenta automáticamente si el usuario ingresa un tipo de dato incorrecto, sin romper el programa

## Tecnologías

- Java 26
- Maven (gestión de dependencias y build)

## Arquitectura y patrones de diseño

El proyecto está organizado en paquetes separando responsabilidades:

```
principal/   → Main.java — menú y flujo del programa
clases/      → ItemBiblioteca (abstracta), Libro, Revista
organizador/ → ItemFactory, GestorItems
utilidades/  → Lector
```

### Patrón Factory
`ItemFactory` centraliza la creación de `Libro` y `Revista` mediante métodos estáticos sobrecargados (`crearLibro`, `crearRevista`), evitando lógica condicional repetida en el resto del programa.

### Herencia y polimorfismo
`ItemBiblioteca` es una clase abstracta que define el comportamiento común (`prestar()`, `devolver()`) y declara `getInfo()` como abstracto, forzando a `Libro` y `Revista` a implementar su propia representación de información.

### Principio de responsabilidad única
Cada clase tiene un único motivo para cambiar:
- `ItemFactory` → solo crea objetos
- `GestorItems` → solo administra la colección (agregar, buscar, listar, eliminar)
- `Lector` → solo maneja la entrada validada por consola
- `Main` → solo orquesta el flujo del menú

### Encapsulamiento
Todos los atributos de las clases del modelo son `private`, expuestos únicamente mediante getters y, cuando corresponde, setters controlados. Los atributos que no deberían cambiar tras la creación (autor, género, edición, periodicidad) son inmutables.

## Cómo ejecutar

```bash
git clone https://github.com/[tu-usuario]/InventarioBiblioteca.git
cd InventarioBiblioteca
mvn compile
mvn exec:java -Dexec.mainClass="principal.Main"
```

## Capturas


## Roadmap

- [ ] Persistencia real con SQLite/JDBC (reemplazando el `ArrayList` en memoria)
- [ ] Clases `Usuario` y `Prestamo` para relacionar préstamos con usuarios y fechas
- [ ] Patrón Observer para notificaciones de vencimiento
- [ ] Tests unitarios con JUnit

## Autor

Angello Ramos

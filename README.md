# Prueba técnica GFT

## Ejecutar la app 🏃

- Desde el propio IDE en la [clase principal](src/main/java/org/rbernalop/pruebatecnicagft/PruebaTecnicaGftApplication.java).

- Maven:
```shell
.\mvnw clean spring-boot:run
```

## Dependencias 📚

Se listan las siguientes [dependencias](pom.xml):
- spring-boot-starter-web
- lombok
- spring-boot-starter-data-jpa
- h2
- mapstruct

Para testing:
- javafaker
- cucumber-java
- cucumber-junit
- cucumber-spring
- junit-vintage-engine

## Implementación 🔨

Se ha seguido la **arquitectura hexagonal**, la cual ayuda a proteger el dominio y casos 
de uso ante cambios en la infraestructura. Es decir, si queremos cambiar de motor de BD o 
actualizarlo ante una subida de versión de la dependencia usada, no se debería cambiar 
las capas de aplicación y dominio.

Organización de paquetes:
- price
  - application = Casos de uso con la lógica de negocio (como control de errores cuando el
  precio no se ha podido encontrar).
  - domain = Modelo de `Price` e interfaz del repositorio que sirve como puerto. 
  - infrastructure = Controlador para el endpoint `GET /api/v1/price` y adaptador del 
  repositorio con la implementación para H2.
- shared = Lógica de excepciones de la aplicación, implementada para que esté 
centralizada y se puedan añadir de manera sencilla en el [enum de errores](src/main/java/org/rbernalop/pruebatecnicagft/shared/domain/exception/ApplicationError.java).

## Testing 🧪

### Unitario

Se han realizado [tests unitarios a la capa de aplicación](src/test/java/org/rbernalop/pruebatecnicagft/price/application/finder/PriceFinderTest.java) 
para cubrir tanto los casos como los casos de error que se puedan dar.

- C1: Se obtiene el precio correctamente.
- C2: No se ha encontrado el precio.

### Integración

Se ha usado Cucumber para crear los [tests al endpoint](src/test/resources/cucumber_tests/price.feature) 
que se pedían, usando un Scenario Outline para que añadir todos los casos sea sencillo. 
Además, se ha configurado para que se inserten automáticamente los datos iniciales.

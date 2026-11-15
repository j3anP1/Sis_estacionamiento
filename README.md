# Grupo 10 – Sistema de Gestión de Estacionamiento

API REST empresarial desarrollada con **Spring Boot** bajo una **arquitectura N capas** para gestionar usuarios, vehículos, espacios de parqueo y el control de ingreso, salida, permanencia y cobro[cite: 1].

## Integrantes
* DIEGO ROBERTO DIAZ CHAVEZ
* ISAAC GEOVANNY BETUN VERGARA
* BRYAN ORLANDO ENRIQUEZ MANYA
* JEAN PIERRE GUADALUPE MACIAS
* 
## Tecnologías Utilizadas
* Java 21 / Spring Boot 4
* Spring Web & Spring Data JPA[cite: 1]
* Oracle Database (Persistencia principal)[cite: 1]
* Spring AOP (AspectJ) & Spring MVC Interceptors[cite: 1]
* GitHub Actions (CI/CD)[cite: 1]

## Arquitectura del Proyecto
* `controller`: Exposición de endpoints REST[cite: 1].
* `service`: Lógica y reglas de negocio[cite: 1].
* `repository`: Acceso a datos y consultas personalizadas Spring Data[cite: 1].
* `model`: Entidades JPA relacionales (`Usuario`, `Vehiculo`, `Espacio`, `Registro`)[cite: 1].
* `dto`: Objetos de transferencia de datos[cite: 1].
* `exception`: Excepciones personalizadas y `GlobalExceptionHandler`[cite: 1].
* `interceptor` & `configuration`: Auditoría de peticiones HTTP y medición de tiempos[cite: 1].
* `aspect`: Monitoreo y auditoría transversal de servicios con AOP[cite: 1].

## Configuración de Base de Datos (Oracle)
1. Crear un usuario/esquema en Oracle Database.
2. Configurar las credenciales en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/xxxxx
   spring.datasource.username=xxxxxxxx
   spring.datasource.password=xxxxxxxx
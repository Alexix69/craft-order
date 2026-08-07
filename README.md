# craft-order — Backend API

Proyecto backend del sistema web para Mueblería Classic. API REST desarrollada con Spring Boot 4.1.0 y arquitectura Clean Architecture. Gestiona la lógica de negocio de cotización de muebles, coordinación de producción por fases y notificaciones automáticas por correo.

## Tecnologías

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA + Hibernate
- PostgreSQL 16
- MapStruct 1.6.3
- Lombok
- Resend (correo electrónico)
- Cloudinary (almacenamiento de imágenes y PDFs)
- iText7 (generación de facturas PDF)
- Docker (base de datos)

## Requisitos previos

- Java 21 instalado
- Maven (o usar el wrapper `./mvnw` incluido)
- Docker Desktop instalado y corriendo
- IDE recomendado: Spring Tool Suite 4 o IntelliJ IDEA

## Estructura del proyecto

```
craft-order/
├── src/main/java/com/classic/craftorder/
│   ├── dominio/          # Entidades de dominio e interfaces de repositorio
│   ├── aplicacion/       # Casos de uso y servicios transversales
│   ├── infraestructura/  # Adaptadores JPA, mappers, configuración
│   └── presentacion/     # Controladores REST y DTOs
├── docker-compose.yml    # PostgreSQL en Docker
├── .env.example          # Plantilla de variables de entorno
└── README.md
```

## Configuración de variables de entorno

El proyecto usa valores por defecto en `application.properties` que funcionan directamente con el Docker Compose incluido. No se requiere configuración adicional para levantar el sistema.

> ℹ️ **Nota:** Los valores por defecto en `application.properties` son suficientes para levantar el sistema completo sin configuración adicional, tanto si descargaste el proyecto desde GitHub como desde el archivo RAR de entrega.

Para el flujo de notificaciones por correo, el sistema usa [Resend](https://resend.com). Si deseas probar el envío de correos con tu propia cuenta, crea una cuenta gratuita en resend.com, obtén tu API key y reemplaza el valor de `RESEND_API_KEY` en el `application.properties`.

## Pasos para ejecutar

### 1. Levantar la base de datos

```bash
docker compose up -d
```

Esto crea y levanta un contenedor PostgreSQL con la base de datos `craftorder`. Las tablas se crean automáticamente al iniciar la aplicación.

### 2. Abrir el proyecto en el IDE

Importa la carpeta `craft-order` como proyecto Maven existente.

### 3. Ejecutar la aplicación

Desde el IDE ejecuta la clase principal `CraftorderApplication` o desde terminal:

```bash
./mvnw spring-boot:run
```

La API queda disponible en: `http://localhost:8080`

> El frontend debe estar corriendo en `http://localhost:8081` para la comunicación entre proyectos.

## Credenciales de acceso al sistema

| Rol | Correo | Contraseña |
|---|---|---|
| Administrador | admin@classic.com | Admin1234 |
| Artesano | (creado desde el panel admin) | (generada automáticamente) |

> Las credenciales del administrador se cargan automáticamente al iniciar la aplicación por primera vez.

## Puertos

| Servicio | Puerto |
|---|---|
| Backend API | 8080 |
| PostgreSQL | 5432 |

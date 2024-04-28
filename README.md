# QuickProd-v2 🚀
## QuickProd simplifica la gestión y maximiza la eficiencia en la administración de productos en diversos contextos.

# ¿Que permite QuickProd? ⚡
Permite combinar los elementos y recursos más utilizados, permitiendonos así tener una suite de microservicios diseñada para proporcionar funcionalidades escalables y distribuidas para la gestión de productos, categorías e inventarios.

Está estructurado como una arquitectura de microservicios utilizando Spring Boot, con servicios para manejar diferentes aspectos de dominio como productos, categorías y gestión de inventario.

La arquitectura está diseñada para ser escalable, segura y eficiente, con un servidor de configuración central, un servidor de descubrimiento para el registro de servicios y una puerta de enlace API que dirige a los diferentes servicios e implementa autentificacion con Keycloack.

## 🎥 [Demostraciones de Quickprod en directo](https://github.com/AdrianAlonsoDev/dekra-qp/wiki/Inicio#demostraciones)

# Architecture diagram
<p align="center">
  <img width="410" height="400" src="https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/2b14cd2c-b7b5-45c1-97c9-14358c4c816f">
  </br>
  Keycloack para autenticación, Zipkin capturador de trazas, propagación de autenticación con TokenRelay.
</br>
</p>

## System Architecture

### Descripcion de los Servicios

#### Config Service
- **Proposito**: Permite onfiguración centralizada que utiliza Spring Cloud Config para gestionar todas las configuraciones de los microservicios.
- **Tecnologías**: Spring Cloud Config, Spring Boot.

#### Discovery Service
- **Proposito**: Utiliza [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html) para el registro y la localización de servicios en nuestra infraestructura.
- **Tecnologías**: Spring Cloud Netflix Eureka.

#### Gateway Service
- **Proposito**: Actúa como un punto de entrada unificado para los servicios.
- **Tecnologías**: Spring Cloud Gateway, OAuth2, Keycloack, [LoadBalancer](https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway-server-mvc/filters/loadbalancer.html) Spring Boot.

### Product Service
- **Proposito**: Maneja las operaciones relacionadas con productos, almacenando los datos en cache con Redis.
- **Tecnologías**: Spring Data JPA, Spring Starter Redis para almacenar en cache, Spring Cloud OpenFeign para comunicación entre servicios.

### Category Service
- **Proposito**: Encargado de las operaciones relacionadas con categorías de productos, Redis para caché, OpenFeign.
- **Tecnologías**: Spring Boot, Spring Data JPA, Spring Cloud OpenFeign para comunicación entre servicios.
- 
### Inventory Service
- **Proposito**: Encargado de las operaciones relacionadas con categorías de productos, Redis para caché, OpenFeign.
- **Tecnologías**: Spring Boot, Spring Data JPA, Spring Cloud OpenFeign para comunicación entre servicios.

Utilizamos [FeignClients](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html) para la inter comunicación de los servicios.

## Installation 🛠️
- Instalar Docker y Docker Compose para levantar instancias de Keycloack, Redis, ZIPKIN
- JDK 17

## Run the project
Para ejecutar el proyecto, sigue estos pasos:

1. Dentro del directorio 'docker', lanza el comando
    - `docker-compose up --build -d`
    - Revisa el inicio correcto de cada container con el prefijo 'dekra-'

2. Ahora ejecuta los servicios en el siguiente orden:
    - `mvn spring-boot:run`
      1. Config Service
      2. Discovery Service
      3. Gateway Service
      4. (Product Service, Category Service, Inventory Service)

4. Conectate al frontend del Gateway en la siguiente URL:
    - `http://localhost:8060/`
Le redirigira automáticamente a la interfaz de Keycloack para autenticarse con el cliente de.
    - Usuario: dekra
    - Contraseña: dekra
Tras una autentificación satisfactoria, le redirigirá nuevamente a la ruta del GATEWAY,
protegiendo así el resto de servicios.
4. Si lo necesitas, accede al panel de administrador de keycloack desde la siguiente Url:
    - `http://localhost:8080/`
Le redirigirle automáticamente a la interfaz admin de Keycloack.
    - Usuario: admin
    - Contraseña: admin
Tras una autentificación satisfactoria, le redirigirá nuevamente a la ruta del GATEWAY,
protegiendo así el resto de servicios.

## Docker Utils 🐳
Lista de contenedores:
-  dekra-keycloack (Manejo de inicio de sesión)
-  dekra-redis (Almacenamiento en cache)
-  dekra-zipkin (Evaluar las trazas)

![containerlist](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/d6322037-4e9a-415a-9faa-00e4338eda3e)


#### Acceder a los contenedores
Para acceder a un contenedor, utiliza el comando:
* `docker exec -it {nombre_del_contenedor} bash`


* Para salir usa, `exit`


Roadmap:
- Crud de productos. (Localizado en product-service) ✅
- Todas las requests HTTP necesitan antes ser autenticadas con Keycloack. (Inclusive, el token debe ser emitido con el SCOPE_manager para acceder a los endpoints) ✅
- Los datos de product, category e inventory son almacenados en memoria H2, utilizando Redis como caché, con previsión de cambiar a una real en producción. ✅
- Aspecto en cada servicio del dominio para loggear el tiempo de cada método, avisando según ms el tipo de alerta (INFO, WARN, ERROR) ✅

 Puntos de la prueba técnica restantes por realizar:
* Calculadora de impuestos por configuracion funcional, pero todavía no está integrado para aplicar a todos los precios de los productos. 🆗
* (Opcional) Desarrollar un endpoint para la obtención de los productos mediante una “query”
dinámica, es decir, que se pueda filtrar por cualquier propiedad del producto de forma dinámica.

En proceso de documentar mis decisiones de arquitectura, problemas encontrados y soluciones aplicadas, junto con todas las referencias que utilicé para construir el proyecto. STAND BY

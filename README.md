## DEKRA-QP 🚀
## QuickProd es una suite de servicios que permite maximizar la eficiencia en la gestión de productos en diferentes contextos.
(Desarrollado para la prueba técnica de DEKRA)

## What is this? 🏃
QuickProd combina los elementos y recursos más utilizados, permitiendonos así tener una suite de microservicios diseñada para proporcionar funcionalidades escalables y distribuidas para la gestión de productos, categorías e inventarios.
Ha sido diseñada para facilitar la expansión y mantenimiento al segregarse en servicios distintos, cada uno con su responsabilidad específica dentro de la arquitectura global.


## Project Architecture
![Captura de pantalla 2024-04-15 120255](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/2b14cd2c-b7b5-45c1-97c9-14358c4c816f)

El proyecto está estructurado en múltiples servicios, cada uno hubicado en su propio subdirectorio dentro del repositorio principal. Los servicios incluidos son:

- ### Service Discovery (discovery-service):
  Utiliza [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html) para el registro y la localización de servicios dentro de la infraestructura de microservicios.
- ### API Gateway (gateway-service):
  * Actúa como un punto de entrada unificado para los servicio.
  * Todas las solicitudes de los endpoints a cada servicio provienen del gateway, protegiéndo los demás servicios tras esta capa.
  * Utilizamos propagacion del token desde el gateway hasta el resto de servicios.
  * Ayuda del LB automático de servicios que estén registrados en la configuración (Sección routes en [gateway-service.yml](https://github.com/AdrianAlonsoDev/dekra-qp/blob/main/config-service/src/main/resources/config/gateway-service.yml).
  (localhost:8060/serviceName/**)
  Utiliza Keycloack para la autentificación de usuarios y el manejo de SCOPES de clientes.
- ### Config Service (config-service):
  Gestiona la configuración externa de los servicios con Spring Cloud Config.
  Todas las configuraciones se cargan desde la carpeta ["config"](https://github.com/AdrianAlonsoDev/dekra-qp/tree/main/config-service/src/main/resources/config) dentro del servicio config.
- ### Product Service (product-service):
  Maneja las operaciones relacionadas con productos, almacenando los datos en cache con Redis. Pese a que contiene ID de category e inventory, no habrá problema por iniciarlo individualmente.
- ### Category Service (category-service):
  Encargado de las operaciones relacionadas con categorías de productos, también con caché en Redis.
- ### Inventory Service (inventory-service):
  Controla las funcionalidades relacionadas con el inventario o werehouses

Todos los servicios tienen sus responsabilidades separadas, excepto en algún posible usecase por corregir, funcionarán individualmente.
Utilizamos [FeignClients](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html) para la inter comunicación de los servicios.

![Captura de pantalla 2024-04-15 120942](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/3858e1fc-4f70-4b9a-98d2-87fc259193e9)

## Installation 🛠️
- Instalar Docker y Docker Compose para levantar: Keycloack, Redis, ZIPKIN
- JDK 17

## Run the project
Para ejecutar el proyecto, sigue estos pasos:

1. Dentro de la carpeta de docker, ejecuta el siguiente comando:
    - `docker-compose up --build -d`

2. Ahora ejecuta cada servicio Spring Boot con el siguiente comando:
    - `mvn spring-boot:run`

4. Conectate al frontend del Gateway en la siguiente URL:
    - `http://localhost:8060/`
Le redirigirle automáticamente a la interfaz de Keycloack para autenticarse.
    - Usuario: dekra
    - Contraseña: dekra
Tras una autentificación satisfactoria, le redirigirá nuevamente a la ruta del GATEWAY,
protegiendo así el resto de servicios.


## Docker Utils 🐳
Lista de contenedores:
-  dekra-keycloack (Manejo de inicio de sesión)
-  dekra-redis (Almacenamiento en cache)
-  dekra-zipkin (Evaluar las trazas)

![Captura de pantalla 2024-04-15 121147](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/d6322037-4e9a-415a-9faa-00e4338eda3e)


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
#### Algunos ejemplos mientras termino de documentar todo...

![keycloacklogin](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/e2c876bc-ede8-424f-9e28-be0c59a6e9a1)

![Animation](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/8eddc627-5042-4598-ab98-156d390847b1)

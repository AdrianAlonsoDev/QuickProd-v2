## DEKRA-QP 🚀
## DEKRA QuickProd es una suite de servicios que permite maximizar la eficiencia en la gestión de productos en diferentes contextos.
(Desarrollado para la prueba técnica de DEKRA)

## What is this? 🏃
QuickProd es una combinación de los elementos y recursos más utilizados, permitiendonos así tener una suite de microservicios diseñada para proporcionar funcionalidades escalables y distribuidas para la gestión de productos, categorías e inventarios.
Ha sido diseñada para facilitar la expansión y mantenimiento al segregarse en servicios distintos, cada uno con su responsabilidad específica dentro de la arquitectura global.

## Project Structure
El proyecto está estructurado en múltiples servicios, cada uno hubicado en su propio subdirectorio dentro del repositorio principal. Los servicios incluidos son:

- ### Service Discovery (discovery-service):
  Utiliza Eureka Server para el registro y la localización de servicios dentro de la infraestructura de microservicios.
- ### API Gateway (gateway-service):
  Actúa como un punto de entrada unificado para los servicios, manejando la autenticación, autorización y enrutamiento a los diferentes servicios.
  Utiliza Keycloack para la autentificación de usuarios y el manejo de SCOPES de clientes.
- ### Config Service (config-service):
  Gestiona la configuración externa de los servicios con Spring Cloud Config.
- ### Product Service (product-service):
  Maneja las operaciones relacionadas con productos, almacenando en cache con Redis.
- ### Category Service (category-service):
  Encargado de las operaciones relacionadas con categorías de productos.
- ### Inventory Service (inventory-service):
  Controla las funcionalidades relacionadas con el inventario y la gestión de stock.

Todos los servicios tienen sus responsabilidades separadas,excepto en algún posible usecase por corregir, deberían de funcionar individualmente aunque falten datos de los demás servicios.

## Installation 🛠️
- Instalar Docker y Docker Compose.

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

#### Acceder a los contenedores
Para acceder a un contenedor, utiliza el comando:
* `docker exec -it {nombre_del_contenedor} bash`


* Para salir usa, `exit`

Puntos de la prueba técnica restantes por realizar:

* (Opcional) Desarrollar un endpoint para la obtención de los productos mediante una “query”
dinámica, es decir, que se pueda filtrar por cualquier propiedad del producto de forma dinámica.

* El calculador de impuestos es funcional, pero no esta terminado de estar integrado para que funcione con cualquier precio de los productos.


#### Algunos ejemplos mientras termino de documentar todo...

![keycloacklogin](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/e2c876bc-ede8-424f-9e28-be0c59a6e9a1)

![Animation](https://github.com/AdrianAlonsoDev/dekra-qp/assets/6146371/8eddc627-5042-4598-ab98-156d390847b1)

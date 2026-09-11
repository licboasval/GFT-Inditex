Ejercicio para prueba técnica GFT
----------------------------------------

Este proyecto expone un servicio REST para obtener el precio aplicable de un producto según la marca, la fecha de aplicación y el identificador del producto.

Endpoint:
http://localhost:8080/inditex/api/prices

Parámetros de entrada (query params):
- applicationDate: fecha y hora de aplicación, formato ISO-8601 (ej. 2020-06-14T16:00:00)
- productId: identificador del producto (ej. 35455)
- brandId: identificador de la marca (ej. 1)

Ejemplo de llamada:
http://localhost:8080/inditex/api/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1

Ejemplo de respuesta:
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}

Casos de prueba que validan la lógica:
- Test 1: 2020-06-14 10:00:00, productId=35455, brandId=1 -> tarifa 1, precio 35.50
- Test 2: 2020-06-14 16:00:00, productId=35455, brandId=1 -> tarifa 2, precio 25.45
- Test 3: 2020-06-14 21:00:00, productId=35455, brandId=1 -> tarifa 1, precio 35.50
- Test 4: 2020-06-15 10:00:00, productId=35455, brandId=1 -> tarifa 3, precio 30.50
- Test 5: 2020-06-16 21:00:00, productId=35455, brandId=1 -> tarifa 4, precio 38.95

La solución incluye:
- base de datos H2 en memoria
- datos iniciales cargados desde src/main/resources/data.sql
- tests unitarios y de integración
- documentación OpenAPI/Swagger

Base de datos H2:
- URL: http://localhost:8080/inditex/h2-console
- Usuario: sa
- Password: (vacío)
- JDBC URL a introducir manualmente en la consola H2:
  jdbc:h2:mem:inditexDB;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

Nota: la consola H2 no reutiliza automáticamente la propiedad spring.datasource.url de Spring Boot. Por ello, al abrir la consola es necesario introducir la JDBC URL anterior manualmente; si se deja la URL por defecto (por ejemplo jdbc:h2:~/test), la aplicación intentará conectarse a una base inexistente y devolverá el error 90149.

Swagger:
- URL: http://localhost:8080/inditex/swagger-ui/index.html

Ejecución del proyecto:
- ./mvnw test
- ./mvnw spring-boot:run

<div align=center><img src="./images/Badge-Spring.png" width="150"></div>

# 💻 Proyecto ForoHub 
Este proyecto, desarrollado en Java y SpringBoot es parte de mi formación en Alura Latam en el área de implementación de API.
La API ForoHub es una API REST dedicada a la gestión integral de un foro, abarcando tópicos, respuestas y cursos. 
Esta API proporciona una variedad de endpoints que permiten realizar operaciones esenciales, como crear, actualizar, listar y eliminar tópicos, respuestas y cursos. 
Además, incluye funcionalidades avanzadas para filtrar tópicos según diferentes criterios, facilitando la búsqueda y organización de la información. 
Con ForoHub, los desarrolladores pueden construir y mantener foros dinámicos y eficientes, optimizando la interacción y el intercambio de conocimientos entre los usuarios.

# 📏 Reglas de Negocio 

### 1. Registrar Tópico 
Los usuarios autenticados pueden crear nuevos tópicos en el foro. Cada tópico debe incluir un título claro, una descripción detallada y etiquetas relevantes para facilitar la búsqueda y la organización.

### 2. Eliminar Tópico 

Los administradores pueden eliminar lógicamente un tópico del foro. Esta acción es reversible.

### 3. Actualizar Tópico 

Los administradores y los moderadores pueden actualizar la información de un tópico, incluyendo el título, la descripción y la categoría asociada.

### 4. Autenticación 

Los usuarios deben estar autenticados para participar en el foro, ya sea para crear, actualizar o eliminar tópicos. La autenticación debe asegurar la identidad del usuario y proteger la integridad del foro
mediante el uso de JWT.

### 5. Listar Tópicos 
Todos los usuarios, autenticados o no, pueden ver la lista de tópicos disponibles en el foro. La lista debe ser filtrable y ordenable por diferentes criterios, como fecha de creación o alfabéticamente.

### 6. Crear una Respuesta 

Tanto Administradores como usuarios, pueden añadir respuestas a los tópicos para facilitar la interacción de la comunidad.

### 7. Actualizar una Respuesta

Es posible modificar el contenido de las respuestas agregadas en un tópico particular, a manera de corrección en caso de errores de escritura u otros inconvenientes.

# 🎁 Funcionalidades Adicionales 
Opté por implementar algunas funcionalidades más a fin de hacer este pequeño proyecto más completo en cuanto a gestión por
parte de los administradores del mismo.

- Eliminar lógicamente usuarios.
- Eliminar lógicamente tópicos.
- Eliminar lógicamente Tópicos y sus respuestas.
- Estandarizado de Retornos API
- Autenticación StateLess
- Cifrado de Contraseñas (Bcrypt)
- Autorización de métodos mediante Roles
- Seguridad mediante JWT (Generacion de Tokens).
- Protección contra Ataques (clickjacking)
- manejar los estados de los tópicos (Abierto, Cerrado, Archivado, Anclado).s.

# 🛢Esquema de Base de datos

<div align=center><img src="./images/Schema.png" width="600"></div>

# 🚀 Despliegue

Sigue estos pasos para ejecutar el proyecto en tu equipo, recuerda tener instalado el JDK.

- Clona el repositorio en tu equipo.
- Importa el proyecto a IntelliJ u otro IDE que soporte Java
- Crea la base de datos "forohub" en MySQL
- Configura las variables de entorno para la BD y JWT en application.properties
- Ejecuta el proyecto
- Crea y prueba las requests en algún Rest Client como Insomnia o Postman
- También puedes probar el proyecto con SpringDoc, usando la herramienta Swagger

# Ejemplo de funcionamiento

Para la demostración de esta aplicación podemos hacer uso de insomnia o Swagger para probar cada uno de los endpoints.

### Login:
<div align=center><img src="./images/Login.png" width="500"></div>

### Insertar un tópico:

<div align=center><img src="./images/insertTopic.png" width="500"></div>

### Listar un Tópico:

<div align=center><img src="./images/listTopic.png" width="500"></div>

### Vista de endpoints en Swagger-ui:

<div align=center><img src="./images/Swagger.png" width="500"></div>

# 🔧 Tecnologías Utilizadas 

<div align="center">
	<code><a href="https://www.oracle.com/java/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></a></code>
	<code><a href="https://start.spring.io/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring"/></a></code>
	<code><a href="https://maven.apache.org/guides/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/></a></code>
	<code><a href="https://hibernate.org/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png" alt="Hibernate" title="Hibernate"/></a></code>
	<code><a href="https://projectlombok.org/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok"/></a></code>
	<code><a href="https://www.mysql.com/" target="_blank"><img width="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/></a></code>
  	<code><a href="https://flywaydb.org/" target="_blank"><img width="50" src="https://upload.wikimedia.org/wikipedia/commons/e/e1/Flyway_logo.svg" alt="Flyway" title="Flyway"/></code>
	<code><a href="https://insomnia.rest/download" target="_blank"><img width="50" src="https://i.ibb.co/s9RzC1t/insomnia-seeklogo.png" alt="Insomnia"  title="Insomnia"/></code>
</div>

# 👤 Acerca De 

Soy Christian Estupiñan, un joven desarrollador Jr. comprometido con el aprendizaje continuo y con la meta de convertirme en desarrollador full-stack.
